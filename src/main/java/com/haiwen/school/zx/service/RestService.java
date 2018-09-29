package com.haiwen.school.zx.service;

import com.haiwen.school.zx.beans.*;

import java.util.List;

public interface RestService {

    List<Status> getStatus();


    List<Teacherstatus> getTeacherStatus();

    List<Profession> getProfession();

    List<Audittype> getAudittype();

    List<Auditstatus> getAuditStatus();
}
