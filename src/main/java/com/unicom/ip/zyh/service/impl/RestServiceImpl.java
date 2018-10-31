package com.unicom.ip.zyh.service.impl;

import com.unicom.ip.zyh.beans.*;
import com.unicom.ip.zyh.mapper.*;
import com.unicom.ip.zyh.service.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestServiceImpl implements RestService {
    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private ProfessionMapper professionMapper;

    @Autowired
    private TeacherstatusMapper teacherstatusMapper;

    @Autowired
    private AudittypeMapper audittypeMapper;

    @Autowired
    private AuditstatusMapper auditstatusMapper;

    public List<Status> getStatus() {
        return statusMapper.getAll();
    }


    public List<Teacherstatus> getTeacherStatus() {
        return teacherstatusMapper.getAll();
    }

    public List<Profession> getProfession() {
        return professionMapper.getAll();
    }

    public List<Audittype> getAudittype() {
        return audittypeMapper.getAll();
    }

    public List<Auditstatus> getAuditStatus() {
        return auditstatusMapper.getAll();
    }
}
