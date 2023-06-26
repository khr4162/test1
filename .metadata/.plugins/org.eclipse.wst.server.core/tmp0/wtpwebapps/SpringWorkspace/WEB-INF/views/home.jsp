<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<style type="text/css">
	h1{
	text-align: center;
	margin-top: 50px;
	}
	
	body{
	background-color: azure;
	margin: auto;
	text-align: center;
	}

	.box1{
	text-align: center;
	margin : 75px;
	background-color: white;
	width: 500px;
	height: 300px;
	vertical-align: middle;
	display: inline-block;
	}
		
	.btn1{
	font-size: 20px;
	padding: 5px;
	display: inline-block;
	margin-top: 40px;
	}
	.box1> h3{
	margin-top: 75px;
	}
		
	</style>
</head>
<body>
<h1>
	형래의 페이지
</h1>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<div class="box1">
<c:if test="${ses.id != null }">
<h3>${ses.id }님 어서와 어서와~</h3>

</c:if>
<c:if test="${ses.id == null }">
<a href="/member/login"><button type="button" class="btn1"
class="btn btn-primary btn-lg">로그인</button></a>
<a href="/member/join"><button type="button" class="btn1"
class="btn btn-primary btn-lg">회원가입</button></a>
</c:if>

<c:if test="${ses.id != null }">
<a href="/"><button type="button"
class="btn btn-primary btn-lg">로그아웃</button></a>
</c:if>

<c:if test="${ses.id != null }">
<a href="/board/register"><button type="button"
class="btn btn-primary btn-lg">글 등록</button></a>
<a href="/board/list"><button type="button"
class="btn btn-primary btn-lg">글 리스트</button></a>
</c:if>
</div>

<script type="text/javascript">
	const msg_home = '<c:out value="${msg_home}"/>';
	console.log(msg_home);
	if(msg_home == 1){
		alert("welcome");
	}
</script>
<%@ include file="./layout/footer.jsp" %>
</body>
</html>
