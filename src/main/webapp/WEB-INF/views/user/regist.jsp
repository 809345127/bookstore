<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 使用include指令将每个页面中公共的部门包含进来 -->
<%@ include file="/WEB-INF/views/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		//给用户名输入框绑定change事件
		$("#username").change(function(){
			var username = $("#username").val();
			var url = "user/checkUsername";
			var param = {"username":username};
		 $.ajax({
                url:url,
                type:"post",
                data:param,
                dataType:"json",
                success:function(res){
               	 var $msg = $("#msg");
    				$msg.html(res.message);
                },
                error:function(){
                     alert("出现异常了！");
                }
            });
		});
		
		//給驗證碼圖片綁定單機事件
		$("#codeImg").click(function(){
			this.src = "code.jpg?t=" + Math.random();
		});
		
		//给注册按钮绑定单击事件
		$("#sub_btn").click(function(){
			//获取用户名
			var userName = $("#username").val();
			//声明验证用户名的正则表达式
			var userReg = /^[a-zA-Z0-9_-]{3,16}$/;
			//验证用户名是否符合要求
			var flag = userReg.test(userName);
			if(!flag){
				alert("请输入3-16位的字母、数字、下划线或者减号的用户名！");
				return false;
			}
			//获取密码
			var password = $("#password").val();
			//声明验证密码的正则表达式
			var passwordReg = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!passwordReg.test(password)){
				alert("请输入6-18位的字母、数字、下划线或者减号的密码！")
				return false;
			}
			//获取确认密码
			var confirmPwd = $("#repwd").val();
			if(confirmPwd==""){
				alert("确认密码也不能为空！");
				return false;
			}
			if(confirmPwd!=password){
				//将确认密码清空
				$("#repwd").val("");
				alert("两次输入的密码不一致！");
				return false;
			}
			//获取邮箱
			var email = $("#email").val();
			//声明验证邮箱的正则表达式
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("邮箱格式不正确！");
				return false;
			}
			//获取验证码
			var code = $("#code").val();
			if(code==""){
				alert("验证码不能为空！");
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
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
<%-- 								<span class="errorMsg"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></span> --%>
								<span class="errorMsg" id="msg">${msg }</span>
							</div>
							<div class="form">
								<form action="user/doRegist" method="post">
<!-- 									<input type="hidden" name="method" value="regist" > -->
									<label>用户名称：</label>
									<input  value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="float: right" width="100px" height="40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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