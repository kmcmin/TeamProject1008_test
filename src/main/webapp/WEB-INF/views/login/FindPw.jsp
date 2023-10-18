<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<!-- jquery -->
<script src="http://code.jquery.com/jquery.js"></script>
<!-- 유효성 검사 -->
 <script type="text/javascript" src="../resources/js/login_findPw.js"></script>
 <!--  부트스트랩 --> 
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" >
<!-- font관련 css 추가 -->
<link href="../resources/css/font.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="../common/tempTop.jsp" flush="true"/> <br>


<script>
<%if(session.getAttribute("mesg")!=null){%>
	alert("<%=(String)session.getAttribute("mesg")%>");
<%
session.removeAttribute("mesg");}%>
</script>
	<div class="d-flex justify-content-center fontTitle"><span>비밀번호 찾기</span><br></div>	
	<br>
	<div class="d-flex justify-content-center">
	<form id="userForm" action="/app/login/findPw" method="post">
      	 <input type="hidden" id="tf" value="false"> 
	    	<label for="inputId" class="col-lg-6 control-label">아이디</label>
			        <input type="text" name="user_id" class="form-control onlyAlphabetAndNumber" id="id" data-rule-required="true" maxlength="14">
			    	<span id="id_message"  style="color:red"></span>
		   <br>
		    <label for="inputEmail1" class="col-lg-4 control-label">이메일</label>
		    	<!-- name통해 값 전달 목적의 hidden -->
		      	<input type="hidden" name="user_email" id="user_email">
		      	
				<!-- 브라우저 통해 보이는 부분 --> 
		       <input type="text" class="form-control"  data-rule-required="true" maxlength="24" id="email1" name="email1">@
		        <input type="text" class="form-control" data-rule-required="true" maxlength="320" id="email2" name="email2">
		  		<br><select  id="selectEmail" class="form-control" data-rule-required="true" >
					    <option value="" selected="selected">직접 입력</option>
				        <option value="naver.com">naver.com</option>
				        <option value="gmail.com">gmail.com</option>
				        <option value="nate.com">nate.com</option>
				        <option value="hanmail.net">hanmail.net</option>
				        <option value="kakao.com">kakao.com</option>
		       	</select>
     			<span id="email_message"  style="color:red"></span><br><br>
       	       	<input type="button" id="buttonEmail" class="form-control btn btn-success" style="" value="이메일 인증"><br>
		       	<br><br>
		   	    <input type="submit"  class="form-control btn btn-outline-success" value="비밀번호 찾기">
	</form>
	</div>
	<br>
	<div class="d-flex justify-content-center">
	<button><a href="/app/login/findIdUI" class="fontBold">아이디 찾기</a><br></button>&nbsp;
	<button><a href="/app/login/UI" class="fontBold">로그인</a></button>
	<br>
	</div><br>
		<jsp:include page="../common/tempBottom.jsp" flush="true"/> <br>
	
</body>
</html>