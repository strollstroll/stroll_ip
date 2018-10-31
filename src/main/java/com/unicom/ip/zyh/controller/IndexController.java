package com.unicom.ip.zyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.service.OperationLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private OperationLogService operationLogService;
    @RequestMapping("/index")
    public String toIndex(HttpSession session, HttpServletRequest request){
        Logininfo logininfo= (Logininfo) session.getAttribute("userInfo");
        if(logininfo==null){
            request.setAttribute("msg","请登录后访问本管理系统，谢谢！");
        }else if(logininfo.getPowerid()==4){
            request.setAttribute("msg","当前账号被禁用，谢谢！");
        }
        else {
            int powerid = logininfo.getPowerid();
            //添加到日志
            operationLogService.addOperationLog(session, "登录", "登录成功");
            if (powerid==1)return "admin/admin-index";
            if (powerid==2)return "admin/auditor-index";
        }
        //添加到日志
        operationLogService.addOperationLog(session, "登录", "登录成功");
        return "admin/employee-index";
    }

    @RequestMapping("/welcome")
    public String toWelcom(){
        return "admin/welcome";
    }
}
