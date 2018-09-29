<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
    <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
</head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
            <c:if test="${! empty course}">
            <div class="layui-form-item">

                <label for="teachername" class="layui-form-label">
                    任课教师
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="id" name="id" value="${course.id}">
                    <input type="text" id="teachername" name="teachername" autocomplete="off"
                           value="${course.teachername}" class="layui-input" lay-verify="required"
                    disabled>
                </div>
            </div>
            </c:if>
          <div class="layui-form-item">
              <label for="coursename" class="layui-form-label">
                  课程名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="coursename" name="coursename" autocomplete="off"
                         value="${course.coursename}" class="layui-input" lay-verify="required"
                  <c:if test="${! empty course}"> disabled</c:if>>
              </div>
          </div>
                <div class="layui-form-item">
                    <label for="courseremake" class="layui-form-label">
                        课程简介
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" value="${course.courseremake}" id="courseremake" name="courseremake" class="layui-input"
                        <c:if test="${! empty course}"> disabled</c:if>>
                    </div>
                </div>
          <div class="layui-form-item">
              <label for="maxsum" class="layui-form-label">
                  人数上限
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${course.maxsum}" id="maxsum" name="maxsum"  class="layui-input" lay-verify="required|maxsum|number"
                  <c:if test="${! empty course}"> disabled</c:if>>
              </div>
          </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">开课时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off" value="${course.startdate}"name="startdate" id="test-limit1" placeholder="yyyy-MM-dd" <c:if test="${! empty course}"> disabled</c:if>>
                    </div>
                </div>
            </div>
            <c:if test="${course.statusid==2}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">结课时间</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" value="${course.enddate}"name="enddate" id="enddate" placeholder="yyyy-MM-dd" <c:if test="${! empty course}"> disabled</c:if>>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">实际人数</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" value="${course.realsum}"name="enddate" id="realsum" placeholder="yyyy-MM-dd" <c:if test="${! empty course}"> disabled</c:if>>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty course}">
            <div class="layui-form-item">
                <c:if test="${!empty msgMax}"><label>${msgMax}</label></c:if>
                <button  class="layui-btn" lay-filter="add" lay-submit="">
                  申请开课
                </button>
            </div>
            </c:if>
            <c:if test="${course.statusid==1&&userInfo.powerid==2}">
                <div class="layui-form-item">
                    <button  class="layui-btn" lay-filter="close" lay-submit="">
                        结课处理
                    </button>
                </div>
            </c:if>
      </form>
    </div>
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
        layui.use(['form','layer','laydate'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer
              ,laydate=layui.laydate;
            form.verify({
                maxsum: function(value){
                    if(value <20){
                        return '课程最低20人';
                    }
                },

            });
      var ins22= laydate.render({
                elem: '#test-limit1'
                ,min: 0
                ,max: 7
                ,theme: '#393D49'
                ,ready: function(){
                    ins22.hint('仅限未来一周内');
                }
            });
          //监听提交
          form.on('submit(add)', function(data){
            console.log(data);
                  $.ajax({
                      url:'<%=basePath%>/course/doUpdate.do',
                      data:data.field,
                      method:'post',
                      success:function (res) {
                          res=JSON.parse(res);
                          if(res.code>0){
                              layer.alert('提交成功',{icon:6},function () {
                                  // 获得frame索引
                                  var index = parent.layer.getFrameIndex(window.name);
                                  //关闭当前frame
                                  parent.layer.close(index);
                              });
                          }else {
                              layer.alert('更新失败',{icon:5});
                          }
                      },
                      error:function (err) {
                          layer.alert('与服务器连接失败，请重新链接');
                          console.log(err);
                      }
                  });
              return false;
            });
            form.on('submit(close)', function(data){
                console.log(data);
                $.ajax({
                    url:'<%=basePath%>/course/doClose.do',
                    data:data.field,
                    method:'post',
                    success:function (res) {
                        res=JSON.parse(res);
                        if(res.code>0){
                            layer.alert('提交成功',{icon:6},function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                            });
                        }else {
                            layer.alert('更新失败',{icon:5});
                        }
                    },
                    error:function (err) {
                        layer.alert('与服务器连接失败，请重新链接');
                        console.log(err);
                    }
                });
                return false;
            });
        });
    </script>
  </body>

</html>