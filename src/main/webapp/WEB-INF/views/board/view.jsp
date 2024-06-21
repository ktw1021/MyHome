<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 보기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>
<div id="container">
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
    <div id="wrapper">
        <div id="content">
            <div id="post-detail">
                <c:if test="${empty post}">
                    <p>해당 게시물을 찾을 수 없습니다.</p>
                </c:if>
                <c:if test="${not empty post}">
                    <h2>${post.title}</h2>
                    <p>작성자: ${post.author}</p>
                    <p>작성일: ${post.createdDate}</p>
                    <p>조회수: ${post.viewCount}</p>
                    <p>${post.content}</p>
                </c:if>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</div>
</body>
</html>
