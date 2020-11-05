package com.kh.practice.point.controller;
import com.kh.practice.point.model.vo.Rectangle;
public class RectangleController {
	// 필드부
	private Rectangle r = new Rectangle();
	
	// 생성자부
	
	// 메서드부
	public String calcArea(int x, int y, int height, int width) {
		r.setX(x);
		r.setY(y);
		r.setHeight(height);
		r.setWidth(width);
		double area = width * height;
		return r.toString()+" / "+area;
	}
	public String calcPerimeter(int x, int y, int height, int width) {
		r.setX(x);
		r.setY(y);
		r.setHeight(height);
		r.setWidth(width);
		double peri = 2*(width + height); 
		return r.toString()+" / "+peri;
	}
}
