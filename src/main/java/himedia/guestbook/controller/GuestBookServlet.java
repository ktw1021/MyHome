package himedia.guestbook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import himedia.guestbook.dao.GuestBookDao;
import himedia.guestbook.dao.GuestBookDaoOracleImpl;
import himedia.guestbook.vo.GuestBookVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "guestbook", urlPatterns = "/guestbook")
public class GuestBookServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private GuestBookDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new GuestBookDaoOracleImpl("himedia","himedia"); // DAO 객체 초기화
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("a");

        if ("add".equals(actionName)) {
            // 사용자 입력 페이지로 FORWARD
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guests/list.jsp");
            rd.forward(req, resp);
        } else if ("edit".equals(actionName)) {
            Long no = Long.parseLong(req.getParameter("no"));
            GuestBookVo vo = dao.getGuestBook(no);
            req.setAttribute("guestBookVo", vo);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guests/edit.jsp");
            rd.forward(req, resp);
        } else {
            // 목록 받아오는 부분 -> el
            List<GuestBookVo> list = dao.getList();
            req.setAttribute("list", list);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guests/list.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("a");

        if ("insert".equals(actionName)) {
            // INSERT 기능 수행
            String name = req.getParameter("name");
            String password = req.getParameter("pass");
            String content = req.getParameter("content");

            GuestBookVo vo = new GuestBookVo(name, password, content);
            vo.setName(name);
            vo.setPassword(password);
            vo.setContent(content);

            boolean success = dao.insert(vo);

            if (success) {
                System.out.println("INSERT SUCCESS");
                resp.sendRedirect(req.getContextPath() + "/guestbook"); // Redirect (3xx)
            } else {
                System.out.println("INSERT FAILED");
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 입력 중 오류가 발생했습니다.");
            }
        } else if ("delete".equals(actionName)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guests/deleteform.jsp");
            req.setAttribute("no", req.getParameter("no"));
            rd.forward(req, resp);
        } else if ("deleteform".equals(actionName)) {
            Long no = Long.valueOf(req.getParameter("no"));
            GuestBookVo vo = dao.get(no);
            String password = req.getParameter("password");

            if (password.equals(vo.getPassword())) {
                boolean success = dao.delete(vo);
                if (success) {
                    System.out.println("Delete Success");
                    resp.sendRedirect(req.getContextPath() + "/guestbook");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 삭제 중 오류가 발생했습니다.");
                }
            } else {
            	resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('비밀번호가 일치하지 않습니다.');");
                out.println("window.location.href = '" + req.getContextPath() + "/guestbook';");
                out.println("</script>");
                out.flush();
                
            }
        } else if ("update".equals(actionName)) {
            // UPDATE 기능 수행
            Long no = Long.parseLong(req.getParameter("no"));
            String name = req.getParameter("name");
            String password = req.getParameter("pass");
            String content = req.getParameter("content");
            
            GuestBookVo existingVo = dao.get(no);
            if (existingVo != null && password.equals(existingVo.getPassword())) {
                // 비밀번호가 일치하면 수정 처리
                GuestBookVo vo = new GuestBookVo(no, name, password, content, existingVo.getDate());
                boolean success = dao.update(vo);
                if (success) {
                    resp.sendRedirect(req.getContextPath() + "/guestbook");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 수정 중 오류가 발생했습니다.");
                }
            } else {
                // 비밀번호가 일치하지 않으면 오류 응답(-> 알림창으로 수정)
            	resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('비밀번호가 일치하지 않습니다.');");
                out.println("window.location.href = '" + req.getContextPath() + "/guestbook';");
                out.println("</script>");
                out.flush();
            }

        } else {
            super.doPost(req, resp);
        }
    }
}