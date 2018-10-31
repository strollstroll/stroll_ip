package com.unicom.ip.zyh.beans;

public class OperationLog {
	private int operationId;
	
	private String operationUser;
	
	private String operationTime;

	private String operationType;
	
	private String operationContent;
	
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getOperationUser() {
		return operationUser;
	}
	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationContent() {
		return operationContent;
	}
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}
	@Override
	public String toString() {
		return "OperationLog [operationId=" + operationId + ", operationUser=" + operationUser + ", operationTime="
				+ operationTime +", operationType=" + operationType +  ", operationContent=" + operationContent + "]";
	}
	
}
