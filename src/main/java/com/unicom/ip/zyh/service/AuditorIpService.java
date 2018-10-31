package com.unicom.ip.zyh.service;

import java.util.Map;

import com.unicom.ip.zyh.beans.UnconfirmIp;

public interface AuditorIpService {
	
	Map<String, Object> getAll(int page, int limit, UnconfirmIp unconfirmIp);

}
