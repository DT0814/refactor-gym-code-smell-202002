//这个代码功能点太多，变化的方向太多。
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