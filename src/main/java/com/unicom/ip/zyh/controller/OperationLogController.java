package com.unicom.ip.zyh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.ip.zyh.beans.OperationLog;
import com.unicom.ip.zyh.service.OperationLogService;
import com.unicom.ip.zyh.service.UserService;

@Controller
@RequestMapping("/log")
public class OperationLogController {

	@Autowired
	UserService userService;
	@Autowired
	OperationLogService operationLogService;
	
	@RequestMapping("/toOperationLogList")
	public String toOperationLogList(HttpServletRequest request) {
		request.setAttribute("power",userService.getPower());
		return "/ip/ip-log";
	}
	
	//获取操作日志列表
	@RequestMapping("/getOperationLogList")
	@ResponseBody
	public Map<String, Object> getOperationLogList(int page, int limit, OperationLog operationLog) {
		return operationLogService.getAll(page,limit,operationLog);
	}
	
	
}
