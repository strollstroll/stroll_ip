package com.haiwen.school.zx.controller;

import com.haiwen.school.zx.beans.*;
import com.haiwen.school.zx.service.AuditService;
import com.haiwen.school.zx.service.CourseService;
import com.haiwen.school.zx.service.RestService;
import com.haiwen.school.zx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RestService restService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private CourseService courseService;
    @RequestMapping("/tolist")
    public String toList(HttpServletRequest request){
        request.setAttribute("teacherStatus",restService.getTeacherStatus());
        request.setAttribute("teacherType",restService.getProfession());
        return "user/teacher-list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(int page, int limit, Teacher teacher){
        return teacherService.getAll(page, limit, teacher);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,HttpServletRequest request){
//        根据session中的用户信息获取与之对应的教师资料
        Logininfo logininfo = (Logininfo) session.getAttribute("userInfo");
        Teacher teacher = teacherService.toUpd(logininfo.getUsername());
        request.setAttribute("teacherType",restService.getProfession());
        if(teacher !=null){
            request.setAttribute("teacher",teacher);
        }
        return "teacher/teacher-edit";
    }

    @RequestMapping("/doUpdate")//更新
    @ResponseBody
    public Map<String,Object> doUpdate(Teacher teacher){
        Map<String,Object> map=new HashMap<String, Object>();
        Audit audit = new Audit();
        audit.setStatus(1);
        audit.setAudittype(3);
        audit.setSendername(teacher.getUsername());
        audit = auditService.checkA(audit);
        if(audit==null){
            map.put("code",teacherService.doUpdate(teacher));
        }else {
            map.put("code",0);
            map.put("msg","当前已有求职申请待管理员审核，请勿重复操作谢谢");
        }

        return map;
    }

    @RequestMapping("/doGo")//离职
    @ResponseBody
    public Map<String,Object> doGo(Teacher teacher ,String resignation){
        Map<String,Object> map=new HashMap<String, Object>();
        Audit audit = new Audit();
        audit.setStatus(1);
        audit.setAudittype(3);
        audit.setSendername(teacher.getUsername());
        audit = auditService.checkA(audit);
        if(audit==null){
            map.put("code",teacherService.doGo(teacher,resignation));
        }else {
            map.put("code",0);
            map.put("msg","当前已有离职申请待管理员审核，请勿重复操作谢谢");
        }
        return map;
    }

    //    查看教师提出的申请
    @RequestMapping("toAudit")
    public String toAudit(HttpServletRequest request) {
        request.setAttribute("status",restService.getAuditStatus());
        request.setAttribute("type",restService.getAudittype());
        return "teacher/teacher-auditList";
    }
    @RequestMapping("/getAudit")
    @ResponseBody
    public Map<String,Object> getAudit(HttpSession session,int page, int limit,Audit audit){
        Logininfo logininfo = (Logininfo) session.getAttribute("userInfo");
        audit.setSendername(logininfo.getUsername());
        return auditService.getAll(page, limit, audit);
    }

    //    到计分列表
    @RequestMapping("toMyStudent")
    public  String toMyStudent(HttpSession session,HttpServletRequest request){
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        Teacher teacher = teacherService.toUpd(logininfo.getUsername());
        request.setAttribute("course",courseService.getByName(teacher.getId()));
        return "teacher/myStudent";
    }

    @RequestMapping("/getCreditList")
    @ResponseBody
    public Map<String,Object> getCreditList(HttpSession session,int page, int limit,Course course){
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        Teacher teacher = teacherService.toUpd(logininfo.getUsername());
        course.setTeacherid(teacher.getId());
        return courseService.getMyStudent(page,limit,course);
    }
    @RequestMapping("/docredit")
    @ResponseBody
    public Map<String,Object> docredit(Courseinfo courseinfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", courseService.docredit(courseinfo));
        return map;
    }

}
