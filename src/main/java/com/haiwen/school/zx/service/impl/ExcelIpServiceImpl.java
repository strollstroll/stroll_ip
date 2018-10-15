package com.haiwen.school.zx.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.ExcelIp;
import com.haiwen.school.zx.beans.Ipform;
import com.haiwen.school.zx.mapper.ExcelIpMapper;
import com.haiwen.school.zx.service.ExcelIpService;
import com.haiwen.school.zx.util.ImportExcelUtil;
@Service
public class ExcelIpServiceImpl implements ExcelIpService{
@Autowired
private ExcelIpMapper excelIpMapper;
	public String ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;    
        
        MultipartFile file = multipartRequest.getFile("upfile");  
        if(file.isEmpty()){  
            try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}  
        }  
          
        InputStream in =null;  
        try {
			in = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
		List<List<Object>> listob = null; 
		try {
			listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
	    //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> lo = listob.get(i);  
            ExcelIp vo = new ExcelIp(); 
            ExcelIp j = null;
        	
			try {
				j = excelIpMapper.selectByPrimaryKey(Integer.valueOf(String.valueOf(lo.get(0))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("没有新增");
			}
			System.out.println("------------------------------------------------------");   
			vo.setIpStatus(String.valueOf(lo.get(0)));System.out.println(0);
			vo.setIpRemarks(String.valueOf(lo.get(1)));System.out.println(1);
			vo.setIpAddress(String.valueOf(lo.get(2)));System.out.println(2);
			vo.setIpSubnetmask(String.valueOf(lo.get(3)));System.out.println(3);
			vo.setIpAddressnumber(Integer.valueOf(String.valueOf(lo.get(4))));System.out.println(4);
			vo.setIpUsetime(Date.valueOf(String.valueOf(lo.get(5))));System.out.println(5);
			vo.setIpUsername(String.valueOf(lo.get(6)));System.out.println(6);
			vo.setIpVlan(String.valueOf(lo.get(7)));System.out.println(7);
			vo.setIpConnectingdevice(String.valueOf(lo.get(8)));System.out.println(8);
			vo.setIpPort(String.valueOf(lo.get(9)));System.out.println(9);
			vo.setIpRate(String.valueOf(lo.get(10)));System.out.println(10);
			vo.setIpAttribution(String.valueOf(lo.get(11)));System.out.println(11);
			vo.setIpBroadbandacceptancenumber(String.valueOf(lo.get(12)));System.out.println(12);
			vo.setIpSnnumber(Integer.valueOf(String.valueOf(lo.get(13))));System.out.println(13);
			vo.setIpOltaddress(String.valueOf(lo.get(14)));System.out.println(14);
			vo.setIpIomusername(String.valueOf(lo.get(15)));System.out.println(15);
			vo.setIpInstalledaddress(String.valueOf(lo.get(16)));System.out.println(16);
			vo.setIpType(String.valueOf(lo.get(17)));System.out.println(17);
			vo.setIpWotvbssremarks(String.valueOf(lo.get(18)));System.out.println(18);
			vo.setIpOutputrate(Integer.valueOf(String.valueOf(lo.get(19))));System.out.println(19);
			vo.setIpInputrate(Integer.valueOf(String.valueOf(lo.get(20))));System.out.println(20);
			vo.setIpTerminalnumber(Integer.valueOf(String.valueOf(lo.get(21))));System.out.println(21);
			if(j == null)
			{
				excelIpMapper.insert(vo);
			}
			else
			{
				excelIpMapper.updateByPrimaryKey(vo);
			}
        }   
        return "文件导入成功！";
	}
	public void addIp(ExcelIp excelIp) {
		// TODO Auto-generated method stub
		
	}
	public List<ExcelIp> getAllExcelIp() {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String, Object> getAll(int page, int limit, ExcelIp excelIp) {
		Map<String,Object> ipMap=new HashMap<String,Object>();
		System.out.println(excelIp.getIpAddress());
		if(StringUtil.isNotEmpty(excelIp.getIpAddress())) {
			ipMap.put("ipAddress", excelIp.getIpAddress());
		}

        PageHelper.startPage(page, limit);
        List<ExcelIp> ipList=excelIpMapper.getAll(ipMap);
        //查看是否查询到数据库数据
        System.out.println("************************************");
        System.out.println(ipList.toString());
        Iterator itr=ipList.iterator();
        while(itr.hasNext())
        	System.out.println(itr.next().toString());
        System.out.println("************************************");
        PageInfo<ExcelIp> pageInfo=new PageInfo<ExcelIp>(ipList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询数据成功！");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
	}
	public ExcelIp getExcelIpById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	public ExcelIp getExcelIpByIpAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}
	public void updateIpById(ExcelIp excelIp) {
		// TODO Auto-generated method stub
		
	}
	public void updateIpByIpAddressSelective(ExcelIp excelIp) {
		// TODO Auto-generated method stub
		
	}
	public void deleteIpById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	public void deleteIpByIpAddress(String address) {
		// TODO Auto-generated method stub
		
	}


	
	
}
