package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Audit;

import java.util.List;
import java.util.Map;


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