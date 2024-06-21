<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>
    <div id="container">
        <jsp:include page="/WEB-INF/views/includes/header.jsp" />
        <jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
        <div id="wrapper">
            <div id="content">
                <div id="board">
                    <form id="search_form" action="search" method="get">
                        <input type="text" id="kwd" name="kwd" placeholder="검색어를 입력하세요">
                        <input type="submit" value="검색">
                    </form>

                    <table class="tbl-ex">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="post" items="${posts}" varStatus="status">
                                <tr class="${status.index % 2 == 0 ? 'even' : 'odd'}">
                                    <td>${post.id}</td>
                                    <td><a href="board/view?id=${post.id}">${post.title}</a></td>
                                    <td>${post.author}</td>
                                    <td>${post.createdDate}</td>
                                    <td>${post.viewCount}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="pager">
                        <ul>
                            <c:forEach var="page" begin="${pagination.startPage}" end="${pagination.endPage}">
                                <li class="${page == pagination.currentPage ? 'selected' : ''}">
                                    <a href="board?page=${page}">${page}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="bottom">
                        <a id="new-post" href="board/write">새 글 작성</a>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
    </div>
</body>
</html>
