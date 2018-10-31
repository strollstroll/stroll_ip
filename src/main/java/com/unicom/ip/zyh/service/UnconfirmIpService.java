package com.unicom.ip.zyh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.unicom.ip.zyh.beans.Ipform;
import com.unicom.ip.zyh.beans.UnconfirmIp;


public interface UnconfirmIpService {
	//添加ip，添加完后信息录入到待审核处
	void addUnconfirmIp(UnconfirmIp unconfirmIp);
	
	//获取所有待审核IP
	List<UnconfirmIp> getAllUnconfirmIp();
	Map<String, Object> getAll(int page, int limit, UnconfirmIp unconfirmIp,HttpSession session);
	
	//根据id（也就是ipNumber）获取一条待审核的IP地址信息
	UnconfirmIp getUnconfirmIpById(Integer id);
	
	//根据ip地址获取一条待审核的IP地址信息
	UnconfirmIp getUnconfirmIpByAddress(String str);
	
	//根据id修改IP地址信息，修改信息录入到待审核处，原ip信息还未修改
	void updateUnconfirmIpById(UnconfirmIp unconfirmIp);
	
	//根据id删除IP地址信息，删除的ip地址录入到待审核处，审核完后才会修改
	void deleteUnconfirmIpById(Integer id);
}
