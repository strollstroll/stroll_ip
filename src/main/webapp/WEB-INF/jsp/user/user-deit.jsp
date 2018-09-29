<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户信息更改</title>
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
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="x-red">*</span>账号
            </label>
            <div class="layui-input-inline">
                <input type="hidden" value="${user.id}" name="id" id="id">
                <input value="${user.username}" type="text" id="username" name="username" required=""
                <c:if test="${! empty user}"> disabled </c:if>
                <c:if test="${empty user}"> lay-verify="username" </c:if>
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>登陆账号<c:if test="${empty user}">(设置之后不可修改)</c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="nickname" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input value="${user.nickname}" type="text" id="nickname" name="nickname" required="" lay-verify="nikename"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <c:if test="${! empty user}">
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>原密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="oldrepass" name="oldrepass" required="" lay-verify="oldrepass"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

        </c:if>
        <div class="layui-form-item">
            <label for="password" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="password" name="password" required="" lay-verify="pass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                <c:choose>
                    <c:when test="${!empty user}">
                        更改
                    </c:when>
                    <c:otherwise>
                        注册
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //自定义验证规则
        form.verify({
            oldrepass:function (value) {
                if(value!="${user.password}"){
                    return '密码错误';
                }
            },
            username:function (value) {
                var r;
                $.ajax({
                    url:'<%=basePath%>/user/checkName.do',
                    data:{
                        username:value
                    },
                    async:false,
                    success:function (res) {
                        r=JSON.parse(res);
                    },
                    error:function (e) {
                        layer.alert("与服务器连接失败，请稍后再试...");
                        console.log(e);
                    }
                });
                if(r.code==0){
                    return '用户名已存在';
                }
            },
            nikename: function(value){
                if(value.length < 2){
                    return '昵称至少得2个字符啊';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_repass').val()!=$('#password').val()){
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){
            $.ajax({
                url:'<%=basePath%>/user/doupdate.do',
                data:data.field,
                method:'post',
                success:function (res) {
                    res=JSON.parse(res);
                    if(res.code==1){
                        layer.alert("更改成功,请重新登录 ",{icon:6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            window.parent.location.href="/school/";
                            parent.layer.close(index);
                        })
                    }else {
                        layer.alert("更改失败",{icon:5},function () {
                            location.reload();
                        })
                    }


                },
                error:function (err) {
                    layer.alert("与服务器连接失败，请稍后再试...");
                    console.log(err);
                }
            });
            return false;
        });


    });
</script>
</body>
</html>
