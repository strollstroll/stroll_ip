package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.ExcelIp;

public interface ExcelIpMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByIpAddress(String record);

    int insert(ExcelIp record);

    int insertSelective(ExcelIp record);

    ExcelIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExcelIp record);
    
    int updateByIpAddressSelective(ExcelIp record);

    int updateByPrimaryKey(ExcelIp record);
    
    ExcelIp selectByIpAddress(String record);

    List<ExcelIp> getAll(Map<String,Object> map);
}
