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
    <title>学生列表</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">用户</a>
        <a>
          <cite>学生列表</cite></a>
      </span>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <div class="layui-input-inline">
            <select name="id">
                <option value="">以结课课程</option>
                <c:forEach items="${course}" var="t">
                    <option value="${t.id}">${t.coursename}</option>
                </c:forEach>
            </select>
            </div>
            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
            <a class="layui-btn layui-btn-small" style="line-height:2.4em" href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </form>
    </div>
    <table id="infoTable" lay-filter="userTable">

    </table>

</div>
<script>
    var t;
    var layer;
    layui.use(['table','layer','laydate','form'],function () {
        var table=layui.table;
        var form=layui.form;
        layer=layui.form;
        t=table.render({
            elem:'#infoTable',
            url:'<%=basePath%>/teacher/getCreditList.do',
            cols:[[
                {type:'checkbox'},
                {field:'id',title:'编号',sort:true},
                {field:'coursename',title:'课程',sort:true},
                {field:'stuname',title:'姓名'},
                {field:'credit',title:'该门分数',templet:function (d) {
                        if(d.credit){
                            return d.credit;
                        }else {
                            return "暂未评分"
                        }
                    }},
                {field:'opt',title:'操作',templet:function (d) {
                        if(d.credit){
                            return '<button class="layui-btn layui-btn-disabled layui-btn-xs" ><i class="layui-icon">&#xe642;</i>已评分</button>';
                        }else {
                            return "<button class='layui-btn layui-btn-xs' onclick=\"docredit("+d.id+")\" ><i class='layui-icon'>&#xe642;</i>点击评分</button>";
                        }
                    }}
            ]],
            page:true
        });
        table.on('edit(userTable)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });
        form.on('submit(search)',function (data) {
            console.log(data);
            t.reload({
                where:data.field
            });
            return false;
        });
    });
    function docredit(id) {
        console.log(1)
        var str = prompt("请输入评分成绩");
        var reg=/\d+(\.\d+)?/;
        if(reg.test(str)&&str<=100){
            $.ajax({
                url: "<%=basePath%>/teacher/docredit.do",
                data:{
                    id:id,
                    credit:str
                },
                method:"post",
                success:function (res) {
                    res = JSON.parse(res);
                    if(res.code>0){
                        t.reload();
                        alert("提交成功");
                    }else{
                       alert('更新失败！')
                    }
                },
                error:function (e) {
                    alert('与服务器链接失败，请稍后再试');

                }
            })
        }else {
            alert("输入必须为数字且不得超过100");
        }
    }
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
</script>
</body>

</html>
