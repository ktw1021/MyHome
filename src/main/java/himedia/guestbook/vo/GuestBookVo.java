package himedia.guestbook.vo;


import java.util.Date;
import java.util.Objects;

public class GuestBookVo {
	private Long no;
	private String name;
	private String password;
	private String content;
	private Date date;
	

	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content + ", date="
				+ date + "]";
	}
	
	public GuestBookVo(Long no, String name, Date date, String content) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
		this.date = date;
	}

	public GuestBookVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}
	
	
	public GuestBookVo(Long no, String name, String password, String content, Date date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.date = date;
	}

	public GuestBookVo(Long no, String name, String password, String content) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		if (password == null)
			return "";
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(no);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuestBookVo other = (GuestBookVo) obj;
		return Objects.equals(no, other.no);
	}

	
	
}