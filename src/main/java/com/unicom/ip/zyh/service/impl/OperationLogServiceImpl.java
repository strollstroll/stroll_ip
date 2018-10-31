package com.unicom.ip.zyh.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.beans.OperationLog;
import com.unicom.ip.zyh.mapper.LogininfoMapper;
import com.unicom.ip.zyh.mapper.OperationLogMapper;
import com.unicom.ip.zyh.service.OperationLogService;
@Service
public class OperationLogServiceImpl implements OperationLogService{
  @Autowired
  OperationLogMapper operationLogMapper;
  
  
    public List<OperationLog> getAllOperationLog() {
        // TODO Auto-generated method stub
        //return operationLogMapper.getAll();
        return operationLogMapper.getAll(null);
  }

  public Map<String, Object> getAll(int page, int limit, OperationLog operationLog) {
              Map<String,Object> ipMap=new HashMap<String,Object>();
              if(StringUtil.isNotEmpty(operationLog.getOperationUser())) {
                    ipMap.put("operationUser", operationLog.getOperationUser());
              }
              if(StringUtil.isNotEmpty(operationLog.getOperationTime())) {
                  ipMap.put("operationTime", operationLog.getOperationTime());
            }
          PageHelper.startPage(page, limit);
          List<OperationLog> ipList=operationLogMapper.getAll(ipMap);
          //查看是否查询到数据库数据
          System.out.println("************************************");
          System.out.println(ipList.toString());
          Iterator itr=ipList.iterator();
          while(itr.hasNext())
              System.out.println(itr.next().toString());
          System.out.println("************************************");
          PageInfo<OperationLog> pageInfo=new PageInfo<OperationLog>(ipList);
          Map<String,Object> map=new HashMap<String, Object>();

          map.put("code",0);
          map.put("msg","查询数据成功！");
          map.put("count",pageInfo.getTotal());
          map.put("data",pageInfo.getList());
          return map;
     
  }

  public void addOperationLog(HttpSession session,String operationType,String operationContent) {
	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Logininfo logininfo=(Logininfo) session.getAttribute("userInfo");
	  OperationLog operationLog=new OperationLog();
	  operationLog.setOperationUser(logininfo.getNickname());
	  operationLog.setOperationTime(df.format(new Date()));
	  operationLog.setOperationType(operationType);
	  operationLog.setOperationContent(operationContent);
        operationLogMapper.insertSelective(operationLog);
        
  }

  public OperationLog getOperationLogById(Integer id) {
        return operationLogMapper.selectByPrimaryKey(id);
  }

  public void updateOperationLogById(OperationLog operationLog) {
        operationLogMapper.updateByPrimaryKey(operationLog);
  }
  


  public void deleteOperationLogById(Integer id) {
        operationLogMapper.deleteByPrimaryKey(id);
  }


 
}

