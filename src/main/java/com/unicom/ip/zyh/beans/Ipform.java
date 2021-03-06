package com.unicom.ip.zyh.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Ipform {
    private String ipAddress;

    private Integer ipNumber;

    private String ipStatus;

    private String ipRemarks;

    private String ipSubnetmask;

    private Integer ipAddressnumber;

   @JsonFormat(pattern="yyyy-MM-dd")
    private Date ipUsetime;

    private String ipUsername;

    private String ipVlan;

    private String ipConnectingdevice;

    private String ipPort;

    private String ipRate;

    private String ipAttribution;

    private String ipBroadbandacceptancenumber;

    private String ipSnnumber;

    private String ipOltaddress;

    private String ipIomusername;

    private String ipInstalledaddress;

    private String ipType;

    private String ipWotvbssremarks;

    private Integer ipOutputrate;

    private Integer ipInputrate;

    private Integer ipTerminalnumber;
    
    //审核状态，0和空都是没有在待审核表里的，1是在审核表里。
    private Integer approvalStatus;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Integer getIpNumber() {
        return ipNumber;
    }

    public void setIpNumber(Integer ipNumber) {
        this.ipNumber = ipNumber;
    }

    public String getIpStatus() {
        return ipStatus;
    }

    public void setIpStatus(String ipStatus) {
        this.ipStatus = ipStatus == null ? null : ipStatus.trim();
    }

    public String getIpRemarks() {
        return ipRemarks;
    }

    public void setIpRemarks(String ipRemarks) {
        this.ipRemarks = ipRemarks == null ? null : ipRemarks.trim();
    }

    public String getIpSubnetmask() {
        return ipSubnetmask;
    }

    public void setIpSubnetmask(String ipSubnetmask) {
        this.ipSubnetmask = ipSubnetmask == null ? null : ipSubnetmask.trim();
    }

    public Integer getIpAddressnumber() {
        return ipAddressnumber;
    }

    public void setIpAddressnumber(Integer ipAddressnumber) {
        this.ipAddressnumber = ipAddressnumber;
    }
    public Date getIpUsetime() {
        return ipUsetime;
    }

    public void setIpUsetime(Date ipUsetime) {
        this.ipUsetime = ipUsetime;
    }

    public String getIpUsername() {
        return ipUsername;
    }

    public void setIpUsername(String ipUsername) {
        this.ipUsername = ipUsername == null ? null : ipUsername.trim();
    }

    public String getIpVlan() {
        return ipVlan;
    }

    public void setIpVlan(String ipVlan) {
        this.ipVlan = ipVlan == null ? null : ipVlan.trim();
    }

    public String getIpConnectingdevice() {
        return ipConnectingdevice;
    }

    public void setIpConnectingdevice(String ipConnectingdevice) {
        this.ipConnectingdevice = ipConnectingdevice == null ? null : ipConnectingdevice.trim();
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort == null ? null : ipPort.trim();
    }

    public String getIpRate() {
        return ipRate;
    }

    public void setIpRate(String ipRate) {
        this.ipRate = ipRate == null ? null : ipRate.trim();
    }

    public String getIpAttribution() {
        return ipAttribution;
    }

    public void setIpAttribution(String ipAttribution) {
        this.ipAttribution = ipAttribution == null ? null : ipAttribution.trim();
    }

    public String getIpBroadbandacceptancenumber() {
        return ipBroadbandacceptancenumber;
    }

    public void setIpBroadbandacceptancenumber(String ipBroadbandacceptancenumber) {
        this.ipBroadbandacceptancenumber = ipBroadbandacceptancenumber == null ? null : ipBroadbandacceptancenumber.trim();
    }

    public String getIpSnnumber() {
        return ipSnnumber;
    }

    public void setIpSnnumber(String ipSnnumber) {
        this.ipSnnumber = ipSnnumber;
    }

    public String getIpOltaddress() {
        return ipOltaddress;
    }

    public void setIpOltaddress(String ipOltaddress) {
        this.ipOltaddress = ipOltaddress == null ? null : ipOltaddress.trim();
    }

    public String getIpIomusername() {
        return ipIomusername;
    }

    public void setIpIomusername(String ipIomusername) {
        this.ipIomusername = ipIomusername == null ? null : ipIomusername.trim();
    }

    public String getIpInstalledaddress() {
        return ipInstalledaddress;
    }

    public void setIpInstalledaddress(String ipInstalledaddress) {
        this.ipInstalledaddress = ipInstalledaddress == null ? null : ipInstalledaddress.trim();
    }

    public String getIpType() {
        return ipType;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType == null ? null : ipType.trim();
    }

    public String getIpWotvbssremarks() {
        return ipWotvbssremarks;
    }

    public void setIpWotvbssremarks(String ipWotvbssremarks) {
        this.ipWotvbssremarks = ipWotvbssremarks == null ? null : ipWotvbssremarks.trim();
    }

    public Integer getIpOutputrate() {
        return ipOutputrate;
    }

    public void setIpOutputrate(Integer ipOutputrate) {
        this.ipOutputrate = ipOutputrate;
    }

    public Integer getIpInputrate() {
        return ipInputrate;
    }

    public void setIpInputrate(Integer ipInputrate) {
        this.ipInputrate = ipInputrate;
    }

    public Integer getIpTerminalnumber() {
        return ipTerminalnumber;
    }

    public void setIpTerminalnumber(Integer ipTerminalnumber) {
        this.ipTerminalnumber = ipTerminalnumber;
    }

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	@Override
	public String toString() {
		return "序号：" + ipNumber + ",状态：" + ipStatus + ",备注：" + ipRemarks + ",IP地址：" + 
				ipAddress + ",子网掩码：" + ipSubnetmask + ",地址数量：" + ipAddressnumber + ",起用时间：" + 
				ipUsetime + ",用户名称：" + ipUsername + ",vlan号：" + ipVlan + ",连接设备：" + ipConnectingdevice +
				",端口：" + ipPort + ",速率：" + ipRate + ",运维系统编号：" + ipAttribution + ",宽带受理编号：" + ipBroadbandacceptancenumber + 
				",SN号=：" + ipSnnumber + ",OLT地址：" + ipOltaddress + ",IOM用户：" + ipIomusername + ",装机地址：" + ipInstalledaddress + ",类型：" + 
				ipType + ",WOTV-BSS主产品(备注2)：" + ipWotvbssremarks + ",上行速率：" + ipOutputrate + ",下行速率：" + ipInputrate + ",对应终端数：" + ipTerminalnumber;
	}

}