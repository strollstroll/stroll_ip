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

import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.beans.UnconfirmIp;
import com.haiwen.school.zx.service.IpService;
import com.haiwen.school.zx.service.UnconfirmIpService;
import com.haiwen.school.zx.service.UserService;
import com.haiwen.school.zx.util.ExcelUtil;

/**
 * 对于普通管理员的增删改添加到审核页面中，提交给审核员处理
 */
@Controller
@RequestMapping("/unconfirmIp")
public class UnconfirmIpController {

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
	
	//跳转到待审核ip列表页面
	@RequestMapping("/toUnconfirmIpList")
	public String toList1(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/unconfirmIp-list";
	}
	
	//获取待审核表里的所有IP信息
	@RequestMapping("/getUnconfirmIpList")
	@ResponseBody
	public Map<String, Object> toUnconfirmIp(int page, int limit, UnconfirmIp unconfirmIp) {
		
		return unconfirmIpService.getAll(page,limit,unconfirmIp);
	}
	
	//跳转到IP编辑页面
	@RequestMapping("/toUnconfirmIpEdit")
	public String unconfirmIpEdit(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/ip-edit";
	}

	//跳转到添加IP页面
	@RequestMapping("/addUnconfirmIp")
	public String addUnconfirmIp(HttpServletRequest request){
	    request.setAttribute("power",userService.getPower());
	      return "employee/ip-add";
	}
	//添加IP地址信息
	@RequestMapping("/doAdd")
	@ResponseBody
	public Integer addUnconfirmIp(UnconfirmIp unconfirmIp) {
		Integer result=0;
        try{
            unconfirmIpService.addUnconfirmIp(unconfirmIp);
        }catch(Exception e){
            result = -1;
        }
        return result;
	}
	
	
	//查看单条IP地址信息
	@RequestMapping("/toGetUnconfirmIpWatch")
	public String getIpWatch(Integer id,HttpServletRequest request) {
		UnconfirmIp unconfirmIp=new UnconfirmIp();
		unconfirmIp=unconfirmIpService.getUnconfirmIpById(id);
		request.setAttribute("ip",unconfirmIp);
		return "employee/ip-watch";
	}
	
	//跳转到修改页面，
	@RequestMapping("toEdit")
	public String toEdit(Integer id,HttpServletRequest request) {
		UnconfirmIp unconfirmIp=unconfirmIpService.getUnconfirmIpById(id);
		request.setAttribute("ip",unconfirmIp);
		return "employee/unconfirmIp-edit";
	}
	
	//修改ip地址信息,普通管理员只能将修改的ip添加到待审核表，原ip表不变
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Integer updateIp(UnconfirmIp unconfirmIp) {
		Integer result=0;
		
		try {
			unconfirmIpService.updateUnconfirmIpById(unconfirmIp);
		}catch(Exception e){
			result=-1;
		}
		return result;
	}
	
	//删除IP地址信息，普通管理员只能将Ip地址添加到待审核表中，原ip表并没有删除
	@RequestMapping("/toDelete")
	@ResponseBody
	public Map<String, Object> deleteUnconfirmIp(int page,int limit,Integer id,UnconfirmIp unconfirmIp) {
		UnconfirmIp unconfirm=unconfirmIpService.getUnconfirmIpById(id);
		Ipform ipform=ipService.getIpformByIpAddress(unconfirm.getIpAddress());
		ipform.setApprovalStatus(0);
		unconfirmIpService.deleteUnconfirmIpById(id);
		ipService.updateIpById(ipform);
		
		return unconfirmIpService.getAll(page,limit,unconfirmIp);
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
			row.createCell(0).setCellValue(list.get(i).getIpAddress());
			row.createCell(1).setCellValue(list.get(i).getIpNumber());
			row.createCell(2).setCellValue(list.get(i).getIpStatus());
			row.createCell(3).setCellValue(list.get(i).getIpRemarks());
			row.createCell(4).setCellValue(list.get(i).getIpSubnetmask());
			row.createCell(5).setCellValue(list.get(i).getIpAddressnumber());
			row.createCell(6).setCellValue(list.get(i).getIpUsetime());
			row.createCell(7).setCellValue(list.get(i).getIpUsername());
			row.createCell(8).setCellValue(list.get(i).getIpVlan());
			row.createCell(9).setCellValue(list.get(i).getIpConnectingdevice());
			row.createCell(10).setCellValue(list.get(i).getIpPort());
			row.createCell(11).setCellValue(list.get(i).getIpRate());
			row.createCell(12).setCellValue(list.get(i).getIpAttribution());
			row.createCell(13).setCellValue(list.get(i).getIpBroadbandacceptancenumber());
			row.createCell(14).setCellValue(list.get(i).getIpSnnumber());
			row.createCell(15).setCellValue(list.get(i).getIpOltaddress());
			row.createCell(16).setCellValue(list.get(i).getIpIomusername());
			row.createCell(17).setCellValue(list.get(i).getIpInstalledaddress());
			row.createCell(18).setCellValue(list.get(i).getIpType());
			row.createCell(19).setCellValue(list.get(i).getIpWotvbssremarks());
			row.createCell(20).setCellValue(list.get(i).getIpOutputrate());
			row.createCell(21).setCellValue(list.get(i).getIpInputrate());
			row.createCell(22).setCellValue(list.get(i).getIpTerminalnumber());
		}
		ExcelUtil.write("IP地址分配表（专线业务部分）",workbook,response);
	}
	
}
