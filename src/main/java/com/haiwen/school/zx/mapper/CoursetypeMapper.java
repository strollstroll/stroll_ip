package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Coursetype;

import java.util.List;

public interface  CoursetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coursetype record);

    int insertSelective(Coursetype record);

    Coursetype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coursetype record);

    int updateByPrimaryKey(Coursetype record);

    List<Coursetype> getAll();
}