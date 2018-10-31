package com.unicom.ip.zyh.mapper;


import com.unicom.ip.zyh.beans.Course;
import com.unicom.ip.zyh.beans.Courseinfo;

import java.util.List;


public interface CourseinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Courseinfo record);

    int insertSelective(Courseinfo record);

    Courseinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Courseinfo record);

    int updateByPrimaryKey(Courseinfo record);
//查看学生正在报名或者开课的数量
    int getEnterSum(Courseinfo record);

    List<Courseinfo>getMyStudent(Course course);

}