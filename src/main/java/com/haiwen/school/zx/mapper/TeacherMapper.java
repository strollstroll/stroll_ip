package com.haiwen.school.zx.mapper;

import com.haiwen.school.zx.beans.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey();

    List<Teacher> getAll(Map<String,Object> map);

    Teacher getByUsername(String username);

    int updByName(Teacher record);
    int deleteByUsername(String username);

}