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
    <title>IP地址Vlan号批量修改</title>
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
  <form class="layui-form">
    <div class="layui-form-item">
    <label class="layui-form-label">待修改序号集</label>
    <div class="layui-input-block">
      <input type="text" name="ipNumber" lay-verify="ipWatch" value="${str}" autocomplete="off"  class="layui-input">
    </div>
  	</div>
  <div class="layui-form-item">
    <label class="layui-form-label">新Vlan号</label>
    <div class="layui-input-block">
      <input type="text" name="ipVlan" lay-verify="ipWatch"  autocomplete="off" placeholder="请输入新Vlan号" class="layui-input">
    </div>
  	</div>

   <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="update">修改</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
                  //监听提交
        form.on('submit(update)', function(data){
            console.log(data);
            $.ajax({
                url:'<%=basePath%>/ip/doVlanChange.do',
                data:data.field,
                method:'post',
                success:function (data) {
                   // res=JSON.parse(res);
                    if(data==0){
                        layer.alert("修改成功", {icon:6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                    }else{
                        layer.alert('修改失败！',{icon:5})
                    }
                },
                error:function (err) {
                    layer.alert('与服务器链接失败，请稍后再试');
                    console.log(err);
                }
            });
            return false;
        });



    });
</script>
</html>