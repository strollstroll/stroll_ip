package com.unicom.ip.zyh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.unicom.ip.zyh.beans.HistoryIp;
import com.unicom.ip.zyh.beans.Ipform;
import com.unicom.ip.zyh.mapper.HistoryIpMapper;

public class HistoryIpAddUtil {
	@Autowired
	private HistoryIpMapper historyIpMapper;
	public HistoryIp historyIpAdd(Ipform ipform) {
		HistoryIp historyIp=new HistoryIp();
	       historyIp.setIpNumber(ipform.getIpNumber());
	       historyIp.setIpAddress(ipform.getIpAddress());
	       if(ipform.getIpStatus()!=null){
	    		historyIp.setIpStatus(ipform.getIpStatus());
		    	}
	    	if(ipform.getIpRemarks()!=null){
	    		historyIp.setIpRemarks(ipform.getIpRemarks());
		    	}
	    	if(ipform.getIpSubnetmask()!=null){
	    		historyIp.setIpSubnetmask(ipform.getIpSubnetmask());
		    	}
	    	if(ipform.getIpAddressnumber()!=null){
	    		historyIp.setIpAddressnumber(ipform.getIpAddressnumber());
		    	}
	    	if(ipform.getIpUsetime()!=null){
	    		historyIp.setIpUsetime(ipform.getIpUsetime());
		    	}
	    	if(ipform.getIpUsername()!=null){
	    		historyIp.setIpUsername(ipform.getIpUsername());
		    	}
	    	if(ipform.getIpVlan()!=null){
	    		historyIp.setIpVlan(ipform.getIpVlan());
		    	}
	    	if(ipform.getIpConnectingdevice()!=null){
	    		historyIp.setIpConnectingdevice(ipform.getIpConnectingdevice());
		    	}
	    	if(ipform.getIpPort()!=null){
	    		historyIp.setIpPort(ipform.getIpPort());
		    	}
	    	if(ipform.getIpRate()!=null){
	    		historyIp.setIpRate(ipform.getIpRate());
		    	}
	    	if(ipform.getIpAttribution()!=null){
	    		historyIp.setIpAttribution(ipform.getIpAttribution());
		    	}
	    	if(ipform.getIpBroadbandacceptancenumber()!=null){
	    		historyIp.setIpBroadbandacceptancenumber(ipform.getIpBroadbandacceptancenumber());
		    	}
	    	if(ipform.getIpSnnumber()!=null){
	    		historyIp.setIpSnnumber(ipform.getIpSnnumber());
		    	}
	    	if(ipform.getIpOltaddress()!=null){
	    		historyIp.setIpOltaddress(ipform.getIpOltaddress());
		    	}
	    	if(ipform.getIpIomusername()!=null){
	    		historyIp.setIpIomusername(ipform.getIpIomusername());
		    	}
	    	if(ipform.getIpInstalledaddress()!=null){
	    		historyIp.setIpInstalledaddress(ipform.getIpInstalledaddress());
		    	}
	    	if(ipform.getIpType()!=null){
	    		historyIp.setIpType(ipform.getIpType());
		    	}
	    	if(ipform.getIpWotvbssremarks()!=null){
	    		historyIp.setIpWotvbssremarks(ipform.getIpWotvbssremarks());
		    	}
	    	if(ipform.getIpOutputrate()!=null){
	    		historyIp.setIpOutputrate(ipform.getIpOutputrate());
		    	}
	    	if(ipform.getIpInputrate()!=null){
	    		historyIp.setIpInputrate(ipform.getIpInputrate());
		    	}
	    	if(ipform.getIpTerminalnumber()!=null){
	    		historyIp.setIpTerminalnumber(ipform.getIpTerminalnumber());
		    	}
	       
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       historyIp.setHistoryTime(df.format(new Date()));

	       return historyIp;
	}
}
