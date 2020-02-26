//delIP为temporary_field
type IPGroup struct {
	TenantID    string
	NetworkID   string
	SubnetID    string
	SubnetCidr  string
	SubnetPools []AllocationPool
	Name        string
	ID          string
	IPs         string
	IPsSlice    []string
	AddIPs      []string
	DelIPs      map[string]string // this is temporary_field
	SizeStr     string
	Size        int
	AddSize     int
	Mode        IPMode
}


func (self *IPGroup) analyzeUpdateIPStr(igInDb *IPGroupInDB) error {
	//empty string means do not change ips
	if self.IPs == "" {
		klog.Debugf("IpGroup analyzeUpdateIps: ip is empty string, do not update ips")
		return nil
	}

	ipStrInDb := make([]string, 0)
	for _, ip := range igInDb.IPs {
		ipStrInDb = append(ipStrInDb, ip.IPAddr)
	}

	//calculate AddIps
	if self.IPs != "" {
		self.AddIPs = StringSliceDiff(self.IPsSlice, ipStrInDb)
	}

	//calculate DelIps
	DelIPAddrs := StringSliceDiff(ipStrInDb, self.IPsSlice)
	if self.DelIPs == nil {
		self.DelIPs = make(map[string]string, 0)
	}
	for _, ip := range igInDb.IPs {
		if InSliceString(ip.IPAddr, DelIPAddrs) {
			if ip.Used {
				klog.Errorf("IpGroup analyzeUpdateIps error: ip[%v] is in use, id: [%v]", ip.IPAddr, self.ID)
				return BuildErrWithCode(http.StatusConflict, errors.New("ip["+ip.IPAddr+"] is in use"))
			}
			self.DelIPs[ip.IPAddr] = ip.PortID
		}
	}

	return nil
}



func (self *IPGroup) Operate(igInDb *IPGroupInDB) error {
	if self.IsDuplicateName(igInDb) {
		klog.Errorf("IpGroup operate error, ip group name exists, name: [%v]", self.Name)
		return BuildErrWithCode(http.StatusConflict, errors.New("ip group name exists"))
	}

	if !self.IsValidIPs() {
		klog.Errorf("IpGroup operate error, invalid ips, name: [%v], ips: [%v]", self.Name, self.IPs)
		return BuildErrWithCode(http.StatusBadRequest, errors.New("invalid ips"))
	}

	err := self.AnalyzeIPs(igInDb)
	if err != nil {
		klog.Errorf("IpGroup analyzeIps error: [%v]", err.Error())
		return err
	}
	klog.Infof("IpGroup addIps: [%v], delIps: [%v], AddIPsCount: [%v]", self.AddIPs, self.DelIPs, self.AddSize)
	if igInDb == nil {
		igInDb = &IPGroupInDB{Name: self.Name, NetworkID: self.NetworkID, ID: self.ID, TenantID: self.TenantID}
	} else if self.Name != "" {
		igInDb.Name = self.Name
	}

	//todo check addips if available

	//create ips
	err = self.createIps(&(igInDb.IPs))
	if err != nil {
		klog.Errorf("IpGroup operate createIps error: [%v], id: [%v]", err.Error(), self.ID)
		return err
	}

	//delete ips
	failedips := make([]string, 0)
	for ipAddr, portID := range self.DelIPs {
		klog.Infof("IpGroup delete ip[%v] start", ipAddr)
		err = iaas.GetIaaS(self.TenantID).DeletePort(portID)
		if err != nil {
			klog.Errorf("IpGroup Delete DeletePort error: [%v], id: [%v], port_id: [%v]", err.Error(), self.ID, portID)
			failedips = append(failedips, ipAddr)
			continue
		}

		RemoveIPFromSlice(&(igInDb.IPs), ipAddr)
	}

	err = saveIGToDBAndCache(igInDb)
	if err != nil {
		klog.Errorf("IpGroup Create saveIpGroupToEtcd error: [%v], ipgroup: [%v]", err.Error(), self.ID)
		return BuildErrWithCode(http.StatusInternalServerError, err)
	}

	if len(failedips) > 0 {
		klog.Errorf("IpGroup Delete error, failedips[%v]", failedips)
		return BuildErrWithCode(http.StatusInternalServerError, errors.New("ip["+strings.Join(failedips, ",")+"] delete failed"))
	}

	return nil
}

