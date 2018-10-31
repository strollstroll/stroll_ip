<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>查看IP地址</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
  <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
  <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
  <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
  <link rel="stylesheet" href="<%=basePath%>/X-admin/lib/layui/css/layui.css"  media="all">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

   <body>
    <div class="x-body">
    <div class="layui-form-item">
    <label class="layui-form-label">序号</label>
    <div class="layui-input-block">
      <input type="text" name="ipNumber" lay-verify="ipWatch" value="${ip.ipNumber}" disabled="disabled" value="${ip.ipStatus}" autocomplete="off" placeholder="待审核，还未分配序号" class="layui-input">
    </div>
  	</div>
 	<div class="layui-form-item">
    <label class="layui-form-label">状态</label>
    <div class="layui-input-block">
      <input type="text" name="ipStatus" lay-verify="ipWatch" value="${ip.ipStatus}" autocomplete="off" placeholder="请输入状态" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <input type="text" name="ipRemarks" lay-verify="ipWatch" value="${ip.ipRemarks}" autocomplete="off" placeholder="请输入备注" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">IP地址</label>
    <div class="layui-input-block">
      <input type="text" name="ipAddress" lay-verify="ipWatch" value="${ip.ipAddress}" autocomplete="off" placeholder="请输入IP地址" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">子网掩码</label>
    <div class="layui-input-block">
      <input type="text" name="ipSubnetmask" lay-verify="ipWatch" value="${ip.ipSubnetmask}" autocomplete="off" placeholder="请输入子网掩码" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">地址数量</label>
    <div class="layui-input-block">
      <input type="text" name="ipAddressnumber" lay-verify="ipWatch" value="${ip.ipAddressnumber}" autocomplete="off" placeholder="请输入地址数量" class="layui-input">
    </div>
  </div>
 <div class="layui-form-item">
     <label class="layui-form-label">起用时间</label>
     <div class="layui-input-block">
       <input type="text" name="ipUsetime" lay-verify="ipWatch" value="<fmt:formatDate value='${ip.ipUsetime}' pattern='yyyy-MM-dd '/>" autocomplete="off" class="layui-input" id="date1" placeholder="yyyy-MM-dd">
     </div>
 </div>
<div class="layui-form-item">
    <label class="layui-form-label">用户名称</label>
    <div class="layui-input-block">
      <input type="text" name="ipUsername" lay-verify="ipWatch" value="${ip.ipUsername}" autocomplete="off" placeholder="请输入用户名称" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">Vlan号</label>
    <div class="layui-input-block">
      <input type="text" name="ipVlan" lay-verify="ipWatch" value="${ip.ipVlan}" autocomplete="off" placeholder="请输入Vlan号" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">连接设备</label>
    <div class="layui-input-block">
      <input type="text" name="ipConnectingdevice" lay-verify="ipWatch" value="${ip.ipConnectingdevice}" autocomplete="off" placeholder="请输入连接设备" class="layui-input">
    </div>
  </div>
 
<div class="layui-form-item">
    <label class="layui-form-label">端口</label>
    <div class="layui-input-block">
      <input type="text" name="ipPort" lay-verify="ipWatch" value="${ip.ipPort}" autocomplete="off" placeholder="请输入端口" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">速率</label>
    <div class="layui-input-block">
      <input type="text" name="ipRate" lay-verify="ipWatch" value="${ip.ipRate}" autocomplete="off" placeholder="请输入速率" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">运维系统编号</label>
    <div class="layui-input-block">
      <input type="text" name="ipAttribution" lay-verify="ipWatch" value="${ip.ipAttribution}" autocomplete="off" placeholder="请输入运维系统编号" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">宽带受理编号</label>
    <div class="layui-input-block">
      <input type="text" name="ipBroadbandacceptancenumber" lay-verify="ipWatch" value="${ip.ipBroadbandacceptancenumber}" autocomplete="off" placeholder="请输入宽带受理编号" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">SN号</label>
    <div class="layui-input-block">
      <input type="text" name="ipSnnumber" lay-verify="ipWatch" value="${ip.ipSnnumber}" autocomplete="off" placeholder="请输入SN号" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">OLT地址</label>
    <div class="layui-input-block">
      <input type="text" name="ipOltaddress" lay-verify="ipWatch" value="${ip.ipOltaddress}" autocomplete="off" placeholder="请输入OLT地址" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">IOM用户</label>
    <div class="layui-input-block">
      <input type="text" name="ipIomusername" lay-verify="ipWatch" value="${ip.ipIomusername}" autocomplete="off" placeholder="请输入IOM用户" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">装机地址</label>
    <div class="layui-input-block">
      <input type="text" name="ipInstalledaddress" lay-verify="ipWatch" value="${ip.ipInstalledaddress}" autocomplete="off" placeholder="请输入装机地址" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">类型</label>
    <div class="layui-input-block">
      <input type="text" name="ipType" lay-verify="ipWatch" value="${ip.ipType}" autocomplete="off" placeholder="请输入类型" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">WOTV-BSS主产品</label>
    <div class="layui-input-block">
      <input type="text" name="ipWotvbssremarks" lay-verify="ipWatch" value="${ip.ipWotvbssremarks}" autocomplete="off" placeholder="请输入WOTV-BSS主产品" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">上行速率</label>
    <div class="layui-input-block">
      <input type="text" name="ipOutputrate" lay-verify="ipWatch" value="${ip.ipOutputrate}" autocomplete="off" placeholder="请输入上行速率" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">下行速率</label>
    <div class="layui-input-block">
      <input type="text" name="ipInputrate" lay-verify="ipWatch" value="${ip.ipInputrate}" autocomplete="off" placeholder="请输入下行速率" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">对应终端数</label>
    <div class="layui-input-block">
      <input type="text" name="ipTerminalnumber" lay-verify="ipWatch" value="${ip.ipTerminalnumber}" autocomplete="off" placeholder="请输入对应终端数" class="layui-input">
    </div>
  </div>
    </div>

</body>