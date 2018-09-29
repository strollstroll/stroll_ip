package com.haiwen.school.zx.service;

import com.haiwen.school.zx.beans.Logininfo;
import com.haiwen.school.zx.beans.Power;

import java.util.List;
import java.util.Map;

public interface UserService {
     Logininfo doLogin(Logininfo record);
     Logininfo checkName(Logininfo record);
     int doUpdate(Logininfo record);
     Logininfo getById(int id);

     List<Power> getPower();

     Map<String, Object> getAll(int page, int limit, Logininfo logininfo);
}
