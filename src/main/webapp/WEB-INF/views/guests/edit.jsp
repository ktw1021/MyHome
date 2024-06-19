<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 수정</title>
</head>
<body>
    <h1>방명록 수정</h1>
    <form action="${pageContext.request.contextPath}/guestbook?a=update" method="post">
        <input type="hidden" name="no" value="${guestBookVo.no}" />
        <table border="1">
            <tr>
                <td>이름</td><td><input type="text" name="name" value="${guestBookVo.name}"></td>
                <td>비밀번호</td><td><input type="password" name="pass"></td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="content" cols="60" rows="5">${guestBookVo.content}</textarea></td>
            </tr>
            <tr>
                <td colspan="4" align="right"><input type="submit" value="확인"></td>
            </tr>
        </table>
    </form>
</body>
</html>
