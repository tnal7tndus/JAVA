package domain;
//**Join Test
//=> son, name, age, jno jname, project, captain, 조장이름 출력하기
//=> JoDTO 작성, joinList() 메서드작성( Controller, service, DAO )

public class JoDTO {
	//** private으로 멤버변수 정의
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;

	
	//** 1) 생성자
	//=> default 생성자, 모든 값을 초기화하는 생성자
	public JoDTO() {
	};

	public JoDTO(int jno, String jname, String captain, String project, String slogan) {
		this.jno = jno;
		this.jname = jname;
		this.captain = captain;
		this.project = project;
		this.slogan = slogan;
	}
	
	//** 2) setter/getter
	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	//** 3) toString
	@Override
	public String toString() {
		return "JoDTO [jno=" + jno + ", jname=" + jname + ", captain=" + captain + ", project=" + project + ", slogan="
				+ slogan + "]";
	}

	

}
