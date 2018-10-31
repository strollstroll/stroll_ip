package com.unicom.ip.zyh.controller;

import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.service.IpService;
import com.unicom.ip.zyh.service.StudentService;
import com.unicom.ip.zyh.service.TeacherService;
import com.unicom.ip.zyh.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;
    //*************************
    private IpService staffService;
    @RequestMapping("/toIpList")
    public String toIpList(HttpServletRequest request){
        request.setAttribute("power",userService.getPower());
        return "user/user-list";
    }
    
    //**************************
    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(Logininfo user, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        user=userService.checkName(user);
        if(user==null){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        return map;
    }
    @RequestMapping("/doupdate")
    @ResponseBody
    public Map<String,Object> doupdate(Logininfo user,HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", userService.doUpdate(user));
        session.invalidate();
        return map;
    }

    @RequestMapping("/exit")
    public String doExit(HttpSession session){
        session.invalidate();
        return "redirect://";
    }

    @RequestMapping("/tolist")
    public String toList(HttpServletRequest request){
        request.setAttribute("power",userService.getPower());
        return "user/user-list";
    }
    
    @RequestMapping("/tolist1")
    public String toList1(HttpServletRequest request){
        request.setAttribute("power",userService.getPower());
        return "user/user1-list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(int page, int limit, Logininfo logininfo){
        return userService.getAll(page,limit,logininfo);
    }

    @RequestMapping("toedit")
    public String toEdit(HttpSession session){
        Logininfo logininfo = (Logininfo) session.getAttribute("userInfo");
        if(logininfo.getPowerid()==2){
            return "redirect:/teacher/toEdit.do";
        }
        return "redirect:/student/toEdit.do";
    }

    @RequestMapping("/explicit")
    public String explicit(HttpServletRequest request, Integer id) {
        Logininfo logininfo=userService.getById(id);
//        教师
        request.setAttribute("login",logininfo);
        if(logininfo.getPowerid()==2)request.setAttribute("teacher",teacherService.toUpd(logininfo.getUsername()));

//        学生
        if(logininfo.getPowerid()==3)request.setAttribute("student",studentService.toUpd(logininfo.getUsername()));

        return "user/explicit";
    }

}
