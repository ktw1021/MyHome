<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<link type="text/css" rel="stylesheet" href="<c:url value = "/css/home.css"/>">
</head>
<body>
  <div id="container">
    <!-- 헤더 영역 -->
    <c:import url="/WEB-INF/views/includes/header.jsp">
        <c:param name="param1" value="value1" />
        <c:param name="param2" value="value2" />
    </c:import>
    
    <div id="wrapper">
      <!-- 내비게이션 영역 -->
      <c:import url="/WEB-INF/views/includes/navigation.jsp">
          <c:param name="param1" value="value1" />
          <c:param name="param2" value="value2" />
          <c:param name="param3" value="value3" />
          <c:param name="param4" value="value4" />
      </c:import>
      
      <div id="content">
        <!-- 콘텐츠 영역 -->
        <!-- TODO: 여기에 콘텐츠를 추가하십시오 -->
      </div>
    </div>
    
    <!-- 푸터 영역 -->
    <jsp:include page="/WEB-INF/views/includes/footer.jsp" />
  </div>
</body>
</html>
