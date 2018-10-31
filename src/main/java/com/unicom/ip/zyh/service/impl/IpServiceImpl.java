package com.unicom.ip.zyh.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.unicom.ip.zyh.beans.HistoryIp;
import com.unicom.ip.zyh.beans.Ipform;
import com.unicom.ip.zyh.mapper.HistoryIpMapper;
import com.unicom.ip.zyh.mapper.IpformMapper;
import com.unicom.ip.zyh.service.IpService;

import org.springframework.stereotype.Service;
@Service
public class IpServiceImpl implements IpService{
@Autowired
private IpformMapper ipformMapper;
@Autowired
private HistoryIpMapper historyIpMapper;

	public List<Ipform> getAllIpform() {
		// TODO Auto-generated method stub
		//return ipformMapper.getAll();
		return ipformMapper.getAll(null);
	}

	public Map<String, Object> getAll(int page, int limit, Ipform ipform) {
			Map<String,Object> ipMap=new HashMap<String,Object>();
			System.out.println(ipform.getIpAddress());
			if(StringUtil.isNotEmpty(ipform.getIpAddress())) {
				ipMap.put("ipAddress", ipform.getIpAddress());
			}
			if(StringUtil.isNotEmpty(ipform.getIpVlan())) {
				ipMap.put("ipVlan", ipform.getIpVlan());
			}

	        PageHelper.startPage(page, limit);
	        List<Ipform> ipList=ipformMapper.getAll(ipMap);
	        //查看是否查询到数据库数据
	        System.out.println("************************************");
	        System.out.println(ipList.toString());
	        Iterator itr=ipList.iterator();
	        while(itr.hasNext())
	        	System.out.println(itr.next().toString());
	        System.out.println("************************************");
	        PageInfo<Ipform> pageInfo=new PageInfo<Ipform>(ipList);
	        Map<String,Object> map=new HashMap<String, Object>();

	        map.put("code",0);
	        map.put("msg","查询数据成功！");
	        map.put("count",pageInfo.getTotal());
	        map.put("data",pageInfo.getList());
	        return map;
	   
	}

	public void addIp(Ipform ipform) {
		ipformMapper.insertSelective(ipform);
		
	}

	public Ipform getIpformById(Integer id) {
		return ipformMapper.selectByPrimaryKey(id);
	}

	public Ipform getIpformByIpAddress(String address) {
		return ipformMapper.selectByIpAddress(address);
	}
	public void updateIpById(Ipform ipform) {
		ipformMapper.updateByPrimaryKey(ipform);
	}
	
	public void updateIpByIpAddressSelective(Ipform ipform) {
		ipformMapper.updateByIpAddressSelective(ipform);
	}

	public void deleteIpById(Integer id) {
		ipformMapper.deleteByPrimaryKey(id);
	}
	
	public void deleteIpByIpAddress(String address) {
		ipformMapper.deleteByIpAddress(address);
	}

	public Map<String, Object> getHistoryIpAll(int page, int limit, HistoryIp historyIp) {
		Map<String,Object> ipMap=new HashMap<String,Object>();
		System.out.println(historyIp.getIpAddress());
		if(StringUtil.isNotEmpty(historyIp.getIpAddress())) {
			ipMap.put("ipAddress", historyIp.getIpAddress());
		}

        PageHelper.startPage(page, limit);
        List<HistoryIp> ipList=historyIpMapper.getAll(ipMap);
        //查看是否查询到数据库数据
        System.out.println("************************************");
        System.out.println(ipList.toString());
        Iterator itr=ipList.iterator();
        while(itr.hasNext())
        	System.out.println(itr.next().toString());
        System.out.println("************************************");
        PageInfo<HistoryIp> pageInfo=new PageInfo<HistoryIp>(ipList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询数据成功！");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
	}

	 
}
