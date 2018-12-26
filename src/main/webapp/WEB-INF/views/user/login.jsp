<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 使用include指令将每个页面中公共的部门包含进来 -->
<%@ include file="/WEB-INF/views/include/base.jsp" %>
<script type="text/javascript" src="static/script/md5.js"></script>
<script type="text/javascript">
	$(function(){
		//给登录按钮绑定单击事件
		$("#sub_btn").click(function(){
			//获取用户输入的用户名
			var userName = $("#username").val();
			if(userName==""){
				alert("用户名不能为空！");
				return false;
			}
			
			//获取用户输入的密码
			var password = $("#pwd").val();
			if(password==""){
				alert("密码不能为空！");
				return false;
			}
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
<!-- 								<span class="errorMsg">请输入用户名和密码</span> -->
<%-- 								<span class="errorMsg"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%></span> --%>
								<span class="errorMsg">${empty msg?"请输入用户名和密码":msg }</span>
							</div>
							<div class="form">
								<form action="user/doLogin" method="post">
<!-- 									<input type="hidden" name="method" value="login" > -->
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="pwd"/>
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>