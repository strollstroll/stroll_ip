<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>教师页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
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
            <label for="username" class="layui-form-label">
                <span class="x-red">*</span>登录名
            </label>
            <div class="layui-input-inline">
                <input type="hidden" value="${student.id}" id="id" name="id">
                <input type="text"  id="username" name="username" required="" lay-verify="required"
                       autocomplete="off" value="${userInfo.username}" class="layui-input" disabled >
            </div>
        </div>

        <div class="layui-form-item">
            <label for="stuname" class="layui-form-label">
                <span class="x-red">*</span>姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${student.stuname}" id="stuname" name="stuname"  required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="stuphone" class="layui-form-label">
                <span class="x-red">*</span>手机号
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${student.stuphone}" id="stuphone" name="stuphone"  required="phone" lay-verify="required|phone"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="age" class="layui-form-label">
                年龄
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${student.age}" id="age" name="age" required="age" lay-verify="age"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio"name="sex" value="男" title="男"
                <c:if test="${student.sex!='女'}"> checked </c:if> >
                <input type="radio" name="sex" value="女" title="女"
                <c:if test="${student.sex=='女'}"> checked </c:if> >
            </div>
        </div>
        <div class="layui-form-item">
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                <c:if test="${! empty teacher}">修改</c:if>
                <c:if test="${empty teacher}">提交</c:if>
            </button>
        </div>
    </form>
</div>

</body>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;
        form.verify({
            age: function(value){
                if(value <15 || value>99){
                    return '年龄范围只能15~99岁';
                }
            }

        });

        //监听提交
        form.on('submit(add)', function(data){
            console.log(data);
            $.ajax({
                url:'<%=basePath%>/student/doUpdate.do',
                data:data.field,
                method:'post',
                success:function (res) {
                    res=JSON.parse(res);
                    if(res.code>0){
                        layer.alert("提交成功", {icon:6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                    }else{
                        layer.alert('更新失败！',{icon:5})
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