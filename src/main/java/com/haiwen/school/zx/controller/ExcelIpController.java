package com.haiwen.school.zx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haiwen.school.zx.beans.ExcelIp;
import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.service.ExcelIpService;
import com.haiwen.school.zx.service.UserService;

@Controller
@RequestMapping("/excelIp/*")
public class ExcelIpController {

	@Autowired
	private ExcelIpService excelIpService;
	
	@Autowired
	private UserService userService;

    @ResponseBody  
    @RequestMapping(value="ajaxUpload.do",method={RequestMethod.GET,RequestMethod.POST})  
    public String ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        return excelIpService.ajaxUploadExcel(request, response);
    } 

    
    @RequestMapping("/importExcel")
    private String importExcelIp(HttpServletRequest request) {
    	return "/ip/ip-importExcel";
    }
    
    

	
  //获取excel批量导入后的数据  
	@RequestMapping("/getExcelIpList")
	@ResponseBody
	public Map<String, Object> toIpInform(int page, int limit, ExcelIp excelIp) {
		return excelIpService.getAll(page,limit,excelIp);
	}
}
