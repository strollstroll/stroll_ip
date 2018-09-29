package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Audittype;

import java.util.List;

public interface AudittypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Audittype record);

    int insertSelective(Audittype record);

    Audittype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Audittype record);

    int updateByPrimaryKey(Audittype record);

    List<Audittype> getAll();

}