<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 使用include指令将每个页面中公共的部门包含进来 -->
<%@ include file="/WEB-INF/views/include/base.jsp" %>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@include file="/WEB-INF/views/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.page.list }">
			<h1>没有任何图书</h1>
		</c:if>
		<c:if test="${!empty requestScope.page.list }">
		<div id="book">
			<div class="book_cond">
				<form action="BookClientServlet?method=getPageBooksByPrice" method="post">
				价格：<input type="text" name="min"> 元 - 
					<input type="text" name="max"> 元 
					<input type="submit" value="查询">
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${not empty cart.cartItemList }">
					<span>您的购物车中有${cart.totalCount }件商品</span>
				</c:if>
				<c:if test="${not empty msg }">
					<h1>${msg }</h1>
				</c:if>
				
				<c:if test="${not empty recentBook.title }">
					<div>
						您刚刚将<span style="color: red">${recentBook.title }</span>加入到了购物车中
						<c:remove var="recentBook"/>
					</div>
				</c:if>
				
			</div>
			
			<c:forEach items="${requestScope.page.list }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock }</span>
						</div>
						<c:if test="${book.stock>0 }">
							<div class="book_add">
								<a href="CartServlet?method=addBook&bookId=${book.id }">加入购物车</a>
							</div>
						</c:if>
						<c:if test="${book.stock<1 }">
							小二拼命补货中
						</c:if>
						
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div id="page_nav">
			<c:if test="${page.pageNo > 1 }">
				<a href="index?min=${min }&max=${max}">首页</a>
				<a href="index?pageNo=${page.prev }&min=${min }&max=${max}">上一页</a>
			</c:if>
			<!-- 
				1.当总页数小于5时
				2.当总页数大于等于5时
					1）当当前页小于等于3时
					2）当当前页大于3时
			 -->
			 <c:choose>
			 	<c:when test="${page.totalPageNo < 5 }">
			 		<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPageNo }"></c:set>
			 	</c:when>
			 	<c:when test="${page.pageNo <= 3 }">
			 		<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="5"></c:set>
			 	</c:when>
			 	<c:otherwise>
			 		<c:set var="begin" value="${page.pageNo - 2 }"></c:set>
					<c:set var="end" value="${page.pageNo + 2 }"></c:set>
					<c:if test="${page.pageNo + 2 > page.totalPageNo }">
						<c:set var="begin" value="${page.totalPageNo - 4 }"></c:set>
						<c:set var="end" value="${page.totalPageNo }"></c:set>
					</c:if>
			 	</c:otherwise>
			 </c:choose>
			
			<c:forEach begin="${begin }" end="${end }" var="index">
				<c:if test="${page.pageNo == index }">
					[<a href="index?pageNo=${index }&min=${min }&max=${max}">${index }</a>]
				</c:if>
				<c:if test="${page.pageNo != index }">
					<a href="index?pageNo=${index }&min=${min }&max=${max}">${index }</a>
				</c:if>
			</c:forEach>
			<c:if test="${page.pageNo < page.totalPageNo }">
				<a href="index?pageNo=${page.next }&min=${min }&max=${max}">下一页</a>
				<a href="index?pageNo=${page.totalPageNo }&min=${min }&max=${max}">末页</a>
			</c:if>
			当前是第${page.pageNo }页，共${page.totalPageNo }页，共${page.totalRecord }条记录 到第<input value="${page.pageNo }" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id="sub">
			<script type="text/javascript">
				//给确定按钮绑定单击事件
				$("#sub").click(function(){
					//获取输入的页码
					var pageNo = $("#pn_input").val();
					if (pageNo>${page.totalPageNo }||pageNo<0) {
						alert("输入正确的页码");
						return false;
					}else {
						//发送请求
//	 					window.location.href="BookClientServlet?method=getPageBooksByPrice&pageNo="+pageNo;
//	 					location.href="BookClientServlet?method=getPageBooksByPrice&pageNo="+pageNo;
						location="index?pageNo="+pageNo+"&min=${min }&max=${max}";
					}

				});
			</script>
		</div>
	</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>