package com.haiwen.school.zx.service;

import java.util.List;
import java.util.Map;

import com.haiwen.school.zx.beans.HistoryIp;
import com.haiwen.school.zx.beans.Ipform;

public interface IpService {
	
	//添加ip
	void addIp(Ipform ipform);
	
	//获取所有IP
	List<Ipform> getAllIpform();
	Map<String, Object> getAll(int page, int limit, Ipform ipform);
	
	//根据id（也就是ipNumber）获取一条IP地址信息
	Ipform getIpformById(Integer id);
	
	Ipform getIpformByIpAddress(String address);
	
	//根据id修改IP地址信息
	void updateIpById(Ipform ipform);
	
	void updateIpByIpAddressSelective(Ipform ipform);
	
	//根据id删除IP地址信息
	void deleteIpById(Integer id);
	
	void deleteIpByIpAddress(String address);

	Map<String, Object> getHistoryIpAll(int page, int limit, HistoryIp historyIp);
}
