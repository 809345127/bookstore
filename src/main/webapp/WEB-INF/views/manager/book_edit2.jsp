<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@ include file="/WEB-INF/views/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@ include file="/WEB-INF/views/include/header.jsp" %>
		</div>
		
		<div id="main">
			<form action="manager/doAdd" method="post">
				<!-- 由于更新图书时需要根据图书的id进行更新，所以我们将图书的id放到隐藏域中 -->
				<input type="hidden" name="bookId" value="${book.id }" id="fbookId">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="${book.title }" id="ftitle"/></td>
						<td><input name="price" type="text" value="${book.price }" id="fprice"/></td>
						<td><input name="author" type="text" value="${book.author }" id="fauthor"/></td>
						<td><input name="sales" type="text" value="${book.sales }" id="fsales"/></td>
						<td><input name="stock" type="text" value="${book.stock }" id="fstock"/></td>
						<td><input type="submit" value="提交" id="saveBtn"/></td>
					</tr>		
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
		<script type="text/javascript" src="static/script/layer/layer.js"></script>
		<script type="text/javascript">
			var index = -1 ;
			$("#saveBtn").click(function() {
				$.ajax({
            		type:"POST",
            		url:"manager/doUpdate",
            		data:{
            			title:$("#ftitle").val(),
            			price:$("#fprice").val(),
            			author:$("#fauthor").val(),
            			sales:$("#fsales").val(),
            			stock:$("#fstock").val(),
            			id:$("#fbookId").val()
            		},
            		beforeSend:function(){
            			index = layer.msg('正在保存用户数据 ,请稍后...', {icon: 16});
            			return true ;
            		},
            		success:function(result){
            			layer.close(index);
            			if(result.success){
            				window.location.href="manager/getBooks?pageNo=${param.pageNo}"; //页面加载完成时自动发起异步分页查询请求
            			}else{
            				layer.msg(result.message, {time:1000, icon:5, shift:6});
            			}
            		}
            	});
            	return false;
			});
		</script>
</body>
</html>