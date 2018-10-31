package com.unicom.ip.zyh.service;

import com.unicom.ip.zyh.beans.Course;
import com.unicom.ip.zyh.beans.Courseinfo;
import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.beans.Teacher;

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
