<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<div>
							<a href="user/toLogin">登录</a> | 
							<a href="user/toRegist">注册</a> &nbsp;&nbsp;
							<a href="cart/cart">购物车</a>
							<a href="manager/manager">后台管理</a>
							<a href="index">返回主页</a>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<span>欢迎<span class="um_span">${sessionScope.user.username }</span>光临尚硅谷书城</span>
							<a href="cart/cart">购物车</a>
							<a href="order">我的订单</a>
							<a href="user/loginOut">注销</a>&nbsp;&nbsp;
							<a href="index">返回主页</a>
						</div>
					</c:otherwise>
				</c:choose>