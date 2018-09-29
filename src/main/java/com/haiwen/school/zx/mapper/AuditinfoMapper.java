package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Auditinfo;

public interface AuditinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auditinfo record);

    int insertSelective(Auditinfo record);

    Auditinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auditinfo record);

    int updateByPrimaryKey(Auditinfo record);

    int getCourseByAd(int id);
}