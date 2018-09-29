package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Status;

import java.util.List;

public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);

    List<Status> getAll();
}