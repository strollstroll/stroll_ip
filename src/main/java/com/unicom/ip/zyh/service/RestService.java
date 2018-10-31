package com.unicom.ip.zyh.service;

import java.util.List;

import com.unicom.ip.zyh.beans.*;

public interface RestService {

    List<Status> getStatus();


    List<Teacherstatus> getTeacherStatus();

    List<Profession> getProfession();

    List<Audittype> getAudittype();

    List<Auditstatus> getAuditStatus();
}
