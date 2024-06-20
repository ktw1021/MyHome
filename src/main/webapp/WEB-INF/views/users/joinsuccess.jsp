<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home: Join Success</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/mysite.css'/>">

</head>
<body>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
        <jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
    
    <h1>Join Success</h1>
    <p>가입해주셔서 감사합니다.</p>
    <a href="<c:url value='/' />">Back To My Home</a>
     <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</body>
</html>
