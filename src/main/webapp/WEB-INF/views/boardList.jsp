<%@ page contentType="text/html; charset=utf-8" language="java" %> <!-- page에 대한 contentType설정 --> <!-- -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> <!-- jstl c를 사용 하기위한 설정 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %> <!-- fmt를 사용 하기위한 설정 -->
<%@ page session="true"%> <!-- session 설정 -->
<c:set var="loginId" value="${sessionScope.id}"/> <!-- loginId c태그 설정 -->
<c:set var="loginOutLink" value="${loginId==''? 'login/login' : '/login/logout'}"/> <!-- loginOutLink c태그 설정/ loginId가 빈문자면 login 아니면 logout 페이지로 이동 -->
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }

        a {
            text-decoration: none;
            color: black;
        }
        button,
        input {
            border: none;
            outline: none;
        }

        .board-container {
            width: 60%;
            height: 1200px;
            margin: 0 auto;
            /* border: 1px solid black; */
        }
        .search-container {
            background-color: rgb(253, 253, 250);
            width: 100%;
            height: 110px;
            border: 1px solid #ddd;
            margin-top : 10px;
            margin-bottom: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-form {
            height: 37px;
            display: flex;
        }
        .search-option {
            width: 100px;
            height: 100%;
            outline: none;
            margin-right: 5px;
            border: 1px solid #ccc;
            color: gray;
        }

        .search-option > option {
            text-align: center;
        }

        .search-input {
            color: gray;
            background-color: white;
            border: 1px solid #ccc;
            height: 100%;
            width: 300px;
            font-size: 15px;
            padding: 5px 7px;
        }
        .search-input::placeholder {
            color: gray;
        }

        .search-button {
            /* 메뉴바의 검색 버튼 아이콘  */
            width: 20%;
            height: 100%;
            background-color: rgb(22, 22, 22);
            color: rgb(209, 209, 209);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
        }
        .search-button:hover {
            color: rgb(165, 165, 165);
        }

        table {
            border-collapse: collapse;
            width: 100%;
            border-top: 2px solid rgb(39, 39, 39);
        }

        tr:nth-child(even) {
            background-color: #f0f0f070;
        }

        th,
        td {
            width:300px;
            text-align: center;
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }

        td {
            color: rgb(53, 53, 53);
        }

        .no      { width:150px;}
        .title   { width:50%;  }

        td.title   { text-align: left;  }
        td.writer  { text-align: left;  }
        td.viewcnt { text-align: right; }

        td.title:hover {
            text-decoration: underline;
        }

        .paging {
            color: black;
            width: 100%;
            align-items: center;
        }

        .page {
            color: black;
            padding: 6px;
            margin-right: 10px;
        }
        .paging-active {
            background-color: rgb(216, 216, 216);
            border-radius: 5px;
            color: rgb(24, 24, 24);
        }

        .paging-container {
            width:100%;
            height: 70px;
            display: flex;
            margin-top: 50px;
            margin : auto;
        }
        .btn-write {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
            margin-left: 30px;
        }

        .btn-write:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li> <!-- 좌측화면 로고 -->
        <li><a href="<c:url value='/'/>">Home</a></li> <!-- 클릭하면 메인 화면으로 넘어감 -->
        <li><a href="<c:url value='/board/list'/>">Board</a></li> <!-- 게시판으로 이동 -->
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li> <!-- 로그아웃 -->
        <li><a href=""><i class="fa fa-search"></i></a></li> <!-- 검색 -->
    </ul>
</div>

<!-- js 부분 -->
<script>
    let msg = "${msg}"; //서버에서 담은 msg 내용을 변수에 담음
    <!-- 서버에서 가져온 msg에 따라 조건에 맞게 alert변경 -->
    if(msg=="LIST_ERR") alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
    if(msg=="READ_ERR") alert("삭제되었거나 없는 게시물 입니다.");
    if(msg=="DEL_ERR") alert("삭제되었거나 없는 게시물 입니다.");

    if(msg=="DEL_OK") alert("성공적으로 삭제 되었습니다.");
    if(msg=="WRT_OK") alert("성공적으로 등록 되었습니다.");
    if(msg=="MOD_OK") alert("성공적으로 수정 되었습니다.");
</script>

<!-- 검색창 부분-->
<div style="text-align: center">
    <div class="board-container">
        <div class="search-container">
            <form action="<c:url value="board/list"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <!-- option값을 A로 정하고, ph에서 꺼내온 값이 A나 빈열이면 제목+내용이 초기값으로 세팅되어 있고, 검색어는 빈열로 한다.
                    <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>제목+내용</option>
                    <option></option>
                    <option></option>
                </select>
            </form>
        </div>
    </div>
</div>
</body>
</html>