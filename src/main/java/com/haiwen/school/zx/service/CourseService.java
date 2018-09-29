package com.haiwen.school.zx.service;

import com.haiwen.school.zx.beans.Course;
import com.haiwen.school.zx.beans.Courseinfo;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.beans.Teacher;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Map<String, Object> getAll(int page, int limit, Course course, Courseinfo courseinfo);

    Course getById(Integer id);

    int addCourse(Course course, Teacher teacher);

    int getSum(Course course);

    int getEnterSum(Courseinfo course);

    int addCourseInfo(Course course,Courseinfo courseinfo);

    int openCourse();

    int doClose(Course course);

    List<Course> getByName(int id);

    Map<String, Object> getMyStudent(int page, int limit, Course course);

    int docredit(Courseinfo courseinfo);

}
