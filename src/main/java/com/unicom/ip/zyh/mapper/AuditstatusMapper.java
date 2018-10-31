package com.unicom.ip.zyh.mapper;

import java.util.List;

import com.unicom.ip.zyh.beans.Auditstatus;

public interface AuditstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auditstatus record);

    int insertSelective(Auditstatus record);

    Auditstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auditstatus record);

    int updateByPrimaryKey(Auditstatus record);

    List<Auditstatus> getAll();
}