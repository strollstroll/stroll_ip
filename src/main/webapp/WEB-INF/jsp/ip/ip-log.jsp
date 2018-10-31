<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="stylesheet" href="<%=basePath%>/X-admin/lib/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">IP地址管理</a>
        <a>
          <cite>IP操作日志</cite></a>
      </span>
</div>
<!--根据IP地址搜索，刷新IP信息列表，添加IP信息  -->
<div class="x-body">
 	<div class="demoTable">
		<div class="layui-inline">
   			<input class="layui-input" name="operationUser" placeholder="操作人" id="logTableReload" autocomplete="off">
  		</div>
  		<div class="layui-inline">
   			<input class="layui-input" name="operationTime" placeholder="操作时间" id="logTableReload1" autocomplete="off">
  		</div>
  	<!-- 根据IP搜索对应的操作日志信息 -->
  	<button class="layui-btn" data-type="reload" ><i class="layui-icon">&#xe615;</i></button>
  	<a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
    <table id="infoTable" lay-filter="logTable" >
	
    </table>	

</div>

<script type="text/html" id="ipbar">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="openWin('IP地址信息查看','<%=basePath%>/ip/toGetIpWatch.do?id={{d.ipNumber}}')">查看</a>

</script>
<!-- 序号自增， -->
<script type="text/html" id="zizeng">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script>
    var t;
    layui.use(['table','layer','laydate','form'],function () {
        var table=layui.table;
        var form=layui.form;
        t=table.render({
            elem:'#infoTable',
          	 url:'<%=basePath%>/log/getOperationLogList.do?timestamp='+(new Date()).valueOf(),
            cols:[[
            	 {type:'checkbox'},
            	 {field:'zizeng', width:80, title: '排序',templet:'#zizeng'},
                 {field:'operationUser',width: 100,title:'操作人'},
                 {field:'operationTime',width: 165,title:'操作时间'},
                 {field:'operationType',width: 120,title:'操作类型'},
                 {field:'operationContent',width: 500,title:'操作详细'},
                 {field:'opt',width: 70,title:'操作',toolbar:'#ipbar',fixed: 'right'}
            ]],
            id:'logTable'
            ,page:true
        });

        var $ = layui.$, active = {
        	    reload: function(){
        	      var logTableReload = $('#logTableReload');
        	      var logTableReload1 = $('#logTableReload1');
        	     
        	      //执行重载
        	      table.reload('logTable', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {
        	          
        	        	  operationUser:logTableReload.val(),
        	        	  operationTime:logTableReload1.val()
        	        }
        	      });
        	    }
        	  };
        $('.x-body .demoTable .layui-btn').on('click', function(){
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
   

    
   /* 处理时间问题 */ 
 // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
 // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
 // 例子：   
 //Format("2016-10-04 8:9:4.423","yyyy-MM-dd hh:mm:ss.S") ==> 2016-10-04 08:09:04.423   
 // Format("1507353913000","yyyy-M-d h:m:s.S")      ==> 2017-10-7 13:25:13.0  
 function Format(datetime,fmt) {
   if (parseInt(datetime)==datetime) {
     if (datetime.length==10) {
       datetime=parseInt(datetime)*1000;
     } else if(datetime.length==13) {
       datetime=parseInt(datetime);
     }
   }
   datetime=new Date(datetime);
   var o = {
   "M+" : datetime.getMonth()+1,                 //月份   
   "d+" : datetime.getDate(),                    //日   
   "h+" : datetime.getHours(),                   //小时   
   "m+" : datetime.getMinutes(),                 //分   
   "s+" : datetime.getSeconds(),                 //秒   
   "q+" : Math.floor((datetime.getMonth()+3)/3), //季度   
   "S"  : datetime.getMilliseconds()             //毫秒   
   };   
   if(/(y+)/.test(fmt))   
   fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));   
   for(var k in o)   
   if(new RegExp("("+ k +")").test(fmt))   
   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
   return fmt;
 }
 
</script>

</html>
