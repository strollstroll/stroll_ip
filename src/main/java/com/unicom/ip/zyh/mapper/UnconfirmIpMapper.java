package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.UnconfirmIp;

public interface UnconfirmIpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnconfirmIp record);

    int insertSelective(UnconfirmIp record);

    UnconfirmIp selectByPrimaryKey(Integer id);
    
    UnconfirmIp selectByIpAddress(String str);

    int updateByPrimaryKeySelective(UnconfirmIp record);

    int updateByPrimaryKey(UnconfirmIp record);

    //List<Ipform> getAll();
   // Ipform checkA(Ipform ipform);
   List<UnconfirmIp> getAll(Map<String,Object> map);
    
}
