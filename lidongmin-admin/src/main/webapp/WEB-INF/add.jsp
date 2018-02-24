<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表添加页面</title>
</head>
<body>
	<form action="add.do" enctype="multipart/form-data" method="post">
		<table border="1">
			<tr>
				<td>图片上传</td>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>产品价格</td>
				<td><input type="text" name="price"></td>
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