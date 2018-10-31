package com.unicom.ip.zyh.service;

import com.unicom.ip.zyh.beans.Teacher;

import java.util.Map;

public interface TeacherService {

     Map<String,Object> getAll(int page, int limit, Teacher teacher);
     Teacher toUpd(String username);
     int doUpdate(Teacher teacher);
     int doGo(Teacher teacher,String resignation);
}
