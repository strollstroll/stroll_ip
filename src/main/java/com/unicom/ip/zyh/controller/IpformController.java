package com.unicom.ip.zyh.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.ip.zyh.beans.HistoryIp;
import com.unicom.ip.zyh.beans.Ipform;
import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.mapper.HistoryIpMapper;
import com.unicom.ip.zyh.mapper.OperationLogMapper;
import com.unicom.ip.zyh.service.IpService;
import com.unicom.ip.zyh.service.OperationLogService;
import com.unicom.ip.zyh.service.UserService;
import com.unicom.ip.zyh.util.ExportExcelUtil;
import com.unicom.ip.zyh.util.HistoryIpAddUtil;

@Controller
@RequestMapping("/ip")
public class IpformController {

	@Autowired
	private IpService ipService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private HistoryIpMapper historyIpMapper;
	
	@Autowired
	private OperationLogService operationLogService;
	
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
	
	//获取IP信息列表
	@RequestMapping("/getIpformList")
	@ResponseBody
	public Map<String, Object> toIpInform(int page, int limit, Ipform ipform) {
		return ipService.getAll(page,limit,ipform);
	}
	
	//历史记录表
	@RequestMapping("/toHistoryIpList")
	public String toHistoryList(HttpServletRequest request){
		request.setAttribute("power", userService.getPower());
		return "ip/historyIp-list";
	}
	//获取历史IP信息列表
	@RequestMapping("/getHistoryIpList")
	@ResponseBody
	public Map<String, Object> toHistoryIp(int page, int limit, HistoryIp historyIp) {
		return ipService.getHistoryIpAll(page,limit,historyIp);
	}

