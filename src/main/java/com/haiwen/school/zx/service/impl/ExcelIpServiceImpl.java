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
				j = excelIpMapper.selectByIpAddress(String.valueOf(lo.get(2)));

			  if(lo.get(0)!=""){vo.setIpStatus(String.valueOf(lo.get(0)));}
		      if(lo.get(1)!=""){vo.setIpRemarks(String.valueOf(lo.get(1)));}
		      if(lo.get(2)!=""){vo.setIpAddress(String.valueOf(lo.get(2)));}
		      if(lo.get(3)!=""){vo.setIpSubnetmask(String.valueOf(lo.get(3)));}
		      if(lo.get(4)!=""){vo.setIpAddressnumber(Integer.valueOf(String.valueOf(lo.get(4))));}
		      if(lo.get(5)!=""){vo.setIpUsetime(Date.valueOf(String.valueOf(lo.get(5))));}
		      if(lo.get(6)!=""){vo.setIpUsername(String.valueOf(lo.get(6)));}
		      if(lo.get(7)!=""){vo.setIpVlan(String.valueOf(lo.get(7)));}
		      if(lo.get(8)!=""){vo.setIpConnectingdevice(String.valueOf(lo.get(8)));}
		      if(lo.get(9)!=""){vo.setIpPort(String.valueOf(lo.get(9)));}
		      if(lo.get(10)!=""){vo.setIpRate(String.valueOf(lo.get(10)));}
		      if(lo.get(11)!=""){vo.setIpAttribution(String.valueOf(lo.get(11)));}
		      if(lo.get(12)!=""){vo.setIpBroadbandacceptancenumber(String.valueOf(lo.get(12)));}
		      if(lo.get(13)!=""){vo.setIpSnnumber(Integer.valueOf(String.valueOf(lo.get(13))));}
		      if(lo.get(14)!=""){vo.setIpOltaddress(String.valueOf(lo.get(14)));}
		      if(lo.get(15)!=""){vo.setIpIomusername(String.valueOf(lo.get(15)));}
		      if(lo.get(16)!=""){vo.setIpInstalledaddress(String.valueOf(lo.get(16)));}
		      if(lo.get(17)!=""){vo.setIpType(String.valueOf(lo.get(17)));}
		      if(lo.get(18)!=""){vo.setIpWotvbssremarks(String.valueOf(lo.get(18)));}
		      if(lo.get(19)!=""){vo.setIpOutputrate(Integer.valueOf(String.valueOf(lo.get(19))));}
		      if(lo.get(20)!=""){vo.setIpInputrate(Integer.valueOf(String.valueOf(lo.get(20))));}
		      if(lo.get(21)!=""){vo.setIpTerminalnumber(Integer.valueOf(String.valueOf(lo.get(21))));}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				return "excel数据请按照格式填写！";
			}
			if(j == null)
			{
				excelIpMapper.insertSelective(vo);
			}
			else
			{
				return "新导入的excel数据与下面预览表内数据重复！请检查后再提交。。。";
			}
        }   
        return "文件导入成功！";
	}
	public void addExcelIp(ExcelIp excelIp) {
		// TODO Auto-generated method stub
		
	}
	public List<ExcelIp> getAllExcelIp() {
		// TODO Auto-generated method stub
		return excelIpMapper.getAll(null);
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
		return excelIpMapper.selectByPrimaryKey(id);
	}
	public ExcelIp getExcelIpByIpAddress(String address) {
		return excelIpMapper.selectByIpAddress(address);
	}
	public void updateExcelIpById(ExcelIp excelIp) {
		excelIpMapper.updateByPrimaryKeySelective(excelIp);
		
	}
	public void updateExcelIpByIpAddressSelective(ExcelIp excelIp) {
		excelIpMapper.updateByIpAddressSelective(excelIp);
		
	}
	public void deleteExcelIpById(Integer id) {
		excelIpMapper.deleteByPrimaryKey(id);
		
	}
	public void deleteExcelIpByIpAddress(String address) {
		excelIpMapper.deleteByIpAddress(address);
		
	}


	
	
}
