package com.haiwen.school.zx.controller;

import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.service.UserService;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doLogin")
    @ResponseBody
    public Map<String,Object> doLogin(HttpSession session,Logininfo logininfo){
        Map<String,Object> map=new HashMap<String, Object>();
        Logininfo user=userService.doLogin(logininfo);
        if(user!=null){
            session.setAttribute("userInfo",user);
            map.put("code",1);
        }else {
            map.put("code",0);
        }
        return map;
    }
    @RequestMapping("/toedit")
    public String toedit(Logininfo user, HttpServletRequest request){
        if(user.getId()!=null){
            request.setAttribute("user",userService.getById(user.getId()));
        }
        return "user/user-deit";
    }

}
