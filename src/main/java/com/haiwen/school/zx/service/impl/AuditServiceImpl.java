package com.haiwen.school.zx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.Audit;
import com.haiwen.school.zx.beans.Course;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.beans.Teacher;
import com.haiwen.school.zx.mapper.*;
import com.haiwen.school.zx.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private AuditinfoMapper auditinfoMapper;

    @Autowired
    private CourseMapper courseMapper;
    public Map<String, Object> getAll(int page, int limit, Audit audit) {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(audit.getSendername())) {
            infoMap.put("sendername", audit.getSendername());
        }
        if(audit.getStatus()!=null){
            infoMap.put("status", audit.getStatus());
        }

        if(audit.getAudittype()!=null){
            infoMap.put("audittype", audit.getAudittype());
        }


        PageHelper.startPage(page, limit);
        List<Audit> userList=auditMapper.getAll(infoMap);
        PageInfo<Audit> pageInfo=new PageInfo<Audit>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public Audit getById(Integer id) {
        return auditMapper.selectByPrimaryKey(id);
    }

    public int upd(Audit audit ,int i,Integer courseId) {
        if (i==1){
            if(audit.getAudittype()==2){
                audit.setStatus(2);
                Teacher teacher=new Teacher();
                teacher.setUsername(audit.getSendername());
                teacher.setStatus(1);
                Logininfo logininfo=new Logininfo();
                logininfo.setPowerid(2);
                logininfo.setUsername(audit.getSendername());
                logininfoMapper.updByName(logininfo);
                teacherMapper.updByName(teacher);
                return auditMapper.updateByPrimaryKeySelective(audit);
            }else if(audit.getAudittype()==1){
                audit.setStatus(2);
                Course course = new Course();
                course.setStatusid(5);
                course.setId(courseId);
                Teacher teacher = teacherMapper.getByUsername(audit.getSendername());
                teacher.setCoursesum(teacher.getCoursesum()+1);
                teacherMapper.updateByPrimaryKeySelective(teacher);
                courseMapper.updateByPrimaryKeySelective(course);
                return auditMapper.updateByPrimaryKeySelective(audit);
            }else if(audit.getAudittype()==3){
                audit.setStatus(2);
                Teacher teacher=new Teacher();
                teacher.setUsername(audit.getSendername());
                teacher.setStatus(2);
                Logininfo logininfo=new Logininfo();
                logininfo.setPowerid(4);
                logininfo.setUsername(audit.getSendername());
                logininfoMapper.updByName(logininfo);
                teacherMapper.updByName(teacher);
                return auditMapper.updateByPrimaryKeySelective(audit);
            }
        }
        if(i==2){
            if(audit.getAudittype()==2){
                audit.setStatus(3);
                teacherMapper.deleteByUsername(audit.getSendername());
                return auditMapper.updateByPrimaryKeySelective(audit);
            }else if(audit.getAudittype()==1){
                audit.setStatus(3);
                Course course = new Course();
                course.setStatusid(4);
                course.setId(courseId);
                courseMapper.updateByPrimaryKeySelective(course);
                return auditMapper.updateByPrimaryKeySelective(audit);
            }else if(audit.getAudittype()==3){
                Teacher teacher=new Teacher();
                teacher.setUsername(audit.getSendername());
                teacher.setStatus(2);
                audit.setStatus(3);
                teacherMapper.updByName(teacher);
                return auditMapper.updateByPrimaryKeySelective(audit);
            }
        }
        return 0;
    }

    public int getCourse(int id) {
        return auditinfoMapper.getCourseByAd(id);
    }

    public Audit checkA(Audit audit) {
        return auditMapper.checkA(audit);
    }
}
