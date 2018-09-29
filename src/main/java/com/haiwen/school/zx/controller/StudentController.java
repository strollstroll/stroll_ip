package com.haiwen.school.zx.controller;

import com.haiwen.school.zx.beans.Course;
import com.haiwen.school.zx.beans.Courseinfo;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.beans.Student;
import com.haiwen.school.zx.service.CourseService;
import com.haiwen.school.zx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    //    跳转学生列表
    @RequestMapping("/tolist")
    public String toList() {
        return "user/student-list";
    }
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(int page, int limit, Student student) {
        return studentService.getAll(page, limit, student);
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session, HttpServletRequest request) {
//        根据session中的用户信息获取与之对应的教师资料
        Logininfo logininfo = (Logininfo) session.getAttribute("userInfo");
        Student student = studentService.toUpd(logininfo.getUsername());
        if (student != null) {
            request.setAttribute("student", student);
        }
        return "student/student-edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public Map<String,Object> doUpdate(Student student){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",studentService.doUpdate(student));
        return map;
    }
    @RequestMapping("doEnter")
    @ResponseBody
    public Map<String, Object> doEnter(HttpSession session,Course course) {
        Map<String, Object> map = new HashMap<String, Object>();
        Courseinfo courseinfo = new Courseinfo();
        courseinfo.setCourseid(course.getId());
        Logininfo logininfo = (Logininfo) session.getAttribute("userInfo");
        Student student = studentService.toUpd(logininfo.getUsername());
        courseinfo.setStuid(student.getId());
        map.put("code",courseService.addCourseInfo(course, courseinfo));
        return map;
    }
}