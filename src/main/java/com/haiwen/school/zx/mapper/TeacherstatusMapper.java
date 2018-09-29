package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Teacherstatus;

import java.util.List;

public interface TeacherstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacherstatus record);

    int insertSelective(Teacherstatus record);

    Teacherstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacherstatus record);

    int updateByPrimaryKey(Teacherstatus record);

    List<Teacherstatus> getAll();
}