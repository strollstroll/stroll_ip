package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Logininfo;

import java.util.List;
import java.util.Map;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logininfo record);

    int insertSelective(Logininfo record);

    Logininfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logininfo record);

    int updateByPrimaryKey(Logininfo record);

    Logininfo doLogin(Logininfo record);

    Logininfo checkName(Logininfo record);

    List<Logininfo> getAll(Map<String,Object> map);

    int updByName(Logininfo logininfo);
}