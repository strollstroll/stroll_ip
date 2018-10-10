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
import com.haiwen.school.zx.beans.UnconfirmIp;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UnconfirmIpService;
import com.haiwen.school.zx.service.UserService;

@Controller
@RequestMapping("/employeeIp")
public class EmployeeIpController {
	@Autowired
	private IpService ipService;
	@Autowired
	private UnconfirmIpService unconfirmIpService;
	@Autowired
    private UserService userService;
	
	//注册一个类型解析器将date类型输入到数据库中
	@org.springframework.web.bind.annotation.InitBinder
	 public void InitBinder(WebDataBinder binder){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }
	
	@RequestMapping("/toEmployeeIpList")
	public String toList1(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/ip-list";
	}
	
	@RequestMapping("/toUnconfirmIpEdit")
	public String employeeIpEdit(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/ip-edit";
	}
	
	//跳转到添加IP页面
	@RequestMapping("/addUnconfirmIp")
	public String addIp(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/ip-add";
	}
	//添加IP地址信息
	@RequestMapping("/doAdd")
	@ResponseBody
	public Integer addIpform(UnconfirmIp unconfirmIp) {
		unconfirmIp.setUnconfirmStatus("添加待审核");
		Integer result=0;
        try{
            unconfirmIpService.addUnconfirmIp(unconfirmIp);
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
		return "employee/ip-edit";
	}
	
	//将修改ip地址信息添加到待审核表中的，员ip表的中内容不变，审核完之后才发生变化
	@RequestMapping("/doUpdateAddUnconfirmIp")
	@ResponseBody
	public Integer UpdateIpAddUnconfirmIp(Ipform ipform) {
		
		//将更改的ip信息插入到待审核表中
		UnconfirmIp unconfirmIp=new UnconfirmIp();
		unconfirmIp.setIpAddress(ipform.getIpAddress());
		unconfirmIp.setIpStatus(ipform.getIpStatus());
		unconfirmIp.setIpRemarks(ipform.getIpRemarks());
		unconfirmIp.setIpSubnetmask(ipform.getIpSubnetmask());
		unconfirmIp.setIpAddressnumber(ipform.getIpAddressnumber());
		unconfirmIp.setIpUsetime(ipform.getIpUsetime());
		unconfirmIp.setIpUsername(ipform.getIpUsername());
		unconfirmIp.setIpVlan(ipform.getIpVlan());
		unconfirmIp.setIpConnectingdevice(ipform.getIpConnectingdevice());
		unconfirmIp.setIpPort(ipform.getIpPort());
		unconfirmIp.setIpRate(ipform.getIpRate());
		unconfirmIp.setIpAttribution(ipform.getIpAttribution());
		unconfirmIp.setIpBroadbandacceptancenumber(ipform.getIpBroadbandacceptancenumber());
		unconfirmIp.setIpSnnumber(ipform.getIpSnnumber());
		unconfirmIp.setIpOltaddress(ipform.getIpOltaddress());
		unconfirmIp.setIpIomusername(ipform.getIpIomusername());
		unconfirmIp.setIpInstalledaddress(ipform.getIpInstalledaddress());
		unconfirmIp.setIpType(ipform.getIpType());
		unconfirmIp.setIpWotvbssremarks(ipform.getIpWotvbssremarks());
		unconfirmIp.setIpOutputrate(ipform.getIpOutputrate());
		unconfirmIp.setIpInputrate(ipform.getIpInputrate());
		unconfirmIp.setIpTerminalnumber(ipform.getIpTerminalnumber());
		//审核状态说明
		unconfirmIp.setUnconfirmStatus("修改待审核");
		
		Integer result=0;
		
		try {
			unconfirmIpService.addUnconfirmIp(unconfirmIp);
			//在ipform表中将该ip的审核状态变为1（审核中）表示修改待审核
			ipform.setApprovalStatus(1);
			ipService.updateIpById(ipform);
		}catch(Exception e){
			result=-1;
		}
		return result;
	}
	
	//删除IP地址信息
	@RequestMapping("/toDelete")
	@ResponseBody
	public Map<String, Object> deleteIp(int page,int limit,Integer id,Ipform ip) {
		Ipform ipform=ipService.getIpformById(id);
		//将更改的ip信息插入到待审核表中
				UnconfirmIp unconfirmIp=new UnconfirmIp();
				unconfirmIp.setIpAddress(ipform.getIpAddress());
				unconfirmIp.setIpStatus(ipform.getIpStatus());
				unconfirmIp.setIpRemarks(ipform.getIpRemarks());
				unconfirmIp.setIpSubnetmask(ipform.getIpSubnetmask());
				unconfirmIp.setIpAddressnumber(ipform.getIpAddressnumber());
				unconfirmIp.setIpUsetime(ipform.getIpUsetime());
				unconfirmIp.setIpUsername(ipform.getIpUsername());
				unconfirmIp.setIpVlan(ipform.getIpVlan());
				unconfirmIp.setIpConnectingdevice(ipform.getIpConnectingdevice());
				unconfirmIp.setIpPort(ipform.getIpPort());
				unconfirmIp.setIpRate(ipform.getIpRate());
				unconfirmIp.setIpAttribution(ipform.getIpAttribution());
				unconfirmIp.setIpBroadbandacceptancenumber(ipform.getIpBroadbandacceptancenumber());
				unconfirmIp.setIpSnnumber(ipform.getIpSnnumber());
				unconfirmIp.setIpOltaddress(ipform.getIpOltaddress());
				unconfirmIp.setIpIomusername(ipform.getIpIomusername());
				unconfirmIp.setIpInstalledaddress(ipform.getIpInstalledaddress());
				unconfirmIp.setIpType(ipform.getIpType());
				unconfirmIp.setIpWotvbssremarks(ipform.getIpWotvbssremarks());
				unconfirmIp.setIpOutputrate(ipform.getIpOutputrate());
				unconfirmIp.setIpInputrate(ipform.getIpInputrate());
				unconfirmIp.setIpTerminalnumber(ipform.getIpTerminalnumber());
				//审核状态说明
				unconfirmIp.setUnconfirmStatus("删除待审核");	
				//在ipform表中将该ip的审核状态变为2（审核中）表示删除待审核
				ipform.setApprovalStatus(2);
				ipService.updateIpById(ipform);
		unconfirmIpService.addUnconfirmIp(unconfirmIp);

		//return "ip/ip-list";
		return ipService.getAll(page,limit,ip);
	}
	
}
