package com.kh.example;

public class Member {

	public String memberld;
	public String memberpwd;
	public String memberName;
	public int age;
	public char gender;
	public String phone;
	public String email;

	public Member() {
	}
	
	public void changeName(String name) {
		 memberName = name;
	}
	
	public void printName() {
		System.out.println(memberName);
	}
}
