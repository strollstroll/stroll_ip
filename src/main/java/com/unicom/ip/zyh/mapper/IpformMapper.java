package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.Ipform;

public interface IpformMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByIpAddress(String record);

    int insert(Ipform record);

    int insertSelective(Ipform record);

    Ipform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ipform record);
    
    int updateByIpAddressSelective(Ipform record);

    int updateByPrimaryKey(Ipform record);
    
    Ipform selectByIpAddress(String record);

   List<Ipform> getAll(Map<String,Object> map);
    
  
}
