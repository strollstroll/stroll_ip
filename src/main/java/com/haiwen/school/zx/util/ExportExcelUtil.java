package com.haiwen.school.zx.util;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportExcelUtil {
	public static void write(String fileName, Workbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
        final String userAgent = request.getHeader("USER-AGENT"); 
        String finalFileName = null;  
        
        if (StringUtils.contains(userAgent, "Edge")) {
            finalFileName = URLEncoder.encode(fileName, "UTF8");
        } else if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
            finalFileName = URLEncoder.encode(fileName, "UTF8");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
            finalFileName = new String(fileName.getBytes(), "ISO8859-1");
        } else {
            finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
        }
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
/*		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO-8859-1"));*/
		response.setHeader("Content-Disposition",
				"attachment;filename="+finalFileName+".xlsx");
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		
		
		

	}
}

