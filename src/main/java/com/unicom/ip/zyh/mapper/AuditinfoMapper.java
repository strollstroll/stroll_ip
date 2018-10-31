package com.unicom.ip.zyh.mapper;

import com.unicom.ip.zyh.beans.Auditinfo;

public interface AuditinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auditinfo record);

    int insertSelective(Auditinfo record);

    Auditinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auditinfo record);

    int updateByPrimaryKey(Auditinfo record);

    int getCourseByAd(int id);
}