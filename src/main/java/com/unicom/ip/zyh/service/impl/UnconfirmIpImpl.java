package com.unicom.ip.zyh.service.impl;

import java.util.ArrayList;
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
import com.unicom.ip.zyh.beans.Ipform;
import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.beans.UnconfirmIp;
import com.unicom.ip.zyh.mapper.UnconfirmIpMapper;
import com.unicom.ip.zyh.service.UnconfirmIpService;
@Service
public class UnconfirmIpImpl implements UnconfirmIpService{
  @Autowired
  private UnconfirmIpMapper unconfirmIpMapper;
	
	
	//添加ip，添加完后信息录入到待审核处
  public void addUnconfirmIp(UnconfirmIp unconfirmIp) {
	  unconfirmIpMapper.insertSelective(unconfirmIp);
  }
  //获取所有待审核IP
  public List<UnconfirmIp> getAllUnconfirmIp() {
    // TODO Auto-generated method stub
    //return unconfirmIpMapper.getAll(map);
	 return null;
  }
  //获取所有待审核IP
  public Map<String, Object> getAll(int page, int limit, UnconfirmIp unconfirmIp,HttpSession session) {
	  Map<String,Object> ipMap=new HashMap<String,Object>();
		System.out.println(unconfirmIp.getIpAddress());
		if(StringUtil.isNotEmpty(unconfirmIp.getIpAddress())) {
			ipMap.put("ipAddress", unconfirmIp.getIpAddress());
		}

      PageHelper.startPage(page, limit);
      List<UnconfirmIp> ipList=unconfirmIpMapper.getAll(ipMap);
      //筛选出对应普通管理员的提交的审核IP地址信息
      List<UnconfirmIp> list=new ArrayList<UnconfirmIp>();
      UnconfirmIp unconfirm=new UnconfirmIp();
      Logininfo logininfo=(Logininfo) session.getAttribute("userInfo");
      String nickName=logininfo.getNickname();
      System.out.println(ipList.toString());
      Iterator it=ipList.iterator();
      while(it.hasNext()) {
    	  unconfirm=(UnconfirmIp) it.next();
    	  if(unconfirm.getSubmitter().equals(nickName))
    		  list.add(unconfirm);
      }
      
      //查看是否查询到数据库数据
      System.out.println("************************************");
      System.out.println(list.toString());
      Iterator itr=list.iterator();
      while(itr.hasNext())
      	System.out.println(itr.next().toString());
      System.out.println("************************************");
      PageInfo<UnconfirmIp> pageInfo=new PageInfo<UnconfirmIp>(list);
      Map<String,Object> map=new HashMap<String, Object>();

      map.put("code",0);
      map.put("msg","查询数据成功！");
      map.put("count",pageInfo.getTotal());
      map.put("data",pageInfo.getList());
      return map;
  }
  
  public UnconfirmIp getUnconfirmIpById(Integer id) {
    return unconfirmIpMapper.selectByPrimaryKey(id);
  }
 
  public UnconfirmIp getUnconfirmIpByAddress(String str) {
    return unconfirmIpMapper.selectByIpAddress(str);
  }
  
  public void updateUnconfirmIpById(UnconfirmIp unconfirmIp) {
	  unconfirmIpMapper.updateByPrimaryKeySelective(unconfirmIp);
  }
  //根据id删除IP地址信息，删除的ip地址录入到待审核处，审核完后才会修改
  public void deleteUnconfirmIpById(Integer id) {
	  unconfirmIpMapper.deleteByPrimaryKey(id);
  }

}
