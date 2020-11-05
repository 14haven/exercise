package com.kh.practice.point.model.vo;

public class Rectangle extends Point {
	// �ʵ��
	private int width;
	private int height;
	
	// �����ں�
	public Rectangle() {
		
	}
	public Rectangle(int x, int y, int width, int height) {
		super(x, y); // �θ� Ŭ���� ������ ȣ��
		this.width = width;
		this.height = height;
		
	}
	// �޼����
	public String toString() { // �ʵ� �� ���ڿ� ���·� ��ȯ(�θ� �ʵ� ����)
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
