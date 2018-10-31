package com.unicom.ip.zyh.mapper;

import java.util.List;

import com.unicom.ip.zyh.beans.Profession;

public interface ProfessionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getAll();
}