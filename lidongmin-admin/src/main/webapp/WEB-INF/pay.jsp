<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>商品ID</td>
			<td>商品图片</td>
			<td>商品名称</td>
			<td>商品价格</td>
		</tr>
		<c:forEach items="${list }" var="bean">
			<tr>
				<td>${bean.id }</td>
				<td><img src="${bean.imageUrl }" width="100px" height="100px"></td>
				<td>${bean.name }</td>
				<td>${bean.price }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				总金额：${pay }
				<input type="button" value="结账">
			</td>
		</tr>
	</table>
</body>
</html>