	@RequestMapping("/toIpformEdit")
	public String ipformEdit(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "ip/ip-edit";
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
	public Integer addIpform(HttpSession session,Ipform ipform) {
		Integer result=0;
		if(ipService.getIpformByIpAddress(ipform.getIpAddress())!=null) {
			return 2;
		}
        try{
            ipService.addIp(ipform);
        }catch(Exception e){
            result = -1;
        }
        //添加到历史表中
       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
       //由于新添加的字段ipNumber（序号）还没有生成，是mysql自增生成的。需要从数据库获取。
       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
        historyIpMapper.insertSelective(historyIp);
        
        //添加到日志
        operationLogService.addOperationLog(session, "添加", "新添加（"+ipService.getIpformByIpAddress(ipform.getIpAddress())+"）");
        
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
	public Integer updateIp(HttpSession session,Ipform ipform) {
		Integer result=0;
		Ipform oldIpform=ipService.getIpformById(ipform.getIpNumber());
		if(oldIpform.toString().equals(ipform.toString())){
			result=-2;
			return result;
		}
		try {
			ipService.updateIpById(ipform);
		}catch(Exception e){
			result=-1;
		}
		//添加到历史记录表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       Ipform newIpform=ipService.getIpformById(ipform.getIpNumber());
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(newIpform);
	        historyIpMapper.insertSelective(historyIp);
	       
	        //添加到日志
	        operationLogService.addOperationLog(session, "修改", "旧（"+oldIpform.toString()+"）   新（"+newIpform.toString()+")"); 
	       
		return result;
	}
	
	//删除IP地址信息
	@RequestMapping("/toDelete")
	@ResponseBody
	public Map<String, Object> deleteIp(HttpSession session,int page,int limit,Integer id,Ipform ipform) {
		String str=ipService.getIpformById(id).toString();
		ipService.deleteIpById(id);
		//添加到日志
        operationLogService.addOperationLog(session, "删除", "（"+str+")"); 
		return ipService.getAll(page,limit,ipform);
	}

	//列表内容导出到excel中
	@RequestMapping("/exportExcel")
	public void exportExcelIpList(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Ipform> list=this.ipService.getAllIpform();
		list.get(0).getApprovalStatus();
		Workbook workbook=new XSSFWorkbook();
		Sheet sheet=workbook.createSheet();
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("状态");
		row.createCell(2).setCellValue("备注");
		row.createCell(3).setCellValue("IP地址");
		row.createCell(4).setCellValue("子网掩码");
		row.createCell(5).setCellValue("地址数量");
		row.createCell(6).setCellValue("起用时间");
		row.createCell(7).setCellValue("用户名称");
		row.createCell(8).setCellValue("vlan号");
		row.createCell(9).setCellValue("连接设备");
		row.createCell(10).setCellValue("端口");
		row.createCell(11).setCellValue("速率");
		row.createCell(12).setCellValue("运维系统编号");
		row.createCell(13).setCellValue("宽带受理编号");
		row.createCell(14).setCellValue("SN号");
		row.createCell(15).setCellValue("OLT地址");
		row.createCell(16).setCellValue("IOM用户名");
		row.createCell(17).setCellValue("装机地址");
		row.createCell(18).setCellValue("类型");
		row.createCell(19).setCellValue("WOTV-BSS主产品(备注2)");
		row.createCell(20).setCellValue("上行速率");
		row.createCell(21).setCellValue("下行速率");
		row.createCell(22).setCellValue("对应终端数");
		
		for(int i=0;i<list.size();i++) {
			row=sheet.createRow(i+1);
			if(list.get(i).getIpNumber()==null){row.createCell(0).setCellValue("");}else{row.createCell(0).setCellValue(list.get(i).getIpNumber());}
			if(list.get(i).getIpStatus()==null){row.createCell(1).setCellValue("");}else{row.createCell(1).setCellValue(list.get(i).getIpStatus());}
			if(list.get(i).getIpRemarks()==null){row.createCell(2).setCellValue("");}else{row.createCell(2).setCellValue(list.get(i).getIpRemarks());}
			if(list.get(i).getIpAddress()==null){row.createCell(3).setCellValue("");}else{row.createCell(3).setCellValue(list.get(i).getIpAddress());}
			if(list.get(i).getIpSubnetmask()==null){row.createCell(4).setCellValue("");}else{row.createCell(4).setCellValue(list.get(i).getIpSubnetmask());}
			if(list.get(i).getIpAddressnumber()==null){row.createCell(5).setCellValue("");}else{row.createCell(5).setCellValue(list.get(i).getIpAddressnumber());}
			if(list.get(i).getIpUsetime()==null){row.createCell(6).setCellValue("");}else{row.createCell(6).setCellValue(list.get(i).getIpUsetime());}
			if(list.get(i).getIpUsername()==null){row.createCell(7).setCellValue("");}else{row.createCell(7).setCellValue(list.get(i).getIpUsername());}
			if(list.get(i).getIpVlan()==null){row.createCell(8).setCellValue("");}else{row.createCell(8).setCellValue(list.get(i).getIpVlan());}
			if(list.get(i).getIpConnectingdevice()==null){row.createCell(9).setCellValue("");}else{row.createCell(9).setCellValue(list.get(i).getIpConnectingdevice());}
			if(list.get(i).getIpPort()==null){row.createCell(10).setCellValue("");}else{row.createCell(10).setCellValue(list.get(i).getIpPort());}
			if(list.get(i).getIpRate()==null){row.createCell(11).setCellValue("");}else{row.createCell(11).setCellValue(list.get(i).getIpRate());}
			if(list.get(i).getIpAttribution()==null){row.createCell(12).setCellValue("");}else{row.createCell(12).setCellValue(list.get(i).getIpAttribution());}
			if(list.get(i).getIpBroadbandacceptancenumber()==null){row.createCell(13).setCellValue("");}else{row.createCell(13).setCellValue(list.get(i).getIpBroadbandacceptancenumber());}
			if(list.get(i).getIpSnnumber()==null){row.createCell(14).setCellValue("");}else{row.createCell(14).setCellValue(list.get(i).getIpSnnumber());}
			if(list.get(i).getIpOltaddress()==null){row.createCell(15).setCellValue("");}else{row.createCell(15).setCellValue(list.get(i).getIpOltaddress());}
			if(list.get(i).getIpIomusername()==null){row.createCell(16).setCellValue("");}else{row.createCell(16).setCellValue(list.get(i).getIpIomusername());}
			if(list.get(i).getIpInstalledaddress()==null){row.createCell(17).setCellValue("");}else{row.createCell(17).setCellValue(list.get(i).getIpInstalledaddress());}
			if(list.get(i).getIpType()==null){row.createCell(18).setCellValue("");}else{row.createCell(18).setCellValue(list.get(i).getIpType());}
			if(list.get(i).getIpWotvbssremarks()==null){row.createCell(19).setCellValue("");}else{row.createCell(19).setCellValue(list.get(i).getIpWotvbssremarks());}
			if(list.get(i).getIpOutputrate()==null){row.createCell(20).setCellValue("");}else{row.createCell(20).setCellValue(list.get(i).getIpOutputrate());}
			if(list.get(i).getIpInputrate()==null){row.createCell(21).setCellValue("");}else{row.createCell(21).setCellValue(list.get(i).getIpInputrate());}
			if(list.get(i).getIpTerminalnumber()==null){row.createCell(22).setCellValue("");}else{row.createCell(22).setCellValue(list.get(i).getIpTerminalnumber());}

		}
		ExportExcelUtil.write("IP",workbook,request,response);
		//添加到日志
       operationLogService.addOperationLog(session, "excel导出", "导出"+list.size()+"条IP。");
	}
	
	@ResponseBody
	@RequestMapping("/toIpMerge")
	public String mergeIp(HttpSession session,String address1,String address2,HttpServletRequest request) {
		Ipform ipform1=ipService.getIpformByIpAddress(address1);
		Ipform ipform2=ipService.getIpformByIpAddress(address2);
		String[] arr1=ipform1.getIpAddress().split("\\.");
		String[] arr2=(ipform2.getIpAddress()).split("\\.");
		String[] arr3=arr1[3].split("\\-");
		String[] arr4=arr2[3].split("\\-");
		String mergeIp=arr1[0]+"."+arr1[1]+"."+arr1[2]+"."+arr3[0]+"-"+arr4[1];
		Ipform mergeIpform=new Ipform();
		mergeIpform.setIpStatus("备用");
		mergeIpform.setIpAddress(mergeIp);
		mergeIpform.setIpSubnetmask("255.255.255."+(255-ipform1.getIpAddressnumber()-ipform2.getIpAddressnumber()+1));
		mergeIpform.setIpAddressnumber(ipform1.getIpAddressnumber()+ipform2.getIpAddressnumber());
		ipService.deleteIpByIpAddress(address1);
		ipService.deleteIpByIpAddress(address2);
		ipService.addIp(mergeIpform);
		
        //添加到历史表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       //由于新添加的字段ipNumber（序号）还没有生成，是mysql自增生成的。需要从数据库获取。
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(mergeIpform.getIpAddress()));
	        historyIpMapper.insertSelective(historyIp);
	        
	      //添加到日志
	        operationLogService.addOperationLog(session, "IP合并","待合并1："+ipform1.getIpAddress()+" 待合并2："+ipform2.getIpAddress()+" 合并后："+mergeIpform.getIpAddress());
	        
		return "IP合并完成："+mergeIpform.getIpAddress();
	}
	
	//跳转到拆分页面，
	@RequestMapping("/toIpSplit")
	public String toIpSplit(String address,HttpServletRequest request) {
		Ipform ipform=ipService.getIpformByIpAddress(address);
		request.setAttribute("ip",ipform);
		return "ip/ip-split";
	}
	
	//跳转到拆分页面，
	@ResponseBody
	@RequestMapping("/doSplit")
	public String doSplit(HttpSession session,String address1,Integer num,HttpServletRequest request) {
		Ipform ipform=ipService.getIpformByIpAddress(address1);
		Ipform ipMerge1=new Ipform();
		Ipform ipMerge2=new Ipform();
		String address2=ipform.getIpAddress();
		String[] temp1=address2.split("\\-");
		String[] temp2=address2.split("\\.");
		String[] temp3=temp2[3].split("\\-");
		String address3=temp1[0]+'-'+(Integer.valueOf(temp3[0])+num-1);
		String address4=temp2[0]+"."+temp2[1]+'.'+temp2[2]+'.'+(Integer.valueOf(temp3[0])+num)+'-'+temp3[1];
		int totalNum=(Integer.valueOf(temp3[1]))-(Integer.valueOf(temp3[0]));
		ipMerge1.setIpStatus("备用");
		ipMerge1.setIpAddress(address3);
		ipMerge1.setIpSubnetmask("255.255.255."+(255-num+1));
		ipMerge1.setIpAddressnumber(num);
		
		ipMerge2.setIpStatus("备用");
		ipMerge2.setIpAddress(address4);
		ipMerge2.setIpSubnetmask("255.255.255."+(255+num-totalNum+1));
		ipMerge2.setIpAddressnumber(totalNum-num+1);
		ipService.addIp(ipMerge1);
		ipService.addIp(ipMerge2);
		ipService.deleteIpByIpAddress(address1);
		
        //添加到历史表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       //由于新添加的字段ipNumber（序号）还没有生成，是mysql自增生成的。需要从数据库获取。
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipMerge1.getIpAddress()));
	        historyIpMapper.insertSelective(historyIp);
		   HistoryIp historyIp1=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipMerge2.getIpAddress()));
		   historyIpMapper.insertSelective(historyIp1);
		   //添加到日志
	        operationLogService.addOperationLog(session, "IP拆分", "原IP："+address1+" 拆分后第一条："+ipMerge1.getIpAddress()+" 拆分后第二条："+ipMerge2.getIpAddress());
		return "拆分成功.";
	}
	
	//Vlan号批量修改页面跳转
	@RequestMapping("/toVlanChange")
	public String toVlanChange(String str,HttpServletRequest request) {
		request.setAttribute("str", str);
		//修改完后要将修改了的信息输入到历史表中
		
		return "ip/ip-Vlan";
	}
	
	//Vlan批量修改
	@ResponseBody
	@RequestMapping("/doVlanChange")
	public Integer doVlanChange(HttpSession session,String ipNumber,String ipVlan) {
		String[] str1=ipNumber.split("\\,");
		Integer result=0;
        //添加到历史表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
		try {
			Ipform ipform=new Ipform();
		for(int i=0;i<str1.length;i++) {
			ipform=ipService.getIpformById(Integer.valueOf(str1[i]));
			ipform.setIpVlan(ipVlan);
			ipService.updateIpById(ipform);

		     //由于新添加的字段ipNumber（序号）还没有生成，是mysql自增生成的。需要从数据库获取。
		     HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
		     historyIpMapper.insertSelective(historyIp);
		}
		
		}catch(Exception e){
			result=-1;
		}
		 //添加到日志
        operationLogService.addOperationLog(session, "Vlan批量修改", "修改的数量："+str1.length+"条  新的Vlan号："+ipVlan);
		return result;
	}
	
	
}
