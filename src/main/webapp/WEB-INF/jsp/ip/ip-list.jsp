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
        <base href="<%=basePath%>">  
    <script type="text/javascript" src="<%=basePath%>/static/jquery.min.js"></script>  
    <script type="text/javascript" src="<%=basePath%>/static/jquery.form.js"></script>  
</head>
<body>

<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">IP地址管理</a>
        <a>
          <cite>IP列表</cite></a>
      </span>
</div>
<!--根据IP地址搜索，刷新IP信息列表，添加IP信息  -->
<div class="x-body">
 	<div class="demoTable">
 		
		<div class="layui-inline">
   			<input class="layui-input" name="ipAddress" placeholder="IP地址" id="ipTableReload" autocomplete="off">
  		</div>
  		<div class="layui-inline">
   			<input class="layui-input" name="ipVlan" placeholder="Vlan号" id="ipTableReload1" autocomplete="off">
  		</div>
  		<!-- 根据IP搜索对应的IP地址信息 -->
  		<button class="layui-btn" data-type="reload" ><i class="layui-icon">&#xe615;</i></button>
  		<a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
   		 <button class="layui-btn layui-btn-normal" onclick="openWin('IP地址添加','<%=basePath%>/ip/addIp.do')">添加</button>
   		 
  		 <button class="layui-btn layui-btn-warm" onclick="openWin('IP地址Excel导入','<%=basePath%>/excelIp/importExcel.do')">excel导入</button>
  		 <form id="excelForm" method="post" action="">
		 <button  id="outputExcel" class="layui-btn layui-btn-warm" onclick="formSubmit()">导出excel</button>
		 <input type="button" id="ipMerge" class="layui-btn" value="IP合并"  name="ipMerge" >
		 <input type="button" id="ipSplit" class="layui-btn" value="IP拆分"  name="ipSplit" >
		 <input type="button" id="vlanChange" class="layui-btn" value="Vlan批量修改"  name="vlanChange" >
       	 </form>
    	
    	
	</div>
	
    <table id="infoTable" lay-filter="userTable" >
	
    </table>	

