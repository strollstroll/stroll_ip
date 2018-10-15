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

import com.haiwen.school.zx.beans.HistoryIp;
import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.beans.UnconfirmIp;
import com.haiwen.school.zx.mapper.HistoryIpMapper;
import com.haiwen.school.zx.service.AuditorIpService;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UnconfirmIpService;
import com.haiwen.school.zx.service.UserService;
import com.haiwen.school.zx.util.HistoryIpAddUtil;
/**
 *面对管理审核员 
 */
@Controller
@RequestMapping("/auditorIp")
public class AuditorIpController {

	@Autowired
	private IpService ipService;
	@Autowired
	private UnconfirmIpService unconfirmIpService;
	@Autowired
	private AuditorIpService auditorIpService;
	@Autowired
    private UserService userService;
	@Autowired
	private HistoryIpMapper historyIpMapper;
	//注册一个类型解析器将date类型输入到数据库中
	@org.springframework.web.bind.annotation.InitBinder
	 public void InitBinder(WebDataBinder binder){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }
	//跳转到IP列表
	@RequestMapping("/toIpformList")
	public String toList1(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/ip-list";
	}
	
	//获取IP信息列表
	@RequestMapping("/getIpformList")
	@ResponseBody
	public Map<String, Object> toIpInform(int page, int limit, Ipform ipform) {
		return ipService.getAll(page,limit,ipform);
	}
	
	//跳转到待审核ip列表页面
	@RequestMapping("/toUnconfirmIpList")
	public String toList(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "auditor/unconfirmIp-list";
	}
	
	//获取待审核表里的所有IP信息
	@RequestMapping("/getUnconfirmIpList")
	@ResponseBody
	public Map<String, Object> toUnconfirmIp(int page, int limit, UnconfirmIp unconfirmIp) {
		
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	
	
	@RequestMapping("/toAgreeDelete")
	@ResponseBody
	public Map<String, Object> agreeDelUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		ipService.deleteIpByIpAddress(unconfirm.getIpAddress());
		unconfirmIpService.deleteUnconfirmIpById(id);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	@RequestMapping("/toCancelDelete")
	@ResponseBody
	public Map<String, Object> cancelDelUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		unconfirm.setUnconfirmStatus("审核未通过(删除操作)");
		unconfirmIpService.updateUnconfirmIpById(unconfirm);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	
	@RequestMapping("/toAgreeAdd")
	@ResponseBody
	public Map<String, Object> agreeAddUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		Ipform ipform=new Ipform();
		   ipform.setIpAddress(unconfirm.getIpAddress());
		   ipform.setIpStatus(unconfirm.getIpStatus());
		   ipform.setIpRemarks(unconfirm.getIpRemarks());
		   ipform.setIpSubnetmask(unconfirm.getIpSubnetmask());
		   ipform.setIpAddressnumber(unconfirm.getIpAddressnumber());
		   ipform.setIpUsetime(unconfirm.getIpUsetime());
		   ipform.setIpUsername(unconfirm.getIpUsername());
		   ipform.setIpVlan(unconfirm.getIpVlan());
		   ipform.setIpConnectingdevice(unconfirm.getIpConnectingdevice());
		   ipform.setIpPort(unconfirm.getIpPort());
		   ipform.setIpRate(unconfirm.getIpRate());
		   ipform.setIpAttribution(unconfirm.getIpAttribution());
		   ipform.setIpBroadbandacceptancenumber(unconfirm.getIpBroadbandacceptancenumber());
		   ipform.setIpSnnumber(unconfirm.getIpSnnumber());
		   ipform.setIpOltaddress(unconfirm.getIpOltaddress());
		   ipform.setIpIomusername(unconfirm.getIpIomusername());
		   ipform.setIpInstalledaddress(unconfirm.getIpInstalledaddress());
		   ipform.setIpType(unconfirm.getIpType());
		   ipform.setIpWotvbssremarks(unconfirm.getIpWotvbssremarks());
		   ipform.setIpOutputrate(unconfirm.getIpOutputrate());
		   ipform.setIpInputrate(unconfirm.getIpInputrate());
		   ipform.setIpTerminalnumber(unconfirm.getIpTerminalnumber());
		   ipform.setApprovalStatus(0);//状态设置为无审核
		   ipService.addIp(ipform);
		   unconfirmIpService.deleteUnconfirmIpById(id);
	        //添加到历史表中
	        ipService.getIpformByIpAddress(ipform.getIpAddress());
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       //由于新添加的字段ipNumber（序号）还没有生成是mysql自增生成的。需要从数据库获取。
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
	        historyIpMapper.insertSelective(historyIp);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	@RequestMapping("/toCancelAdd")
	@ResponseBody
	public Map<String, Object> cancelAddUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		unconfirm.setUnconfirmStatus("审核未通过(添加操作)");
		unconfirmIpService.updateUnconfirmIpById(unconfirm);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	
	@RequestMapping("/toAgreeUpdate")
	@ResponseBody
	public Map<String, Object> agreeUpdateUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		Ipform ipform=new Ipform();
		   ipform.setIpAddress(unconfirm.getIpAddress());
		   ipform.setIpStatus(unconfirm.getIpStatus());
		   ipform.setIpRemarks(unconfirm.getIpRemarks());
		   ipform.setIpSubnetmask(unconfirm.getIpSubnetmask());
		   ipform.setIpAddressnumber(unconfirm.getIpAddressnumber());
		   ipform.setIpUsetime(unconfirm.getIpUsetime());
		   ipform.setIpUsername(unconfirm.getIpUsername());
		   ipform.setIpVlan(unconfirm.getIpVlan());
		   ipform.setIpConnectingdevice(unconfirm.getIpConnectingdevice());
		   ipform.setIpPort(unconfirm.getIpPort());
		   ipform.setIpRate(unconfirm.getIpRate());
		   ipform.setIpAttribution(unconfirm.getIpAttribution());
		   ipform.setIpBroadbandacceptancenumber(unconfirm.getIpBroadbandacceptancenumber());
		   ipform.setIpSnnumber(unconfirm.getIpSnnumber());
		   ipform.setIpOltaddress(unconfirm.getIpOltaddress());
		   ipform.setIpIomusername(unconfirm.getIpIomusername());
		   ipform.setIpInstalledaddress(unconfirm.getIpInstalledaddress());
		   ipform.setIpType(unconfirm.getIpType());
		   ipform.setIpWotvbssremarks(unconfirm.getIpWotvbssremarks());
		   ipform.setIpOutputrate(unconfirm.getIpOutputrate());
		   ipform.setIpInputrate(unconfirm.getIpInputrate());
		   ipform.setIpTerminalnumber(unconfirm.getIpTerminalnumber());
		   ipform.setApprovalStatus(0);
		   ipService.updateIpByIpAddressSelective(ipform);
		   unconfirmIpService.deleteUnconfirmIpById(id);
			//添加到历史记录表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       //由于新添加的字段ipNumber（序号）还没有生成是mysql自增生成的。需要从数据库获取。
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
	        historyIpMapper.insertSelective(historyIp);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
	@RequestMapping("/toCancelUpdate")
	@ResponseBody
	public Map<String, Object> cancelUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		unconfirm.setUnconfirmStatus("审核未通过(修改操作)");
		unconfirmIpService.updateUnconfirmIpById(unconfirm);
		return auditorIpService.getAll(page,limit,unconfirmIp);
	}
}
