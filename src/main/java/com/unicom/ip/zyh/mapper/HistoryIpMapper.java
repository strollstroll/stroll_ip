package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.HistoryIp;

public interface HistoryIpMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByIpAddress(String record);

    int insert(HistoryIp record);

    int insertSelective(HistoryIp record);

    HistoryIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoryIp record);
    
    int updateByIpAddressSelective(HistoryIp record);

    int updateByPrimaryKey(HistoryIp record);
    
    HistoryIp selectByIpAddress(String record);

    List<HistoryIp> getAll(Map<String,Object> map);
}
