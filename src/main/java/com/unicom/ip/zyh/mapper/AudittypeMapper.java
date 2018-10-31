package com.unicom.ip.zyh.mapper;

import java.util.List;

import com.unicom.ip.zyh.beans.Audittype;

public interface AudittypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Audittype record);

    int insertSelective(Audittype record);

    Audittype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Audittype record);

    int updateByPrimaryKey(Audittype record);

    List<Audittype> getAll();

}