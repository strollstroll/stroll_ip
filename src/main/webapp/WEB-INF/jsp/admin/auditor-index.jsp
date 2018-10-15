<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎IP地址管理系统</title>
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
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="#">IP地址管理审核</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${userInfo.nickname}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('修改密码','/school/login/toedit.do?id=${userInfo.id}','600','500')">修改密码</a></dd>
                <dd><a href="<%=basePath%>/user/exit.do">退出</a></dd>
            </dl>
        </li>
        <!-- ********这个前台页面展示没有用处********哈哈哈 -->
        <!-- <li class="layui-nav-item to-index"><a href="/">前台首页</a></li> -->
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->

<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>IP地址信息</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="<%=basePath%>/auditorIp/toIpformList.do">   
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>IP地址列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="<%=basePath%>/auditorIp/toUnconfirmIpList.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>IP修改审核</cite>
                        </a>
                    </li >
                     <li>
                        <a _href="<%=basePath%>/ip/toHistoryIpList.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>IP地址历史记录</cite>
                        </a>
                    </li >
                </ul>
            </li>
        </ul>
    </div>
</div>

<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='<%=basePath%>index/welcome.do' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">欢迎使用IP管理系统</div>
</div>
</body>
<script>
    layui.use(['layer'],function () {
        var layer=layui.layer;
        if("${msg}"!=""){
            layer.msg('${msg}',{icon:2,time:1500},function () {
                location.href="/school/"
            })   }
    })
</script>
</html>