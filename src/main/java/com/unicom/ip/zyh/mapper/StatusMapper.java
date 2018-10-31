package com.unicom.ip.zyh.mapper;

import java.util.List;

import com.unicom.ip.zyh.beans.Status;

public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);

    List<Status> getAll();
}