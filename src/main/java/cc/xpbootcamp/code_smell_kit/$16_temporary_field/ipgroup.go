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

    //do otherthings
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

	return nil
}

