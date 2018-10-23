<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
%>  
<html>  
  <head>  
  	<meta charset="UTF-8">
    <title>IP地址批量添加</title>
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
    <script type="text/javascript">

            //ajax 方式上传文件操作  
             $(document).ready(function(){  
                $('#btn').click(function(){  
                    if(checkData()){  
                        $('#form1').ajaxSubmit({    
                            url:'<%=basePath%>/excelIp/ajaxUpload.do',  
                            dataType: 'text',
                            success: resultMsg,  
                            error: errorMsg  
                        });   
                        function resultMsg(msg){  
                        	  
                            $("#upfile").val("");
                            alert(msg);
                        }  
                        function errorMsg(){   
                            alert("导入excel出错！ 可能IP地址重复或者导入的excel没有按照格式要求填写！");      
                        } 
                      
                    }  
                });
                
                /* 导入到正式表中（ipform） */
                $('#importI').click(function(){  
                	/* var postForm = document.getElementById("importId"); */
                	var panduan=confirm("确认导入数据?");
                	if(panduan==true){
                        $('#form1').ajaxSubmit({    
                            url:'<%=basePath%>/excelIp/importIpform.do',  
                            dataType: 'text',
                            success: resultMsg,  
                            error: errorMsg  
                        });   
                        function resultMsg(msg){  
                        	  
                            alert(msg);
                        }  
                        function errorMsg(){   
                            alert("服务器连接失败！请稍后重试。。。");      
                        } 
                	}
                      
                }); 
                /* 导入到正式表中（ipform） */
                $('#delAll').click(function(){  
                	var panduan=confirm("确认删除下面所有数据?");
                	if(panduan==true){
                        $('#form1').ajaxSubmit({    
                            url:'<%=basePath%>/excelIp/toDeleteAllExcelIp.do',  
                            dataType: 'text',
                            success: resultMsg,  
                            error: errorMsg  
                        });   
                        function resultMsg(msg){  
                        	  
                            alert(msg);
                        }  
                        function errorMsg(){   
                            alert("服务器连接失败！请稍后重试。。。");      
                        } 
                	}
                      
                }); 
             });  
               
             //JS校验form表单信息  
             function checkData(){  
                var fileDir = $("#upfile").val();  
                var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
                if("" == fileDir){  
                    alert("选择需要导入的Excel文件！");  
                   	return false;
                }  
                if(".xls" != suffix && ".xlsx" != suffix ){  
                    alert("选择Excel格式的文件导入！");  
                    return false;  
                }  
                return true;  
             }  
    </script>   
  </head>  
    
  <body>
  <div>&nbsp;</div>  
  <!-- <div>1.通过简单的form表单提交方式，进行文件的上</br> 2.通过jquery.form.js插件提供的form表单一步提交功能 </div></br>   -->
 
    <form method="POST"  enctype="multipart/form-data" id="form1" action="">  
        <table align="center" width="100%">  
         <tr> 
         	  
            <td align="center">上传文件:
             <input id="upfile" type="file" name="upfile">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="layui-btn layui-btn-small" type="button" value="上传文件" id="btn" name="btn" ></td>

         </tr> 
         <tr><td><hr></td></tr>
         <tr>
			<td align="center">Excel批量导入的数据，请刷新查看，确认无误后全部提交到正式数据库中</td>
		</tr>
         <tr>
         	<td align="center">
  			<a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
            <input type="button"  id="importI" class="layui-btn layui-btn-normal" value="全部导入">
            <input type="button"  id="delAll" class="layui-btn layui-btn-warm" value="删除全部">
            </td>
         </tr> 
        </table> 
    </form> 


 <div class="x-body">
    <table id="infoTable" lay-filter="userTable" >
    </table>	
</div>

<script type="text/html" id="ipbar">
  	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="openWin('IP地址信息查看','<%=basePath%>/ip/toGetIpWatch.do?id={{d.ipNumber}}')">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit" onclick="openWin('IP地址信息编辑','<%=basePath%>/excelIp/toEdit.do?id={{d.ipId}}')">编辑</a>
  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

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
          	 url:'<%=basePath%>/excelIp/getExcelIpList.do?timestamp='+(new Date()).valueOf(),
            cols:[[
            	 /* {type:'checkbox'}, */
            	 {field:'zizeng', width:80, title: '排序',templet:'#zizeng'},
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
    /*   form.on('submit(search)',function (data) {
            console.log(data);
            t.reload({
                where:data.field
            });
            return false;
        }); */

        var $ = layui.$, active = {
        	    reload: function(){
        	      var ipTableReload = $('#ipTableReload');
        	     
        	      //执行重载
        	      table.reload('ipTable', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {
        	          
        	        	  ipAddress:ipTableReload.val()
        	          
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
              	  url:'<%=basePath%>/excelIp/toDelete.do'
              	  ,where:{
              		  id:data.ipId
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
 
 
</script>
 
 
      
  </body>
