package com.kh.practice.point.view;
import java.util.Scanner;
import com.kh.practice.point.controller.*;
public class PointMenu {
	//필드부
	private	Scanner sc = new Scanner(System.in);
	private CircleController cc= new CircleController();
	private RectangleController rc = new RectangleController();
	
	
	// 메서드부
	public void mainMenu() {
		while(true) {
		System.out.println("===== 메뉴 =====");
		System.out.println("1. 원");
		System.out.println("2. 사각형");
		System.out.println("9. 끝내기");
		System.out.print("메뉴 번호: ");
		int select = sc.nextInt();
		switch(select) {
			case 1 : circleMenu();
				break;
			case 2 : rectangleMenu();
				break;
			case 9 :
				return;
			default : 
				System.out.println("잘못 입력하셨습니다. 다시~");
		}
		}
	}
	public void circleMenu() {
		System.out.println("===== 원 메뉴 =====");
		System.out.println("1. 원 둘레");
		System.out.println("2. 원 넓이");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		int select = sc.nextInt();
		switch (select) {
			case 1: calcCircum();
				break;
			case 2: calcCircleArea();
				break;
			case 9:
			default:
				return;
		}
	}
	public void rectangleMenu() {
		System.out.println("===== 메뉴 =====");
		System.out.println("1. 사각형 둘레");
		System.out.println("2. 사각형 넓이");
		System.out.println("3. 메인으로");
		System.out.print("메뉴 번호 : ");
		int select = sc.nextInt();
		switch(select) {
			case 1 : calcPerimeter();
				break;
			case 2 : calcRectArea();
				break;
			case 9 : 
			default:
				return;
		}
	}
	public void calcCircum() {	// 원의 둘레를 구하는 메서드
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		System.out.print("반지름 : ");
		int radius = sc.nextInt();
		System.out.println("둘레 : "+cc.calcCircum(x, y, radius));
	}
	public void calcCircleArea() {	//원의 면적을 구하는 메서드
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		System.out.print("반지름 : ");
		int radius = sc.nextInt();
		System.out.println("면적 : "+cc.calcArea(x, y, radius));
	}
	public void calcPerimeter() {	// 사각형의 둘레를 구하는 메서드
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		System.out.print("높이 : ");
		int width = sc.nextInt();
		System.out.print("너비 : ");
		int height = sc.nextInt();
		System.out.println("둘레 : "+rc.calcPerimeter(x, y, height, width));
	}
	public void calcRectArea() {	// 사각형의 면적을 구하는 메서드
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		System.out.print("높이 : ");
		int width = sc.nextInt();
		System.out.print("너비 : ");
		int height = sc.nextInt();
		System.out.println("면적 : "+rc.calcArea(x, y, height, width));
	}
	
	
}
