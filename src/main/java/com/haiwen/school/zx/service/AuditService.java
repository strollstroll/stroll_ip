package com.haiwen.school.zx.service;

import com.haiwen.school.zx.beans.Audit;

import java.util.Map;

public interface AuditService {
    Map<String, Object> getAll(int page, int limit, Audit audit);

    Audit getById(Integer id);

    int upd(Audit audit , int i,Integer courseId);

    int getCourse(int id);

    Audit checkA(Audit audit);
}
