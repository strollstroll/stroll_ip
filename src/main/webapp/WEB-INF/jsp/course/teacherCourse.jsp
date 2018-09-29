<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">课程管理</a>
        <a>
          <cite>课程列表</cite></a>
      </span>

</div>
<div class="x-body">
    <div class="layui-row">
        <div class="layui-row">
            <form class="layui-form layui-col-md12 x-so">
                <input class="layui-input" autocomplete="off" placeholder="开课时间" name="startdate" id="startdate">
                <input type="text" name="coursename"  placeholder="课程名" autocomplete="off" class="layui-input">
                <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                <a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                    <i class="layui-icon" style="line-height:30px">ဂ</i></a>
            </form>
        </div>
    </div>
    <xblock>
        <button class="layui-btn" onclick="openWin('添加课程','<%=basePath%>/course/toedit.do')"><i class="layui-icon"></i>开课申请</button>
        <span class="x-right" style="line-height:40px"></span>
    </xblock>
    <table id="infoTable" lay-filter="courseTable"></table>
</div>
<script id="toolbar" type="text/html">
    <button class="layui-btn layui-btn layui-btn-xs" lay-event="upd" onclick="openWin('课程信息','<%=basePath%>/course/toedit.do?id={{d.id}}')" ><i class="layui-icon">&#xe642;</i>查看详情</button>
</script>
<script>
    var t;
    layui.use(['table','layer','laydate','form'],function () {
        var table=layui.table;
        var laydate=layui.laydate;
        var form=layui.form;
        laydate.render({
            elem:'#startdate',
            range:true
        });
        t=table.render({
            elem:'#infoTable',
            url:'<%=basePath%>/course/getList.do',
            cols:[[
                {type:'checkbox'},
                {field:'id',title:'编号'},
                {field:'coursename',title:'课程名'},
                {field:'courseremake',title:'课程简介'},
                {field:'maxsum',title:'人数上限'},
                {field:'realsum',title:'实际人数'},
                {field:'statusname',title:'状态'},
                {field:'startdate',title:'预计开课日期'},
                {field:'enddate',title:'结束日期'},
                {field:'opt',title:'操作',toolbar:'#toolbar'}
            ]],
            page:true
        });

        table.on('tool(courseTable)',function (obj) {
            var data=obj.data;//获取当前行数据
            var layEvent=obj.event;//获取lay-event对应的值（也可以是表头的event）
            var tr=obj.tr;//获取当前行tr的DOM对象
        });

        form.on('submit(search)',function (data) {
            t.reload({
                where:data.field
            });
            return false;
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
                t.reload();
            }
        });
    }
</script>
</body>

</html>