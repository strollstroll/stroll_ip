package com.haiwen.school.zx.service;

import java.util.Map;

import com.haiwen.school.zx.beans.UnconfirmIp;

public interface AuditorIpService {
	
	Map<String, Object> getAll(int page, int limit, UnconfirmIp unconfirmIp);

}
