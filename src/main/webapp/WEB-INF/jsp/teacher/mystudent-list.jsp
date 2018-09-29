<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
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
  
  <body class="layui-anim layui-anim-up">
    <div class="x-body">
      <div class="layui-row">
        <form id="searchForm" class="layui-form layui-col-md12 x-so layui-form-pane">
          <input type="hidden" value="${teacherid}" name="id">
          <div class="layui-input-inline">
            <select name="coursename">
              <option value="" selected>请选择课程</option>
              <c:forEach items="${course}" var="c">
                <option value="${c.coursename}">${c.coursename}</option>
              </c:forEach>
            </select>
          </div>
          <input class="layui-input" placeholder="请输入学生姓名" name="stuname" autocomplete="off">
          <button id="sub" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="line-height:38px">ဂ</i></a>
        </form>
      </div>
      <xblock>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>电话</th>
            <th>所选课程</th>
            <th>成绩</th>
            <th>操作</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${student}" var="s">
          <tr>
            <td>${s.id}</td>
            <td>${s.stuname}</td>
            <td>${s.age}</td>
            <td>${s.sex}</td>
            <td>${s.stuphone}</td>
            <td>${s.course}</td>
            <td>${s.credit}</td>
            <td class="td-manage">
              <a title="编辑" href="<%=basePath%>/toList/addScore.do?id=${s.id}&course=${s.course}&courseid=${s.courseid}">
                <i class="layui-icon">&#xe642;</i>录入成绩
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>
        $(function() {
            $("#sub").click(function() {
                $.ajax({
                    url:"<%=basePath%>/toList/toStudentList.do",
                    data:$("#searchForm").serialize(),
                    method:"post",
                    success:function(data) {
                            location.reload();

                    },
                    error:function(e) {
                        console.log($("#searchForm").serialize());
                        console.log(e);
                    }
                });
            });
        });


       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
  </body>

</html>