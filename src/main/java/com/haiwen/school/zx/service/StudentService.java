package com.haiwen.school.zx.service;

import com.haiwen.school.zx.beans.Student;

import java.util.Map;

public interface StudentService {
     Map<String, Object> getAll(int page, int limit, Student student);

     Student toUpd(String username);

     int doUpdate(Student student);
}
