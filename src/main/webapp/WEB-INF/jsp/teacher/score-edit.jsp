<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
      <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
      <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
      <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
      <script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-3.2.1.js"></script>
      <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
      <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>

  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
          <div class="layui-form-item">
              <label for="stuid" class="layui-form-label">
                  <span class="x-red">*</span>学号
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="stuid" name="id" required=""
                  value="${info.id}" readonly="readonly" class="layui-input">
              </div>

          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" required="" lay-verify="nikename"
                   value="${info.stuname}"      readonly="readonly" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>科目
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_pass" name="pass" required="" lay-verify="pass"
                  value="${info.course}"     readonly="readonly" class="layui-input">
              </div>

          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>输入成绩
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_repass" name="credit" required="" lay-verify="repass"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input id="sub-btn" type="button" class="layui-btn" lay-filter="add" lay-submit="" value="提交">
          </div>
      </form>
    </div>


  </body>
    <script>
        $(function (){

            $("#sub-btn").click(function() {
                var scroe = $("#L_repass").val();
                var stuid = $("#stuid").val();
                $.ajax({
                    url:"<%=basePath%>/toList/editScore.do",
                    data:{
                        credit:scroe,
                        stuid:stuid,
                        courseid:"${info.courseid}"
                    },
                    method:"post",
                    success:function (data) {
                        data=JSON.parse(data);
                        if(data.code>0) {
                            location.href = "<%=basePath%>/toList/toStudentList.do?username=${userInfo.username}"
                        } else {
                            alert("失败")
                        }
                    },
                    error:function(e) {

                        console.log(e);
                    }
                    })
            });
        });
    </script>
</html>