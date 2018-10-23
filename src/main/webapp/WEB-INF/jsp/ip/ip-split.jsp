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

<body>
  <div    class="x-body" align="center">
  <form id="idForm" method="post" class="layui-form"  action="">
  <div  class="layui-form-item" align="center">
  	
  				<div class="layui-form-item">
  				<div style="width:310px" align="center">
				    <label class="layui-form-label">IP地址</label>
				    <div class="layui-input-inline">
				      <input type="text" name="ipAddress" lay-verify="ipWatch" value="${ip.ipAddress}" autocomplete="off" readonly="readonly" class="layui-input">
				    </div>
				  </div>
				  </div>
				<div class="layui-form-item">
				<div style="width:310px" align="center">
				    <label class="layui-form-label">子网掩码</label>
				    <div class="layui-input-inline">
				      <input type="text" name="ipSubnetmask" lay-verify="ipWatch" value="${ip.ipSubnetmask}" autocomplete="off" readonly="readonly" class="layui-input">
				    </div>
				    </div>
				  </div>
				<div class="layui-form-item">
				<div style="width:310px" align="center">
				    <label class="layui-form-label">地址数量</label>
				    <div class="layui-input-inline">
				      <input type="text" name="ipAddressnumber" lay-verify="ipWatch" value="${ip.ipAddressnumber}" autocomplete="off" readonly="readonly" class="layui-input">
				    </div>
				    </div>
				  </div>
  				<div  class="layui-form-item" align="center" >
  				<div style="width:310px" align="center">
				    <label class="layui-form-label">拆分的第一条IP的地址数量：</label>
				    <div class="layui-input-inline">
				      <input type="text" name="splitNum" lay-verify="ipWatch"  autocomplete="off"  class="layui-input">
				    </div>
				    </div>
				  </div>

   <div class="layui-form-item">
    <div class="layui-input-block">
      <input class="layui-btn" id="update" type="button" value="确认">
    </div>
  </div>
  </div>
  </form>
</div>
</body>

<script>
    layui.use(['form','layer','laydate'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate;
        laydate.render({
            elem: '#date1'
          });
    });
    
  $(document).ready(function(){
	  $('#update').click(function(){
		var address1=$('input[name="ipAddress"]').val();
		var num=$('input[name="splitNum"]').val();
		var ziNum=$('input[name="ipAddressnumber"]').val();
		var arrAddress1=address1.split('.');
		var arrAddress2=arrAddress1[3].split("-");
		var num1=parseInt(arrAddress2[0])+parseInt(num)-1;
		var new1=address1.split('-',1)+'-'+num1;
		
		var num2=num1+1;
		var new2=arrAddress1[0]+'.'+arrAddress1[1]+'.'+arrAddress1[2]+'.'+num2+'-'+arrAddress2[1];
		
		var ziNum1=255-parseInt(num)+1;
		var ziNum2=255+parseInt(num)-parseInt(ziNum)+1;
		var ziNum3="255.255.255."+ziNum1;
		var ziNum4="255.255.255."+ziNum2;
		if(parseInt(num)>=parseInt(ziNum)||parseInt(num)<0){
			alert("第一条地址数量请在 0 到 "+ziNum+" 范围之内！");
		}else if(""==num||parseInt(num)==0){
			alert("第一条地址数量不能为空或者0！");
		}else if(Math.floor(num)!=num){
			alert("请输入整数！");
		}
		else{
		con=confirm("确认拆分的信息\n第一条\nIP地址："+new1+"\n子网掩码："+ziNum3+"\n地址数量："+num
				+"\n第二条\nIP地址："+new2+"\n子网掩码："+ziNum4+"\n地址数量："+(parseInt(ziNum)-parseInt(num)));
		
		if(con==true){
            $('#idForm').ajaxSubmit({ 
                url:'<%=basePath%>/ip/doSplit.do?address1='+address1+'&num='+num,
                dataType: 'text',
                success: resultMsg,  
                error: errorMsg  
            });   
            function resultMsg(msg){  
            	  
                alert(msg);
                window.parent.location.reload(true);
            }  
            function errorMsg(){   
                alert("服务器连接失败！请稍后重试");      
            }
		  }
		}
	  });
	
  });  
    
</script>
</html>