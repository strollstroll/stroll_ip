package com.unicom.ip.zyh.service;

import java.util.List;
import java.util.Map;

import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.beans.Power;

public interface UserService {
     Logininfo doLogin(Logininfo record);
     Logininfo checkName(Logininfo record);
     int doUpdate(Logininfo record);
     Logininfo getById(int id);

     List<Power> getPower();

     Map<String, Object> getAll(int page, int limit, Logininfo logininfo);
}
