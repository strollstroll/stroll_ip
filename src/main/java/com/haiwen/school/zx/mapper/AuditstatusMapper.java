package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Auditstatus;

import java.util.List;

public interface AuditstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auditstatus record);

    int insertSelective(Auditstatus record);

    Auditstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auditstatus record);

    int updateByPrimaryKey(Auditstatus record);

    List<Auditstatus> getAll();
}