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
                  <input type="hidden" value="${teacher.id}" id="id" name="id">
                  <input type="text"  id="username" name="username" required="" lay-verify="required"
                  autocomplete="off" value="${userInfo.username}" class="layui-input" disabled >
              </div>
          </div>

            <div class="layui-form-item">
                <label for="teachername" class="layui-form-label">
                    <span class="x-red">*</span>教师姓名
                </label>
                <div class="layui-input-inline">
                    <input type="text" value="${teacher.teachername}" id="teachername" name="teachername"  required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
          <div class="layui-form-item">
              <label for="teacherphone" class="layui-form-label">
                  <span class="x-red">*</span>手机号
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${teacher.teacherphone}" id="teacherphone" name="teacherphone"  required="phone" lay-verify="required|phone"
                  autocomplete="off" class="layui-input">
              </div>
          </div>

            <div class="layui-form-item">
                <label for="age" class="layui-form-label">
                    年龄
                </label>
                <div class="layui-input-inline">
                    <input type="text" value="${teacher.age}" id="age" name="age" required="age" lay-verify="age"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio"name="sex" value="男" title="男"
                    <c:if test="${teacher.sex!='女'}"> checked </c:if> >
                    <input type="radio" name="sex" value="女" title="女"
                    <c:if test="${teacher.sex=='女'}"> checked </c:if> >
                </div>
            </div>
            <c:if test="${empty teacher}">
            <div class="layui-form-item">
                    <label for="profession" class="layui-form-label"><span class="x-red">*</span>请选择专业</label>
                <div class="layui-inline">
                    <select name="profession" id="profession" required="profession" lay-verify="profession">
                        <option value="">选择后不得修改</option>
                        <c:forEach var="type" items="${teacherType}">
                            <option value="${type.id}"<c:if test="${type.id==teacher.profession}">
                                selected
                            </c:if>>${type.teachertype}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            </c:if>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">自我介绍</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" name="remake" class="layui-textarea">${teacher.remake}</textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text" id="resignation" style="display:none" >
                <label class="layui-form-label">离职原因</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入离职原因" name="resignation" class="layui-textarea"></textarea>
                </div>
            </div>

            <c:if test="${teacher.status==3}">
                <div class="layui-form-mid layui-word-aux">
                    正在等待管理员审核请耐心等候.......
                </div>
            </c:if>
            <c:if test="${teacher.status!=3}">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button  class="layui-btn" lay-filter="add" lay-submit="">
                        <c:if test="${! empty teacher}">修改</c:if>
                        <c:if test="${empty teacher}">提交申请</c:if>
                    </button>
                    <c:if test="${! empty teacher}">
                        <button class="layui-btn-danger layui-btn "  lay-submit="" lay-filter="refuse" >
                            离职申请
                        </button>
                    </c:if>
                </div>
            </c:if>

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
                  if(value <20 || value>60){
                      return '教师范围只能20~60岁';
                  }
              },
              profession:function (value) {
                  if(value==""){
                      return '请选择从事专业';
                  }
              }

          });
          //监听离职申请
          form.on('submit(refuse)', function(data){
              if( $("#resignation").css('display')=='none'){
                  $("#resignation").css('display','block');
              }else {
                  $.ajax({
                      url:'<%=basePath%>/teacher/doGo.do',
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
                              if(res.msg){
                                  layer.alert(res.msg,{icon:5})
                              }else {
                                  layer.alert('更新失败！',{icon:5})
                              }

                          }
                      },
                      error:function (err) {
                          layer.alert('与服务器链接失败，请稍后再试');
                          console.log(err);
                      }
                  });
              }
              return false;
          });
              //监听提交
          form.on('submit(add)', function(data){
              console.log(data);
              $.ajax({
                  url:'<%=basePath%>/teacher/doUpdate.do',
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
                          if(res.msg){
                              layer.alert(res.msg,{icon:5})
                          }else {
                              layer.alert('更新失败！',{icon:5})
                          }

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