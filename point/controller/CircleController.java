package com.kh.practice.point.controller;
import com.kh.practice.point.model.vo.Circle;
public class CircleController {
	// �ʵ��
	private Circle c = new Circle();
	
	// �����ں�
		//public CircleController(){} - ������ ������ ���
	
	// �޼����
	public String calcArea(int x , int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		double area = 3.1415 * radius * radius;
		return c.toString()+" / "+area;
	}
	public String calcCircum(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		double circum = 3.1415 * radius * 2;
		return c.toString()+" / "+circum;
	}
}
