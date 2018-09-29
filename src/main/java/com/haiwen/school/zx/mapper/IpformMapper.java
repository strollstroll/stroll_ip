package com.haiwen.school.zx.mapper;

import java.util.List;
import java.util.Map;

import com.haiwen.school.zx.beans.Ipform;

public interface IpformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ipform record);

    int insertSelective(Ipform record);

    Ipform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ipform record);

    int updateByPrimaryKey(Ipform record);

    //List<Ipform> getAll();
   // Ipform checkA(Ipform ipform);
   List<Ipform> getAll(Map<String,Object> map);
    
  
}
