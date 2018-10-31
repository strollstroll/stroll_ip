package com.unicom.ip.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.Audit;


public interface AuditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Audit record);

    int insertSelective(Audit record);

    Audit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Audit record);

    int updateByPrimaryKey(Audit record);

    List<Audit> getAll(Map<String,Object> map);
    Audit checkA(Audit audit);
}