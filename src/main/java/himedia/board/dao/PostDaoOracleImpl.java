package himedia.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import himedia.board.model.PostVo;
import himedia.board.util.DBUtil;

public class PostDaoOracleImpl implements PostDao {

    @Override
    public List<PostVo> getPosts(int page, int pageSize) {
        List<PostVo> posts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT * FROM boarduser ORDER BY no DESC) A) WHERE RNUM BETWEEN ? AND ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (page - 1) * pageSize + 1);
            pstmt.setInt(2, page * pageSize);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVo post = new PostVo();
                post.setId(rs.getInt("no"));
                post.setTitle(rs.getString("title"));
                post.setAuthor(rs.getString("name"));
                post.setCreatedDate(rs.getString("created_date"));
                post.setViewCount(rs.getInt("view_count"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return posts;
    }

    @Override
    public int getPostCount() {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) AS cnt FROM boarduser";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return count;
    }

    @Override
    public boolean addPost(PostVo post) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO boarduser (no, title, name, created_date, view_count, content) VALUES (SEQ_BOARDUSER.NEXTVAL, ?, ?, ?, 0, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getAuthor());
            pstmt.setString(3, post.getCreatedDate());
            pstmt.setString(4, post.getContent());

            int count = pstmt.executeUpdate();
            success = count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, conn);
        }

        return success;
    }

    @Override
    public PostVo getPostById(int postId) {
        PostVo post = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM boarduser WHERE no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                post = new PostVo();
                post.setId(rs.getInt("no"));
                post.setTitle(rs.getString("title"));
                post.setAuthor(rs.getString("name"));
                post.setCreatedDate(rs.getString("created_date"));
                post.setViewCount(rs.getInt("view_count"));
                post.setContent(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return post;
    }

    @Override
    public void incrementViewCount(int postId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE boarduser SET view_count = view_count + 1 WHERE no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, conn);
        }
    }
}
