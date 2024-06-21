<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>

    <div id="container">
        <jsp:include page="/WEB-INF/views/includes/header.jsp" />
        <jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
        <div id="wrapper">
      
    <h1>게시글 작성</h1>
    <form action="${pageContext.request.contextPath}/board" method="post">
        <input type="hidden" name="a" value="write">
        <div>
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <button type="submit">작성</button>
    </form>
          </div>
        </div>
        <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</body>
</html>
