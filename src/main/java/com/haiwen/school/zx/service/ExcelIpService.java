package com.haiwen.school.zx.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haiwen.school.zx.beans.ExcelIp;

public interface ExcelIpService {
	String ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response);
	
	
	//添加ip
	void addIp(ExcelIp excelIp);
	
	//获取所有IP
	List<ExcelIp> getAllExcelIp();
	Map<String, Object> getAll(int page, int limit, ExcelIp excelIp);
	
	//根据id（也就是ipNumber）获取一条IP地址信息
	ExcelIp getExcelIpById(Integer id);
	
	ExcelIp getExcelIpByIpAddress(String address);
	
	//根据id修改IP地址信息
	void updateIpById(ExcelIp excelIp);
	
	void updateIpByIpAddressSelective(ExcelIp excelIp);
	
	//根据id删除IP地址信息
	void deleteIpById(Integer id);
	
	void deleteIpByIpAddress(String address);
}
