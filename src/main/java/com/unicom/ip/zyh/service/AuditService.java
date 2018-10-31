package com.unicom.ip.zyh.service;

import java.util.Map;

import com.unicom.ip.zyh.beans.Audit;

public interface AuditService {
    Map<String, Object> getAll(int page, int limit, Audit audit);

    Audit getById(Integer id);

    int upd(Audit audit , int i,Integer courseId);

    int getCourse(int id);

    Audit checkA(Audit audit);
}
