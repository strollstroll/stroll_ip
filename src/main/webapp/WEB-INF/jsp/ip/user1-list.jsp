<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/8
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IP信息列表</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">用户</a>
        <a>
          <cite>IP列表</cite></a>
      </span>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="username"  placeholder="IP地址" autocomplete="off" class="layui-input">
         
            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
            <a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </form>
    </div>
    <table id="infoTable" lay-filter="userTable" >

    </table>	

</div>
<div class="demoTable">
  搜索ID：
  <div class="layui-inline">
    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
<script id="toolbar" type="text/html">
    {{#  if(d.powerid == 1 ||d.powerid == 4){ }}
    <button class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="upd" ><i class="layui-icon">&#xe642;</i>无详细资料</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-xs" lay-event="upd" onclick="openWin('IP信息','/school/user/explicit.do?id={{d.id}}')" ><i class="layui-icon">&#xe642;</i>查看详情</button>
    {{#  } }}
</script>
<script type="text/html" id="ipbar">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    var t;
    layui.use(['table','layer','laydate','form'],function () {
        var table=layui.table;
        var form=layui.form;
        t=table.render({
            elem:'#infoTable',
            <%-- url:'<%=basePath%>/user/getList.do', --%>
            url:'<%=basePath%>/ip/getIpformList.do',
            cols:[[
                {type:'checkbox'},
                {field:'ipNumber',width:'7%',title:'序号',sort:false},
                {field:'ipStatus',width:'7%',title:'状态'},
                {field:'ipRemarks',width: '12%',title:'备注'},
                {field:'ipAddress',width: '12%',title:'IP地址'},
                {field:'ipSubnetmask',width: '12%',title:'子网掩码'},
                {field:'ipAddressnumber',width: '7%',title:'地址数量'},
                {field:'ipUsetime',width: '12%',title:'起用时间'},
                {field:'ipUsername',width: '12%',title:'用户名称'},
                {field:'ipVlan',width: '15%',title:'vlan号'},
                {field:'ipConnectingdevice',width: '15%',title:'连接设备'},
                {field:'ipPort',width: '7%',title:'端口'},
                {field:'ipRate',width: '7%',title:'速率'},
                {field:'ipAttribution',width: '15%',title:'运维系统编号'},
                {field:'ipBroadbandacceptancenumber',width: '12%',title:'宽带受理编号'},
                {field:'ipSnnumber',width: '12%',title:'SN号'},
                {field:'ipOltaddress',width: '15%',title:'OLT地址'},
                {field:'ipIomusername',width: '12%',title:'IOM用户'},
                {field:'ipInstalledaddress',width: '15%',title:'装机地址'},
                {field:'ipType',width: '12%',title:'类型'},
                {field:'ipWotvbssremarks',width: '15%',title:'WOTV-BSS主产品(备注2)'},
                {field:'ipOutputrate',width: '7%',title:'上行速率'},
                {field:'ipInputrate',width: '7%',title:'下行速率'},
                {field:'ipTerminalnumber',width: '9%',title:'对应终端数'},
                {field:'opt',width: '7%',title:'操作',toolbar:'#toolbar'}  
            ]],
            id:'testReload'
            ,page:true
        });
        var $ = layui.$, active = {
        	    reload: function(){
        	      var demoReload = $('#demoReload');
        	      
        	      //执行重载
        	      table.reload('testReload', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {
        	          key: {
        	        	  ipNumber: demoReload.val()
        	          }
        	        }
        	      });
        	    }
        	  };
        	  
        	  $('.demoTable .layui-btn').on('click', function(){
        	    var type = $(this).data('type');
        	    active[type] ? active[type].call(this) : '';
        	  });
    });
    function openWin(title,url,w,h){
        if (title == null || title == '') {
            title=false;
        };
        if (url == null || url == '') {
            url="404.html";
        };
        if (w == null || w == '') {
            w=($(window).width()*0.9);
        };
        if (h == null || h == '') {
            h=($(window).height() - 50);
        };
        layer.open({
            type: 2,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade:0.4,
            title: title,
            content: url,
            end:function () {
                t.reload()
            }
        });
    }
   
</script>
</body>

</html>
