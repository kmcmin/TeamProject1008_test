<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<!-- jquery -->
<script src="http://code.jquery.com/jquery.js"></script>
<!-- 유효성 검사 -->
<script type="text/javascript" src="../resources/js/login_resetPw.js"></script>
<!-- font관련 css 추가 -->
<link href="../resources/css/font.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="../common/tempTop.jsp" flush="true"/> <br>

<%
	String user_id= "";
	if(request.getAttribute("user_id")==null){%>
	<script>
	location.href="/app/login/findPwUI";
	</script>
<%	}else{
		user_id = (String)request.getAttribute("user_id"); 
	}//else
	 if(session.getAttribute("mesg")==null){%>

	 <div class="d-flex justify-content-center fontTitle">비밀번호 재설정</div>
<br>
<div class="row">
<div class="d-flex justify-content-center col-sm-10" >
	<form id="userForm" action ="resetPw" method="post">
		아이디<input type="text" name="user_id" value="<%=user_id%>">
		<div class="form-group" id="divPassword">
		    <label for="inputPassword" class="col-lg-4 control-label">비밀번호</label>
		    <div class="col-lg-6">
		        <input type="password" name="user_pw" class="form-control" id="password" name="excludeHangul" data-rule-required="true"maxlength="20"  placeholder="영문,숫자,특수문자 8~20자리">
		       	<span id="pw_message1" style="color:red"></span>
		    </div>
		</div>
		<div class="form-group" id="divPasswordCheck">
		    <label for="inputPasswordCheck" class="col-lg-4 control-label">비밀번호 확인</label>
		    <div class="col-lg-6">
		        <input type="password" class="form-control" id="passwordCheck" data-rule-required="true" maxlength="30">
		       	<span id="pw_message2" style="color:red"></span>
		    </div>
		</div>
		<br>
		<input type="submit" value="비밀번호 재설정">
	</form>
</div>
</div>
	<br>
<%}else{ //넘어온 출력 값이 있으면%>
메시지: <%=(String)session.getAttribute("mesg")%>  <%
session.removeAttribute("mesg");}%>
	<button><a href="/app/login/findIdUI">아이디 찾기</a><br></button>
	<button><a href="/app/loginUI">로그인</a></button>
	<jsp:include page="../common/tempBottom.jsp" flush="true"/> <br>
</body>
</html>