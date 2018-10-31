package com.unicom.ip.zyh.mapper;

import java.util.List;

import com.unicom.ip.zyh.beans.Teacherstatus;

public interface TeacherstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacherstatus record);

    int insertSelective(Teacherstatus record);

    Teacherstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacherstatus record);

    int updateByPrimaryKey(Teacherstatus record);

    List<Teacherstatus> getAll();
}