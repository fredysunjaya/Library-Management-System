package librarymanagementsystem;

import java.time.LocalDate;

public class Member {
	private String id;
	private String name;
	private LocalDate birthDate;
	private String email;
	private String phoneNum;
	private String password;
	private LocalDate joinDate;
	
	public Member() {
		
	}

	public Member(String id, String name, LocalDate birthDate, String email, String phoneNum, String password, LocalDate joinDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNum = phoneNum;
		this.password = password;
		this.joinDate = joinDate;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
