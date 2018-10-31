package com.unicom.ip.zyh.service;

import com.unicom.ip.zyh.beans.Student;

import java.util.Map;

public interface StudentService {
     Map<String, Object> getAll(int page, int limit, Student student);

     Student toUpd(String username);

     int doUpdate(Student student);
}
