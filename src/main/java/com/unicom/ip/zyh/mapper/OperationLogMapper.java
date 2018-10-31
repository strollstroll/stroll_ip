package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.OperationLog;

public interface OperationLogMapper {

      int deleteByPrimaryKey(Integer id);
      
      int insert(OperationLog record);

      int insertSelective(OperationLog record);

      OperationLog selectByPrimaryKey(Integer id);

      int updateByPrimaryKeySelective(OperationLog record);
      
      int updateByPrimaryKey(OperationLog record);
      
      List<OperationLog> getAll(Map<String,Object> map);
      
}
