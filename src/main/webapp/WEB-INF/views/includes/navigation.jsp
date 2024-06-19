<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="navigation">
    <form name="guests" method="get" action="a">
        <ul>
            <li><a href="<c:url value='/' />">My Home</a></li>
            <li><a href="<c:url value='/guestbook' />">방명록</a></li>
            <li><a href="<c:url value='/board' />">게시판</a></li>
        </ul>
    </form>
</div>
