package s02_member;

//dto 클래스
public class Member {
	private String userid;
	private String passwd;
	private String name;
	private String email;
	
	//생성자
	public Member() {
		super();
	}
	public Member(String userid, String passwd, String name, String email) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
	}
	
	//getter, setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	//toString() - 오버라이딩
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}
}
