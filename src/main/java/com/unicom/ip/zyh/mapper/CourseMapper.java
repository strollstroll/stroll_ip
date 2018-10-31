package com.unicom.ip.zyh.mapper;

import com.unicom.ip.zyh.beans.Course;
import com.unicom.ip.zyh.beans.Courseinfo;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getAll(Map<String,Object> map);

    int getSum(Course course);
//改变教师状态
    int updByTeacher(Course course);

    // 获取学生已选课程
    List<Course> getCourseByStu(Map<String,Object> map);

    //    开课
    int openCourse(Course course);
    Course getById(Integer id);

    List<Course> getByName(int teacherid);


}