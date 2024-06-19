<%@page import="himedia.myhome.dao.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<div id="header">
    <h1>My Homepages</h1>
    <c:choose>
        <c:when test="${not empty authUser}">
            <ul>
                <li><a href="<c:url value = "/users?a=logout" />">로그아웃</a></li>
                <li>${authUser.name}님 환영합니다</li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul>
                <li><a href="<c:url value = "/users?a=joinform"/>">회원가입</a></li>
                <li><a href="<c:url value = "/users?a=loginform"/>">로그인</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
