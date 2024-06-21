package himedia.board.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import himedia.board.dao.PostDao;
import himedia.board.dao.PostDaoOracleImpl;
import himedia.board.model.Pagination;
import himedia.board.model.PostVo;
import himedia.myhome.dao.UserVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/*")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PostDao postDao = new PostDaoOracleImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/board")) {
            handleBoard(req, resp);
        } else if (pathInfo.startsWith("/view")) {
            handleView(req, resp);
        } else if (pathInfo.equals("/write")) {
            handleWrite(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String actionName = req.getParameter("a");

        if ("write".equals(actionName)) {
            handleWritePost(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = getPage(req);
        int pageSize = 10;

        List<PostVo> postVos = postDao.getPosts(page, pageSize);
        int totalPosts = postDao.getPostCount();

        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page);
        pagination.setStartPage(Math.max(1, page - 2));
        pagination.setEndPage(Math.min(pagination.getStartPage() + 4, (int) Math.ceil((double) totalPosts / pageSize)));

        req.setAttribute("posts", postVos);
        req.setAttribute("pagination", pagination);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/board.jsp");
        rd.forward(req, resp);
    }

    private void handleView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postIdParam = req.getParameter("id");
        if (postIdParam != null) {
            try {
                int postId = Integer.parseInt(postIdParam);
                PostVo post = postDao.getPostById(postId);

                if (post != null) {
                    postDao.incrementViewCount(postId);
                    req.setAttribute("post", post);
                    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
                    rd.forward(req, resp);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/writeform.jsp");
        rd.forward(req, resp);
    }

    private void handleWritePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDate = now.format(formatter);

        HttpSession session = req.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser != null) {
            PostVo newPost = new PostVo();
            newPost.setTitle(title);
            newPost.setContent(content);
            newPost.setCreatedDate(createdDate);
            newPost.setAuthor(authUser.getName());

            boolean success = postDao.addPost(newPost);

            if (success) {
                resp.sendRedirect(req.getContextPath() + "/board");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "게시글 작성 실패");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/users?a=loginform");
        }
    }

    private int getPage(HttpServletRequest req) {
        String pageParam = req.getParameter("page");
        return (pageParam == null) ? 1 : Integer.parseInt(pageParam);
    }
}
