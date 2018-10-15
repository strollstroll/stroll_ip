package com.haiwen.school.zx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.UnconfirmIp;
import com.haiwen.school.zx.mapper.UnconfirmIpMapper;
import com.haiwen.school.zx.service.AuditorIpService;
@Service
public class AuditorIpServiceImpl implements AuditorIpService{
	@Autowired
	private UnconfirmIpMapper unconfirmIpMapper;
	public Map<String, Object> getAll(int page, int limit, UnconfirmIp unconfirmIp) {
		  Map<String,Object> ipMap=new HashMap<String,Object>();
			System.out.println(unconfirmIp.getIpAddress());
			if(StringUtil.isNotEmpty(unconfirmIp.getIpAddress())) {
				ipMap.put("ipAddress", unconfirmIp.getIpAddress());
			}
	      PageHelper.startPage(page, limit);
	      List<UnconfirmIp> ipList=unconfirmIpMapper.getAll(ipMap);
	      
	      PageInfo<UnconfirmIp> pageInfo=new PageInfo<UnconfirmIp>(ipList);
	      Map<String,Object> map=new HashMap<String, Object>();

	      map.put("code",0);
	      map.put("msg","查询数据成功！");
	      map.put("count",pageInfo.getTotal());
	      map.put("data",pageInfo.getList());
	      return map;
	}

}
