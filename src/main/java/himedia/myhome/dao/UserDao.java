package himedia.myhome.dao;

import java.util.List;

public interface UserDao {
	public List<UserVo> getList();	// 목록
	public boolean insert(UserVo userVo);	// 인서트
	public boolean update(UserVo userVo);	// 수정
	public boolean delete(Long no);			// 삭제
	public UserVo getUserByIdAndPassword(String email, String password);	// 로그인 메소드

}
