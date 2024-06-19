<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/guestbook.css'/>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>방명록</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/guestbook?a=deleteform">
        <input type="hidden" name="no" value="${param.no}">
        <table>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password"></td>
                <td><input type="submit" value="확인"></td>
                <td><a href="${pageContext.request.contextPath}">메인으로 돌아가기</a></td>
            </tr>
        </table>
    </form>
</body>
</html>
