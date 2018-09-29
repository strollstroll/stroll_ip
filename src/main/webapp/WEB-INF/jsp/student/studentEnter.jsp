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
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">课程名</label>
                <div class="layui-input-block">
                    <input type="hidden" name="id" value="${course.id}">
                    <input value="${course.coursename}" name="coursename" type="text" disabled
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">课程类型</label>
                <div class="layui-input-block">
                    <input value="${course.typename}" name="typename" type="text" disabled
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">人数上限</label>
                <div class="layui-input-block">
                    <input value="${course.maxsum}" name="maxsum" type="text" disabled
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">当前人数</label>
                <div class="layui-input-block">
                    <input value="${course.realsum}" name="realsum" type="text" disabled
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">开课时间</label>
                <div class="layui-input-block">
                    <input value="${course.startdate}" type="text" disabled
                           autocomplete="off" class="layui-input">
                </div>
            </div>
         <div class="layui-form-item">
                <div class="layui-input-block">
                    <button  class="layui-btn" lay-filter="agree" lay-submit="">
                        报名
                    </button>
                </div>
            </div>
    </form>
</div>

</body>
<script>
    $(function () {
        layui.use(['layer'],function () {
            var layer=layui.layer;
            if("${msgMax}"!=""){
                layer.msg('${msgMax}',{icon:2,time:1500},function () {
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                })   }
        })
    })
</script>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //监听通过
        form.on('submit(agree)', function(data){
                $.ajax({
                    url:'<%=basePath%>/student/doEnter.do',
                    data:data.field,
                    method:'post',
                    success:function (res) {
                        res=JSON.parse(res);
                        if(res.code>0){
                            layer.alert("报名成功", {icon:6},function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                            });
                        }else{
                            layer.alert('报名失败！',{icon:5})
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
