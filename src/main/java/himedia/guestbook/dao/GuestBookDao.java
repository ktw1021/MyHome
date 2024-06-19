package himedia.guestbook.dao;


import java.util.List;

import himedia.guestbook.vo.GuestBookVo;


public interface GuestBookDao {
	List<GuestBookVo> getList();
	GuestBookVo getGuestBook(Long no);
	GuestBookVo get(Long no);
	boolean insert(	GuestBookVo vo);
//	public List<GuestVo> getList(); 	// emaillist table SELECT
//	public boolean insert(GuestVo vo);	// emaillist table INSERT
//	public boolean delete(int no, String password);
	boolean delete(GuestBookVo vo);
	boolean update(GuestBookVo vo);

}