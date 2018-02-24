<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改页面</title>
</head>
<body>
	<form action="update.do" enctype="multipart/form-data" method="post">
		<table border="1">
			<tr>
				<td>商品ID</td>
				<td><input type="text" name="id" value="${product.id }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${product.name }"></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price" value="${product.price }"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="提交">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>