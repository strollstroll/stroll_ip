  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ page isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
              + path ;
  %>
  <!DOCTYPE html>
  <html>
  
  <head>
      <meta charset="UTF-8">
      <title>管理员页面</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
      <meta http-equiv="Cache-Control" content="no-siteapp" />
      <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
      <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
      <script type="text/javascript" src="<%=basePath%>/static/jquery-3.2.1.js"></script>
      <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
      <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
      <link rel="stylesheet" href="<%=basePath%>/X-admin/lib/layui/css/layui.css"  media="all">
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
      <!-- <label class="layui-form-label">状态</label>
      <div class="layui-input-block">
        <input type="text" name="ipStatus" lay-verify="title" autocomplete="off" placeholder="请输入状态" class="layui-input">
      </div> -->
      <label class="layui-form-label">状态</label>
      <div class="layui-input-inline">
        <select name="ipStatus" id="ipSta">
          <option value="">请选择状态</option>
          <option value="备用" selected="">备用</option>
          <option value="移机">移机</option>
          <option value="停机">停机</option>
        </select>
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-block">
        <input type="text" name="ipRemarks" lay-verify="remarks" autocomplete="off" placeholder="请输入备注" class="layui-input" onblur="onblus()">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">IP地址</label>
      <div class="layui-input-block">
        <input type="text" name="ipAddress" id="ipAddr" lay-verify="address" autocomplete="off" placeholder="请输入IP地址" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">子网掩码</label>
      <div class="layui-input-block">
        <input type="text" name="ipSubnetmask" lay-verify="subnetmask" autocomplete="off" placeholder="请输入子网掩码" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">地址数量</label>
      <div class="layui-input-block">
        <input type="text" name="ipAddressnumber" lay-verify="addressnumber" autocomplete="off" placeholder="请输入地址数量" class="layui-input">
      </div>
    </div>
   <div class="layui-form-item">
       <label class="layui-form-label">起用时间</label>
       <div class="layui-input-block">
         <input type="text" name="ipUsetime" lay-verify="usetime" autocomplete="off" class="layui-input" id="date1" placeholder="yyyy-MM-dd">
       </div>
   </div>
  <div class="layui-form-item">
      <label class="layui-form-label">用户名称</label>
      <div class="layui-input-block">
        <input type="text" name="ipUsername" lay-verify="usename" autocomplete="off" placeholder="请输入用户名称" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">Vlan号</label>
      <div class="layui-input-block">
        <input type="text" name="ipVlan" lay-verify="vlan" autocomplete="off" placeholder="请输入Vlan号" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">连接设备</label>
      <div class="layui-input-block">
        <input type="text" name="ipConnectingdevice" lay-verify="connectingdevice" autocomplete="off" placeholder="请输入连接设备" class="layui-input">
      </div>
    </div>
   
  <div class="layui-form-item">
      <label class="layui-form-label">端口</label>
      <div class="layui-input-block">
        <input type="text" name="ipPort" lay-verify="port" autocomplete="off" placeholder="请输入端口" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">速率</label>
      <div class="layui-input-block">
        <input type="text" name="ipRate" lay-verify="rate" autocomplete="off" placeholder="请输入速率" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">运维系统编号</label>
      <div class="layui-input-block">
        <input type="text" name="ipAttribution" lay-verify="attribution" autocomplete="off" placeholder="请输入运维系统编号" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">宽带受理编号</label>
      <div class="layui-input-block">
        <input type="text" name="ipBroadbandacceptancenumber" lay-verify="broadbandacceptancenuber" autocomplete="off" placeholder="请输入宽带受理编号" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">SN号</label>
      <div class="layui-input-block">
        <input type="text" name="ipSnnumber" lay-verify="snnumber" autocomplete="off" placeholder="请输入SN号" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">OLT地址</label>
      <div class="layui-input-block">
        <input type="text" name="ipOltaddress" lay-verify="oltaddress" autocomplete="off" placeholder="请输入OLT地址" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">IOM用户</label>
      <div class="layui-input-block">
        <input type="text" name="ipIomusername" lay-verify="iomusername" autocomplete="off" placeholder="请输入IOM用户" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">装机地址</label>
      <div class="layui-input-block">
        <input type="text" name="ipInstalledaddress" lay-verify="installedaddress" autocomplete="off" placeholder="请输入装机地址" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">类型</label>
      <div class="layui-input-block">
        <input type="text" name="ipType" lay-verify="type" autocomplete="off" placeholder="请输入类型" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">WOTV-BSS主产品</label>
      <div class="layui-input-block">
        <input type="text" name="ipWotvbssremarks" lay-verify="wotvbssremarks" autocomplete="off" placeholder="请输入WOTV-BSS主产品" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">上行速率</label>
      <div class="layui-input-block">
        <input type="text" name="ipOutputrate" lay-verify="outputrate" autocomplete="off" placeholder="请输入上行速率" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">下行速率</label>
      <div class="layui-input-block">
        <input type="text" name="ipInputrate" lay-verify="inputrate" autocomplete="off" placeholder="请输入下行速率" class="layui-input">
      </div>
    </div>
  <div class="layui-form-item">
      <label class="layui-form-label">对应终端数</label>
      <div class="layui-input-block">
        <input type="text" name="ipTerminalnumber" lay-verify="terminalnumber" autocomplete="off" placeholder="请输入对应终端数" class="layui-input">
      </div>
    </div>
      <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="add">提交审核</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;新添加的IP地址，提交审核后到IP信息待审核页面查看</p>
      </form>
  </div>
  
  </body>
  <script>
      layui.use(['form','layer','laydate'], function(){
          $ = layui.jquery;
          var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
          laydate.render({
              elem: '#date1'
            });
          //输入信息正则校验
            form.verify({
            	address:function(value){
                	var pattern= /^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\-(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)/;
               		if(!pattern.test(value)){
               			return "请输入正确的IP地址";
               		}else if(parseInt(value.split(".")[3].split("-")[0])>parseInt(value.split("-")[1])){
               			return "地址区间有问题！";
               		}
                } 
                ,subnetmask :function(value){
                	var addr=document.getElementById("ipAddr").value;
                	var num=parseInt(addr.split("-")[1])-parseInt(addr.split(".")[3].split("-")[0]);
                	var num1=255-num;
                	pattern="255.255.255."+num1;
                	if(pattern!=value){
                		return "请输入："+pattern;
                	}
                }
                ,addressnumber :function(value){
                	var addr=document.getElementById("ipAddr").value;
                	var num=parseInt(addr.split("-")[1])-parseInt(addr.split(".")[3].split("-")[0])+1;
                	if(num!=value){
                		return "请输入："+num;
                	}
                }
                
                ,outputrate :function(value){
                	var pattern=/^\d*$/;
                	if(!pattern.test(value)||value.length>10){
                		return "请输入合理数字！";
                	}
                }
                ,inputrate :function(value){
                	var pattern=/^\d*$/;
                	if(!pattern.test(value)||value.length>10){
                		return "请输入合理数字！";
                	}
                }
                ,terminalnumber :function(value){
                	var pattern=/^\d*$/;
                	if(!pattern.test(value)||value.length>10){
                		return "请输入合理数字！";
                	}
                }
                
  
          });
  
          //监听提交
          form.on('submit(add)', function(data){
              console.log(data);
              $.ajax({
                  url:'<%=basePath%>/employeeIp/doAdd.do',
                  data:data.field,
                  method:'post',
                  success:function (data) {
                     // res=JSON.parse(res);
                      if(data==0){
                          layer.alert("提交成功", {icon:6},function () {
                              // 获得frame索引
                              var index = parent.layer.getFrameIndex(window.name);
                              //关闭当前frame
                              parent.layer.close(index);
                          });
                      }else if(data==-2){
                          layer.alert('请不要重复提交!',{icon:5})
                      }else{
                          layer.alert('添加审核失败！',{icon:5})
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