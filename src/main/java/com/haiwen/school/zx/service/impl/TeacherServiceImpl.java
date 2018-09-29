package com.haiwen.school.zx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.Audit;
import com.haiwen.school.zx.beans.Teacher;
import com.haiwen.school.zx.mapper.AuditMapper;
import com.haiwen.school.zx.mapper.TeacherMapper;
import com.haiwen.school.zx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private AuditMapper auditMapper;
    public Map<String, Object> getAll(int page, int limit, Teacher teacher){
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if(StringUtil.isNotEmpty(teacher.getTeachername())){
            infoMap.put("teachername", teacher.getTeachername());
        }
        if(teacher.getStatus()!=null){
            infoMap.put("status", teacher.getStatus());
        }

        if(teacher.getProfession()!=null){
            infoMap.put("profession", teacher.getProfession());
        }

        PageHelper.startPage(page, limit);
        List<Teacher> userList=teacherMapper.getAll(infoMap);
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public Teacher toUpd(String username) {
        return teacherMapper.getByUsername(username);
    }

    public int doUpdate(Teacher teacher) {
        if (teacher.getId() == null){
            Audit audit=new Audit();
            audit.setAudittype(2);
            audit.setStatus(1);
            audit.setSendername(teacher.getUsername());
            audit.setRemake(teacher.getUsername()+"申请成为教师");
            auditMapper.insertSelective(audit);
            teacher.setStatus(3);
            return teacherMapper.insertSelective(teacher);
        }
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public int doGo(Teacher teacher, String resignation) {
        Audit audit=new Audit();
        audit.setAudittype(3);
        audit.setStatus(1);
        audit.setSendername(teacher.getUsername());
        audit.setRemake(resignation);
        teacher.setStatus(3);
        auditMapper.insertSelective(audit);
        return teacherMapper.updateByPrimaryKeySelective(teacher);

    }
}
