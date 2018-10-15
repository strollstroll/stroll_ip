package com.haiwen.school.zx.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.haiwen.school.zx.beans.HistoryIp;
import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.mapper.HistoryIpMapper;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UserService;
import com.haiwen.school.zx.util.ExportExcelUtil;
import com.haiwen.school.zx.util.HistoryIpAddUtil;

@Controller
@RequestMapping("/ip")
public class IpformController {

	@Autowired
	private IpService ipService;
	
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
	public Integer addIpform(Ipform ipform) {
		Integer result=0;
        try{
            ipService.addIp(ipform);
        }catch(Exception e){
            result = -1;
        }
        //添加到历史表中
        ipService.getIpformByIpAddress(ipform.getIpAddress());
       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
       //由于新添加的字段ipNumber（序号）还没有生成是mysql自增生成的。需要从数据库获取。
       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
        historyIpMapper.insertSelective(historyIp);
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
		//添加到历史记录表中
	       HistoryIpAddUtil historyIpAddUtil=new HistoryIpAddUtil();
	       //由于新添加的字段ipNumber（序号）还没有生成是mysql自增生成的。需要从数据库获取。
	       HistoryIp historyIp=historyIpAddUtil.historyIpAdd(ipService.getIpformByIpAddress(ipform.getIpAddress()));
	        historyIpMapper.insertSelective(historyIp);
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
	
	
	
	//列表内容导出到excel中
	
	
	/**
	 * 描述：导出用户列表
	 * @param response
	 * @throws Exception
	 * @author songfayuan
	 * 2017年6月19日下午4:26:32
	 */
	@RequestMapping("/exportExcel")
	public void exportExcelIpList(HttpServletResponse response) throws Exception {
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
		ExportExcelUtil.write("IP地址分配表",workbook,response);
	}
	
}
