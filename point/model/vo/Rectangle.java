package com.kh.practice.point.model.vo;

public class Rectangle extends Point {
	// 필드부
	private int width;
	private int height;
	
	// 생성자부
	public Rectangle() {
		
	}
	public Rectangle(int x, int y, int width, int height) {
		super(x, y); // 부모 클래스 생성자 호출
		this.width = width;
		this.height = height;
		
	}
	// 메서드부
	public String toString() { // 필드 값 문자열 형태로 반환(부모 필드 포함)
		return super.toString()+" "+height+" "+width;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
