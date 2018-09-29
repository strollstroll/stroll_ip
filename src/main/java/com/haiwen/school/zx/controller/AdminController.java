package com.haiwen.school.zx.controller;

import com.haiwen.school.zx.beans.Audit;

import com.haiwen.school.zx.service.AuditService;
import com.haiwen.school.zx.service.CourseService;
import com.haiwen.school.zx.service.RestService;
import com.haiwen.school.zx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RestService restService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;
    //******************************************
   
    
    //******************************************
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("status",restService.getAuditStatus());
        request.setAttribute("type",restService.getAudittype());
        return "admin/audit-list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getPage(int page, int limit, Audit audit){
        return  auditService.getAll(page ,limit,audit);
    }

    @RequestMapping("/toEdit")
    public String toEdit(Integer id,HttpServletRequest request){
        Audit audit=auditService.getById(id);
            request.setAttribute("audit",audit);
            if (audit.getAudittype()==1){
               request.setAttribute("course",courseService.getById(auditService.getCourse(id)));
            }else {
                request.setAttribute("user",teacherService.toUpd(audit.getSendername()));
            }
            return "admin/audit-edit";
    }

    @RequestMapping("/doagree")
    @ResponseBody
    public Map<String,Object> doagree(Audit audit,Integer courseId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", auditService.upd(audit, 1,courseId));
        return map;
    }
    @RequestMapping("/dorefuse")
    @ResponseBody
    public Map<String,Object> dorefuse(Audit audit,Integer courseId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", auditService.upd(audit, 2,courseId));
        return map;
    }
}
