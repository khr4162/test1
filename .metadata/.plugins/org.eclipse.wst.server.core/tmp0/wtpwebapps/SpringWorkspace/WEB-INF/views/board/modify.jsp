<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>Modify Page</title>
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
	.btn2{
	display: inline;
	}
		
	</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>게시글 수정</h1>

<c:set var="board" value="${boardDTO.bvo }"></c:set>

<form action="/board/modify" method="post" enctype="multipart/form-data">
<div class="box1">

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">제목</span>
  <input type="text" name="title" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">작성자</span>
  <input type="text" name="writer" class="form-control" aria-label="Username" aria-describedby="basic-addon1"value="${ses.id }" readonly="readonly">
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">작성일</span>
  <input type="text" name="reg_date" class="form-control" aria-label="Username" aria-describedby="basic-addon1"value="${ses.id }" readonly="readonly">
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">내용</span>
  <input type="text" name="content" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
</div>

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">파일</span>
  <input type="file" name="files" id="file" class="form-control" aria-label="Username" aria-describedby="basic-addon1" multiple="multiple">
<c:set var="flist" value="${boardDTO.flist }"></c:set>
</div>



<!-- 파일 등록 라인 -->
file : <input type="file" name="files" id="file" multiple>
<button id="trigger" type="button">FileUpload</button><br>

<button type="submit" id="regBtn">수정</button>
</form>
<br>
<a href="/board/list"><button>목록</button></a>
<a href="/"><button>home</button></a>
</div>
<script type="text/javascript" 
src="/resources/JS/boardRegister.js"></script>
<script type="text/javascript" 
src="/resources/JS/boardModify.js"></script>
</body>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
</html>