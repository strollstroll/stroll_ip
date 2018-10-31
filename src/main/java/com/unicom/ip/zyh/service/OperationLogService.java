package com.unicom.ip.zyh.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.unicom.ip.zyh.beans.OperationLog;

public interface OperationLogService {

            
            void addOperationLog(HttpSession session,String operationType,String operationContent);
            
            List<OperationLog> getAllOperationLog();
            
            Map<String, Object> getAll(int page, int limit, OperationLog operationLog);
            
            OperationLog getOperationLogById(Integer id);
            
            void updateOperationLogById(OperationLog operationLog);
            
            void deleteOperationLogById(Integer id);
            

}
