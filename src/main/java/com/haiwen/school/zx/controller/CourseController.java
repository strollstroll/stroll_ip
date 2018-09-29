package com.haiwen.school.zx.controller;

import com.haiwen.school.zx.beans.*;
import com.haiwen.school.zx.service.CourseService;
import com.haiwen.school.zx.service.RestService;
import com.haiwen.school.zx.service.StudentService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private RestService restService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("type",restService.getProfession());
        request.setAttribute("status",restService.getStatus());
        return "course/course-list";

    }
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getPage(HttpSession session,int page, int limit, Course course){
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
//        判断用户权限  一旦为2（教师） 则只查找自己名下的课程
        if(logininfo.getPowerid()==2){
            course.setTeacherid(teacherService.toUpd(logininfo.getUsername()).getId());
        }else if(logininfo.getPowerid()==3||logininfo.getPowerid()==5){//一旦为3（学生） 只能查可选课程
            course.setStatusid(5);
        }
        return  courseService.getAll(page ,limit,course,new Courseinfo());
    }
//教师课程列表跳转
    @RequestMapping("/toTeacherCourse")
    public String toTeacherCourse(){
        return "course/teacherCourse";
    }
//    学生课程列表跳转
    @RequestMapping("/toStudentCourse")
    public  String toStudentCourse(HttpServletRequest request){
        request.setAttribute("type",restService.getProfession());
        return "course/studentCourse";
    }

    @RequestMapping("toedit")
    public String toedit(HttpSession session,HttpServletRequest request,Integer id){
        if(id!=null){
            request.setAttribute("course",courseService.getById(id));
        }else {
            Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
            Teacher teacher = teacherService.toUpd(logininfo.getUsername());
            Course course = new Course();
            course.setTeacherid(teacher.getId());
            /*当教师开课数或待审核超过5时提醒教师不可继续开课*/
            if(courseService.getSum(course)>5) request.setAttribute("msgMax","当前开课与待审核已达上限，请安心上课");
        }

        return "course/course-edit";
    }

    @RequestMapping("doUpdate")
    @ResponseBody
    public Map<String,Object> doUpdate(Course course,HttpSession session){
//        获取教师资料  将教师id 存入 课程对象中
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        Teacher teacher = teacherService.toUpd(logininfo.getUsername());
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("code", courseService.addCourse(course, teacher));
        return map;
    }
//学生报名页面跳转
    @RequestMapping("enter")
    public String enter(HttpServletRequest request ,Integer id){
        request.setAttribute("course",courseService.getById(id));
        return "student/studentEnter";
    }

    @RequestMapping("check")
    @ResponseBody
    public Map<String,Object> check(HttpSession session,Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        Student student = studentService.toUpd(logininfo.getUsername());
        Courseinfo courseinfo = new Courseinfo();
        if(student==null){
            map.put("hello","需完善个人资料");
            return map;
        }
        courseinfo.setStuid(student.getId());
        //判断学生课程报名上限
        if(courseService.getEnterSum(courseinfo)>5) map.put("msgMax","报名上限");
        //判断用户当先课程已选
        courseinfo.setCourseid(id);
        if(courseService.getEnterSum(courseinfo)>0) map.put("msgMax","已报名");
        return map;
    }
    @RequestMapping("/toStuOld")
    public String toStuOld(HttpServletRequest request){
        request.setAttribute("type",restService.getProfession());
        request.setAttribute("status",restService.getStatus());
        return "student/studentOld";
    }

    @RequestMapping("/doStuOld")
    @ResponseBody
    public Map<String,Object> doStuOld(HttpSession session,int page, int limit,Course course){
        Map<String, Object> map = new HashMap<String, Object>();
        Courseinfo courseinfo = new Courseinfo();
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        Student student = studentService.toUpd(logininfo.getUsername());
        if (student==null){
            map.put("code",1);
            map.put("msg","查询不到数据");
            return map;
        }
        courseinfo.setStuid(student.getId());
        return courseService.getAll(page,limit,course,courseinfo);
    }
    @RequestMapping("/openCourse")
    @ResponseBody
    public Map<String,Object> openCourse(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", courseService.openCourse());
        return map;
    }
//结课
    @RequestMapping("/doClose")
    @ResponseBody
    public Map<String,Object> doClose(Course course){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", courseService.doClose(course));
        return map;
    }

}
