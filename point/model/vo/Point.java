package com.kh.practice.point.model.vo;

public class Point {
	// �ʵ��
	private int x;
	private int y;
	// �����ں�
	public Point() {
	}
	public Point(int x, int y) {
		this.x = x;
		this.x = y;
	}
	// �޼����
	public String toString() {	// �ʵ� �� ���ڿ� ���·� ��ȯ
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