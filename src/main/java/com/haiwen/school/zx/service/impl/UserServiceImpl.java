package com.haiwen.school.zx.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.beans.Power;
import com.haiwen.school.zx.mapper.LogininfoMapper;
import com.haiwen.school.zx.mapper.PowerMapper;
import com.haiwen.school.zx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private PowerMapper powerMapper;
    public Logininfo doLogin(Logininfo record) {
        return logininfoMapper.doLogin(record);
    }

    public Logininfo checkName(Logininfo record) {
        return logininfoMapper.checkName(record);
    }

    public int doUpdate(Logininfo record) {
        if(record.getId()==null){
            record.setPowerid(5);
            return logininfoMapper.insertSelective(record);
        }
        return logininfoMapper.updateByPrimaryKeySelective(record);
    }

    public Logininfo getById(int id) {
        return logininfoMapper.selectByPrimaryKey(id);
    }

    public List<Power> getPower() {
        return powerMapper.getAll();
    }

    public Map<String, Object> getAll(int page, int limit, Logininfo logininfo) {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if(StringUtil.isNotEmpty(logininfo.getUsername())){
            infoMap.put("username", logininfo.getUsername());
        }

        if(logininfo.getPowerid()!=null){
            infoMap.put("powerid", logininfo.getPowerid());
        }


        PageHelper.startPage(page, limit);
        List<Logininfo> userList=logininfoMapper.getAll(infoMap);
        PageInfo<Logininfo> pageInfo=new PageInfo<Logininfo>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

}

