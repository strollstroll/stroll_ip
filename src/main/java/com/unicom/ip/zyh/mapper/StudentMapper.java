package com.unicom.ip.zyh.mapper;

import com.unicom.ip.zyh.beans.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> getAll(Map<String,Object> map);

    Student getByUsername(String username);

}