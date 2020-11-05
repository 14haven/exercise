package com.kh.practice.point.model.vo;

public class Point {
	// 필드부
	private int x;
	private int y;
	// 생성자부
	public Point() {
	}
	public Point(int x, int y) {
		this.x = x;
		this.x = y;
	}
	// 메서드부
	public String toString() {	// 필드 값 문자열 형태로 반환
		return x+" "+y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	
}
