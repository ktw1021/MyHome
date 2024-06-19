package himedia.guestbook.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.guestbook.vo.GuestBookVo;

public class GuestBookDaoOracleImpl implements GuestBookDao {
	private String dbuser;
	private String dbpass;

	public GuestBookDaoOracleImpl(String dbuser, String dbpass) {
		super();
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}


	private Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver를 찾을 수 없습니다.");
		}
		return con;
	}

	@Override
	public List<GuestBookVo> getList() {
		List<GuestBookVo> lst = new ArrayList<>();
		String sql = "SELECT no, name, reg_date, content FROM guestbook ORDER BY no";

		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");

				GuestBookVo tmp = new GuestBookVo(no, name, regDate, content);
				lst.add(tmp);
			}
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return lst;
	}

	// method for get ResultSet with id parameter
	private ResultSet executeQuery(PreparedStatement pstmt, Long id) throws SQLException {
		pstmt.setLong(1, id);
		return pstmt.executeQuery();
	}

	@Override
	public GuestBookVo get(Long id) {
		GuestBookVo tmp = null;
		String sql = "SELECT no, name, password, content, reg_date FROM guestbook WHERE no = ?";
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = executeQuery(pstmt, id);) {
			if (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");

				tmp = new GuestBookVo(no, name, password, content, regDate);
			}
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return tmp;
	}

	@Override
	public boolean insert(GuestBookVo vo) {
		int insertCount = 0;
		String sql = "INSERT INTO guestbook(no, name, password, content, reg_date) VALUES(seq_guestbook_no.nextval, ?, nvl(?, ''), ?, sysdate)";
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return insertCount == 1;
	}

	@Override
	public boolean delete(GuestBookVo vo) {
		int deleteCount = 0;
		String sql = "DELETE FROM guestbook WHERE no = ?";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setLong(1, vo.getNo());
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("연결 에러");
		} catch (Exception e) {
			System.err.println("에러");
		}
		return deleteCount == 1;
	}

	@Override
    public GuestBookVo getGuestBook(Long no) {
        GuestBookVo vo = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT no, name, reg_date, content FROM GuestBook WHERE no = ?")) {
            pstmt.setLong(1, no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Date reg_date = rs.getDate("reg_date");
                    String content = rs.getString("content");
                    vo = new GuestBookVo(no, name, reg_date, content);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

	@Override
	public boolean update(GuestBookVo vo) {
        boolean success = false;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE GuestBook SET name = ?, content = ? WHERE no = ?")) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getContent());
            pstmt.setLong(3, vo.getNo());
            success = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}