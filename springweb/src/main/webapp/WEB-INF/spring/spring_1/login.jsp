<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- cmd : cmd flag에 따라서 해당하는 공장이동
cmd=login : 등록된 flag key에 login 공장이동
cmd 등록이 않되는 flag 해당하는 페이지를 접근을 못한다 -->

<form action="/?cmd=login">
	<input type="text"  name="userid"/><br/>
	<input type="text"  name="userpw"/><br/>
	<input type="submit"  value="로그인"/><br/>
</form>
</body>
</html>