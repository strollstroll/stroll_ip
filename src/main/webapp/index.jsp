<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IP地址管理平台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="X-admin/css/font.css">
    <link rel="stylesheet" href="X-admin/css/xadmin.css">
    <script type="text/javascript" src="static/jquery-3.2.1.js"></script>
    <script src="X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="X-admin/js/xadmin.js"></script>

</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">IP地址管理平台</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
        <a onclick="x_admin_show('用户注册','/school/login/toedit.do','600','500')" style="float:right">暂无账号？注册账号</a>
    </form>
</div>

<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){

                console.log(data);
                $.ajax({
                    url:"login/doLogin.do",
                    data:data.field,
                    method:"post",
                    success:function (res) {
                        res=JSON.parse(res);
                        if(res.code==1){
                            location.href="index/index.do";
                        }else {
                            layer.msg('用户名或密码不正确!');
                        }
                    },
                    error:function (err) {
                        alert("与服务器连接失败，请稍后重试。。。。。");
                        console.log(err);
                    }
                });
                return false;
            });
        });
    })


</script>

</body>
</html>