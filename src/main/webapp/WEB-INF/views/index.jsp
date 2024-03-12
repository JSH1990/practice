<!--페이지언어와 웹 언어 설정-->
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- jstl 사용하기 위한 라이브러리 추가-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이유 모르겠음 -->
<%@ page session="false"%>
<!-- loginId를 변수로 설정하고, 값이 null이면 빈값이고, 값이있으면  session의 id 값을 가져온다. -->
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<!-- 로그아웃할때 링크 변수 loginOutLink로 설정하고, loginId 값유무의 따라 이동하는 페이지 설정 ->
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<!-- 잘모르겠음 -->
<c:set var="loginOut" value="${loginId=='' ? 'login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
</body>
</html>