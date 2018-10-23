package com.haiwen.school.zx.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haiwen.school.zx.beans.ExcelIp;
import com.haiwen.school.zx.beans.HistoryIp;
import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.mapper.HistoryIpMapper;
import com.haiwen.school.zx.service.ExcelIpService;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UserService;
import com.haiwen.school.zx.util.HistoryIpAddUtil;

@Controller
@RequestMapping("/excelIp")
public class ExcelIpController {

	@Autowired
	private ExcelIpService excelIpService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private IpService ipService;
   
	@Autowired
	private HistoryIpMapper historyIpMapper;
    
	//注册一个类型解析器将date类型输入到数据库中
	@org.springframework.web.bind.annotation.InitBinder
	 public void InitBinder(WebDataBinder binder){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }
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
	
	
	//跳转到修改页面，
	@RequestMapping("toEdit")
	public String toEdit(Integer id,HttpServletRequest request) {
		ExcelIp excelIp=excelIpService.getExcelIpById(id);
		request.setAttribute("ip",excelIp);
		return "ip/ip-excelEdit";
	}
	//修改ip地址信息
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Integer updateIp(ExcelIp excelIp) {
		Integer result=0;
		
		try {
			excelIpService.updateExcelIpById(excelIp);
		}catch(Exception e){
			result=-1;
		}
		return result;
	}
	
	//删除IP地址信息
	@RequestMapping("/toDelete")
	@ResponseBody
	public Map<String, Object> deleteIp(int page,int limit,Integer id,ExcelIp excelIp) {
		excelIpService.deleteExcelIpById(id);
		//return "ip/ip-list";
		return excelIpService.getAll(page,limit,excelIp);
	}
	
	//删除excelip表里的所有数据
	@ResponseBody
	@RequestMapping("/toDeleteAllExcelIp")
	public String deleteAllExcelIp(HttpServletResponse response) {
		List<ExcelIp> list=this.excelIpService.getAllExcelIp();
		if(null == list || list.size() ==0 ) {
			return "无可删除的数据！";
		}else {
		for(int i=0;i<list.size();i++) {
			excelIpService.deleteExcelIpById(list.get(i).getIpId());
		}
		return "删除成功！请刷新查看";
		}
	}
	
	//导入到正式表（ipform）中
	@ResponseBody
	@RequestMapping("/importIpform")
	public String importIpform(HttpServletResponse response)  {
		List<ExcelIp> list=this.excelIpService.getAllExcelIp();
		if(null==list||list.size()==0) {
			return "预览表中没有待导入的数据！";
		}
		ExcelIp excelIp=new ExcelIp();
		Ipform ipform=new Ipform();
		int flag=0;
		for(int i=0;i<list.size();i++) {
		
			excelIp=list.get(i);
		       if(ipService.getIpformByIpAddress(excelIp.getIpAddress())!=null){  		    	   
	            try {
						throw new Exception("IP地址重复！");
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	   flag+=1;
		           continue;
		        }
			ipform.setIpAddress(excelIp.getIpAddress());
			ipform.setIpStatus(excelIp.getIpStatus());
			ipform.setIpRemarks(excelIp.getIpRemarks());
			ipform.setIpSubnetmask(excelIp.getIpSubnetmask());
			ipform.setIpAddressnumber(excelIp.getIpAddressnumber());
			ipform.setIpUsetime(excelIp.getIpUsetime());
			ipform.setIpUsername(excelIp.getIpUsername());
			ipform.setIpVlan(excelIp.getIpVlan());
			ipform.setIpConnectingdevice(excelIp.getIpConnectingdevice());
			ipform.setIpPort(excelIp.getIpPort());
			ipform.setIpRate(excelIp.getIpRate());
			ipform.setIpAttribution(excelIp.getIpAttribution());
			ipform.setIpBroadbandacceptancenumber(excelIp.getIpBroadbandacceptancenumber());
			ipform.setIpSnnumber(excelIp.getIpSnnumber());
			ipform.setIpOltaddress(excelIp.getIpOltaddress());
			ipform.setIpIomusername(excelIp.getIpIomusername());
			ipform.setIpInstalledaddress(excelIp.getIpInstalledaddress());
			ipform.setIpType(excelIp.getIpType());
			ipform.setIpWotvbssremarks(excelIp.getIpWotvbssremarks());
			ipform.setIpOutputrate(excelIp.getIpOutputrate());
			ipform.setIpInputrate(excelIp.getIpInputrate());
			ipform.setIpTerminalnumber(excelIp.getIpTerminalnumber());
			

			ipService.addIp(ipform);
			excelIpService.deleteExcelIpById(excelIp.getIpId());
			
	        //添加到历史表中
		       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
		       //由于新添加的字段ipNumber（序号）还没有生成，是mysql自增生成的。需要从数据库获取。
		       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
		        historyIpMapper.insertSelective(historyIp);
		
		}
		
		if(flag!=0) {
			return "有"+flag+"条IP与IP列表中的IP地址重复！请检查后再次提交。";
		}else {
			return "导入IP地址表成功！";
		}
		
	}
}
