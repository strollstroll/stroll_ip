package com.unicom.ip.zyh.mapper;

import com.unicom.ip.zyh.beans.Coursetype;

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