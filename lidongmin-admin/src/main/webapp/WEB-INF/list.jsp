<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<title>商品列表展示</title>
<script type="text/javascript">
	function addToMall(id){
		var url = "addToMall.do?id="+id;
		$.get(url,function(result){
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
		});
	}
</script>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="7">
				商品名称：
				<input type="text" id="find"/>
				<input type="button" value="搜索" onclick="window.location.href='list.do?find='+document.getElementById('find').value+''"/>
			</td>
		</tr>
		<tr>
			<td>商品ID</td>
			<td>商品图片</td>
			<td>商品名称</td>
			<td>商品价格</td>
			<td>编辑(<a href="toAdd.do">添加</a>)</td>
			<td><a href="toMall.do">查看购物车</a></td>
			<td>去结账</td>
		</tr>
		<c:forEach items="${list }" var="bean">
			<tr>
				<td>${bean.id }</td>
				<td><img src="${bean.imageUrl }" width="100px" height="100px"></td>
				<td>${bean.name }</td>
				<td>${bean.price }</td>
				<td><a href="toUpdate.do?id=${bean.id }">修改</a>&nbsp;&nbsp;<a href="del.do?id=${bean.id }">删除</a></td>
				<td><a href="javascript:addToMall(${bean.id })">添加到购物车</a></td>
				<td><a href="toPay.do?id=${bean.id }">去结账</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>