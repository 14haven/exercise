package kh.com.library.member;

import java.sql.Date;

public class Member {
// 필드부
	private int userNo;
	private String userId;
	private String userName;
	private int userAge;
	private String addr;
	private String gender;
	private Date enrollDate;

// 생성자부
	public Member() {}
	public Member(int userNo, String userId, String userName, int userAge
			, String addr, String gender, Date enrollDate) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.addr = addr;
		this.gender = gender;
		this.enrollDate = enrollDate;
	}
	
	
// 메소드부	
	public int getUserNo() {		return userNo;	}
	public void setUserNo(int userNo) {		this.userNo = userNo;	}
	public String getUserId() {		return userId;}
	public void setUserId(String userId) {	this.userId = userId;}
	public String getUserName() {	return userName;	}
	public void setUserName(String userName) {		this.userName = userName;	}
	public int getUserAge() {		return userAge;	}
	public void setUserAge(int userAge) {		this.userAge = userAge;	}
	public String getAddr() {		return addr;	}
	public void setAddr(String addr) {		this.addr = addr;	}
	public String getGender() {		return gender;}
	public void setGender(String gender) {		this.gender = gender;	}
	public Date getEnrollDate() {	return enrollDate;}
	public void setEnrollDate(Date enrollDate) {	this.enrollDate = enrollDate;	}
	

@Override
	public String toString() {
	return userNo+" / "+userId+" / "+userName+" / "+userAge+" / "+addr
			+" / "+gender+" / "+enrollDate;
}
	

}