</div>
<!-- <form id="idform" name="inputForm" action="" method="post" ></form> -->
<script id="toolbar" type="text/html">
    {{#  if(d.powerid == 1 ||d.powerid == 4){ }}
    <button class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="upd" ><i class="layui-icon">&#xe642;</i>无详细资料</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-xs" lay-event="upd" onclick="openWin('IP信息','/school/user/explicit.do?id={{d.id}}')" ><i class="layui-icon">&#xe642;</i>查看详情</button>
    {{#  } }}
</script>
<script type="text/html" id="ipbar">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="openWin('IP地址信息查看','<%=basePath%>/ip/toGetIpWatch.do?id={{d.ipNumber}}')">查看</a>

    {{#  if(d.approvalStatus ==1){ }}
    <button class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="upd" ><i class="layui-icon">&#xe642;</i>修改待审核</button>
     {{# } else if(d.approvalStatus ==2 ){ }}
    <button class="layui-btn layui-btn-disabled layui-btn-xs" lay-event="upd" ><i class="layui-icon">&#xe642;</i>删除待审核</button>
	{{#  } else { }}
	<a class="layui-btn layui-btn-xs" lay-event="edit" onclick="openWin('IP地址信息编辑','<%=basePath%>/ip/toEdit.do?id={{d.ipNumber}}')">编辑</a>
  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } }}
</script>
<!-- 序号自增， -->
<script type="text/html" id="zizeng">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script>
    var t;
    var table;
    layui.use(['table','layer','laydate','form'],function () {
        table=layui.table;
        var form=layui.form;
        t=table.render({
            elem:'#infoTable',
          	 url:'<%=basePath%>/ip/getIpformList.do?timestamp='+(new Date()).valueOf(),
            cols:[[
            	 {type:'checkbox'},
            	 {field:'zizeng', width:80, title: '排序',templet:'#zizeng'},
            	 {field:'ipNumber',width: 86,title:'序号'},
                 {field:'ipStatus',width: 86,title:'状态'},
                 {field:'ipRemarks',width: 150,title:'备注'},
                 {field:'ipAddress',width: 150,title:'IP地址'},
                 {field:'ipSubnetmask',width: 150,title:'子网掩码'},
                 {field:'ipAddressnumber',width: 86,title:'地址数量'},
                 {field:'ipUsetime',width: 110,title:'起用时间',templet:'<div>{{ Format(d.ipUsetime,"yyyy-MM-dd")}}</div>'},
                 {field:'ipUsername',width: 150,title:'用户名称'},
                 {field:'ipVlan',width: 150,title:'vlan号'},
                 {field:'ipConnectingdevice',width: 150,title:'连接设备'},
                 {field:'ipPort',width: 86,title:'端口'},
                 {field:'ipRate',width: 86,title:'速率'},
                 {field:'ipAttribution',width: 150,title:'运维系统编号'},
                 {field:'ipBroadbandacceptancenumber',width: 150,title:'宽带受理编号'},
                 {field:'ipSnnumber',width: 150,title:'SN号'},
                 {field:'ipOltaddress',width: 150,title:'OLT地址'},
                 {field:'ipIomusername',width: 150,title:'IOM用户'},
                 {field:'ipInstalledaddress',width: 150,title:'装机地址'},
                 {field:'ipType',width: 150,title:'类型'},
                 {field:'ipWotvbssremarks',width: 150,title:'WOTV-BSS主产品(备注2)'},
                 {field:'ipOutputrate',width: 86,title:'上行速率'},
                 {field:'ipInputrate',width: 86,title:'下行速率'},
                 {field:'ipTerminalnumber',width: 100,title:'对应终端数'},
                 /* {field:'opt',width: 86,title:'操作',toolbar:'#toolbar'} */ 
                 {field:'opt',width: 170,title:'操作',toolbar:'#ipbar',fixed: 'right'}
            ]],
            id:'ipTable'
            ,page:true
        });

        var $ = layui.$, active = {
        	    reload: function(){
        	      var ipTableReload = $('#ipTableReload');
        	      var ipTableReload1 = $('#ipTableReload1');

        	     
        	      //执行重载
        	      
        	      table.reload('ipTable', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {
        	        	ipAddress:ipTableReload.val(),
        	        	ipVlan:ipTableReload1.val()
        	        }
        	      });
        	      }
        	    
        

        	  };
        $('.x-body .demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
          });
        
        //监听行工具事件,执行删除事件
        table.on('tool(userTable)', function(obj){ //注：tool 是工具条事件名，userTable 是 table 原始容器的属性 lay-filter="对应的值"
          var data = obj.data //获得当前行数据

          ,layEvent = obj.event; //获得 lay-event 对应的值
          if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
            	
            	   //向服务端发送删除指令
                table.reload('ipTable',{
              	  url:'<%=basePath%>/ip/toDelete.do'
              	  ,where:{
              		  id:data.ipNumber
              	  }
                });
            	   
              obj.del(); //删除对应行（tr）的DOM结构
              layer.close(index);
           
            });
          } 
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
 
 //导出excel链接
function formSubmit(){
	var postForm = document.getElementById("excelForm");
	var panduan=confirm("确认导出数据?");
	if(panduan==true){
		postForm.action = '<%=basePath%>/ip/exportExcel.do';
		postForm.submit();
	}

} 
 
 
/* Ip合并 */   
$(document).ready(function(){ 
   $('#ipMerge').click(function(){ //获取选中数据
       var checkStatus = table.checkStatus('ipTable')
       ,data = checkStatus.data;
   	if(data.length==2){
   		a1=data[0]['ipAddress'].split(".");
   		b1=data[1]['ipAddress'].split(".");
   		//alert(data[0]['ipAddress'].split(".",3));
   		if(a1[0]==b1[0]&&a1[1]==b1[1]&&a1[2]==b1[2]&&parseInt(a1[3].split("-")[1])+1==data[1]['ipAddress'].split(".")[3].split("-",1)){
   			con=confirm("确定IP："+data[0]['ipAddress']+"和"+data[1]['ipAddress']+"合并？");
       		if(con==true){
                   d1=data[0]['ipAddress'];
                   d2=data[1]['ipAddress'];
                   $('#excelForm').ajaxSubmit({    
	                            url:'<%=basePath%>/ip/toIpMerge.do?address1='+d1+'&address2='+d2,
	                            dataType: 'text',
	                            success: resultMsg,  
	                            error: errorMsg  
	                        });   
	                        function resultMsg(msg){  
	                        	  
	                            alert(msg);
	                            window.location.reload(true);
	                        }  
	                        function errorMsg(){   
	                            alert("服务器连接失败！请稍后重试");      
	                        } 
	                        
       		}
   		}else{
   			layer.alert("需要合并的两条IP不是连续，无法合并。");	
   		} 

   	}
   	else{
   		layer.alert("请选择两条待合并的IP！！！");
   	}      
     });
 //IP拆分   
   $('#ipSplit').click(function(){
		var checkStatus=table.checkStatus("ipTable");
		var data=checkStatus.data;
		if(data.length==1){
			openWin("IP拆分",'<%=basePath%>/ip/toIpSplit.do?address='+data[0]['ipAddress']);

  		 }else{
  			 layer.alert("请选择一条待拆分的IP！");
  		 }
	});
 //Vlan批量修改
 $('#vlanChange').click(function(){
	var checkStatus=table.checkStatus("ipTable");
	var data=checkStatus.data;
	if(data.length>0){
	var str=data[0]['ipNumber']+",";
	var ipVlan=data[0]['ipVlan'];
	for(var i=1;i<data.length;i++){
		str=str+data[i]['ipNumber']+",";
	}
		var con=confirm("确定对选中的"+data.length+"条IP地址Vlan号批量修改？");
		if(con==true){
		openWin("Vlan号批量修改",'<%=basePath%>/ip/toVlanChange.do?str='+str);
		}
	}
	else{
		layer.alert("请至少选择一条待修改的IP！");
	}


	 
 });
 
 
});

</script>

</html>
