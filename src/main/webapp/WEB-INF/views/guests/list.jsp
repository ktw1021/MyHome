<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/guestbook.css'/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
        <jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
<div id="wrapper">
<div id="content">
    <form action="${pageContext.request.contextPath}/guestbook?a=insert" method="post">
        <table border="1" width="500">
            <tr>
                <td>이름</td><td><input type="text" name="name"></td>
                <td>비밀번호</td><td><input type="password" name="pass"></td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="content" cols="60" rows="5"></textarea></td>
            </tr>
            <tr>
                <td colspan="4" align="right"><input type="submit" value="확인"></td>
            </tr>
        </table>
    </form>
    <br/>
    
    <c:forEach var="vo" items="${list}">
        <table width="510" border="1">
            <tr>
                <td>${vo.no}</td>
                <td>${vo.name}</td>
                <td>${vo.date}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/guestbook?a=delete" method="POST" style="display:inline;">
                        <input type="hidden" name="no" value="${vo.no}" />
                        <input type="submit" value="삭제" />
                    </form>
                    <a href="${pageContext.request.contextPath}/guestbook?a=edit&no=${vo.no}">수정</a>
                </td>
            </tr>
            <tr>
                <td colspan="4">${vo.content}</td>
            </tr>
        </table>
        <br/>
    </c:forEach>
    <br>

            </div>
        </div>
    
        <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
    </div>
</body>
</html>
