package com.kh.practice.point.model.vo;

public class Circle extends Point {
	// �ʵ��
	private int radius;

	// �����ں�
	public Circle() {
	
	}
	public Circle(int x, int y, int radius) {
		super(x , y); // �θ� Ŭ���� ������ ȣ��
		this.radius = radius; // �� �ʵ忡 ����
	}
	
	// �޼����
	public String toString() {	// �ʵ� �� ���ڿ� ���·� ��ȯ(�θ� �ʵ� ����)
		return super.toString()+" "+radius; //�θ��� toString ȣ���ؾ� �ϴϱ� super.
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	

}
