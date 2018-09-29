package com.haiwen.school.zx.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UserService;

@Controller
@RequestMapping("/ip")
public class IpformController {

	@Autowired
	private IpService ipService;
	
	@Autowired
    private UserService userService;
	
	//注册一个类型解析器将date类型输入到数据库中
	@org.springframework.web.bind.annotation.InitBinder
	 public void InitBinder(WebDataBinder binder){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }
	
	@RequestMapping("/toIpformList")
	public String toList1(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/ip-list";
	}
	
	//测试******************************************
	@RequestMapping("/toIpformList1")
	public String toList2(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/user1-list";
	}
	//测试结束******************************************
	@RequestMapping("/toIpformEdit")
	public String ipformEdit(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/ip-edit";
	}
	//获取IP信息列表
	@RequestMapping("/getIpformList")
	@ResponseBody
	public Map<String, Object> toIpInform(int page, int limit, Ipform ipform) {
		
		/*//获取所有IP信息数据
		request.setAttribute("ipform",ipService.getAllIpform());
		//return "ip/ipInformList";
		return "ip/user1-list";*/
		return ipService.getAll(page,limit,ipform);
	}
	
	//跳转到添加IP页面
	@RequestMapping("/addIp")
	public String addIp(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/ip-add";
	}
	//添加IP地址信息
	@RequestMapping("/doAdd")
	@ResponseBody
	public Integer addIpform(Ipform ipform) {
		Integer result=0;
        try{
            ipService.addIp(ipform);
        }catch(Exception e){
            result = -1;
        }
        return result;
	}
	
	
	//查看单条IP地址信息
	@RequestMapping("/toGetIpWatch")
	public String getIpWatch(Integer id,HttpServletRequest request) {
		Ipform ipform=new Ipform();
		ipform=ipService.getIpformById(id);
		request.setAttribute("ip",ipform);
		return "ip/ip-watch";
	}
	
	//跳转到修改页面，
	@RequestMapping("toEdit")
	public String toEdit(Integer id,HttpServletRequest request) {
		Ipform ipform=ipService.getIpformById(id);
		request.setAttribute("ip",ipform);
		return "ip/ip-edit";
	}
	
	//修改ip地址信息
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Integer updateIp(Ipform ipform) {
		Integer result=0;
		
		try {
			ipService.updateIpById(ipform);
		}catch(Exception e){
			result=-1;
		}
		return result;
	}
	
	//删除IP地址信息
	@RequestMapping("/toDelete")
	@ResponseBody
	public Map<String, Object> deleteIp(int page,int limit,Integer id,Ipform ipform) {
		ipService.deleteIpById(id);
		//return "ip/ip-list";
		return ipService.getAll(page,limit,ipform);
	}
}
