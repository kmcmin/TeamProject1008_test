<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="resources/main.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="common/top.jsp" flush="true"/> <br>
<h1>전체 주문 확인 화면입니다.</h1>
<jsp:include page="delivery/deliveryMain.jsp" flush="true"/> <br>
<jsp:include page="common/bottom.jsp" flush="true"/> <br>
</body>
</html>