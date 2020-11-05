package com.kh.practice.point.view;
import java.util.Scanner;
import com.kh.practice.point.controller.*;
public class PointMenu {
	//�ʵ��
	private	Scanner sc = new Scanner(System.in);
	private CircleController cc= new CircleController();
	private RectangleController rc = new RectangleController();
	
	
	// �޼����
	public void mainMenu() {
		while(true) {
		System.out.println("===== �޴� =====");
		System.out.println("1. ��");
		System.out.println("2. �簢��");
		System.out.println("9. ������");
		System.out.print("�޴� ��ȣ: ");
		int select = sc.nextInt();
		switch(select) {
			case 1 : circleMenu();
				break;
			case 2 : rectangleMenu();
				break;
			case 9 :
				return;
			default : 
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ�~");
		}
		}
	}
	public void circleMenu() {
		System.out.println("===== �� �޴� =====");
		System.out.println("1. �� �ѷ�");
		System.out.println("2. �� ����");
		System.out.println("9. ��������");
		System.out.print("�޴� ��ȣ : ");
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
		System.out.println("===== �޴� =====");
		System.out.println("1. �簢�� �ѷ�");
		System.out.println("2. �簢�� ����");
		System.out.println("3. ��������");
		System.out.print("�޴� ��ȣ : ");
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
	public void calcCircum() {	// ���� �ѷ��� ���ϴ� �޼���
		System.out.print("x ��ǥ : ");
		int x = sc.nextInt();
		System.out.print("y ��ǥ : ");
		int y = sc.nextInt();
		System.out.print("������ : ");
		int radius = sc.nextInt();
		System.out.println("�ѷ� : "+cc.calcCircum(x, y, radius));
	}
	public void calcCircleArea() {	//���� ������ ���ϴ� �޼���
		System.out.print("x ��ǥ : ");
		int x = sc.nextInt();
		System.out.print("y ��ǥ : ");
		int y = sc.nextInt();
		System.out.print("������ : ");
		int radius = sc.nextInt();
		System.out.println("���� : "+cc.calcArea(x, y, radius));
	}
	public void calcPerimeter() {	// �簢���� �ѷ��� ���ϴ� �޼���
		System.out.print("x ��ǥ : ");
		int x = sc.nextInt();
		System.out.print("y ��ǥ : ");
		int y = sc.nextInt();
		System.out.print("���� : ");
		int width = sc.nextInt();
		System.out.print("�ʺ� : ");
		int height = sc.nextInt();
		System.out.println("�ѷ� : "+rc.calcPerimeter(x, y, height, width));
	}
	public void calcRectArea() {	// �簢���� ������ ���ϴ� �޼���
		System.out.print("x ��ǥ : ");
		int x = sc.nextInt();
		System.out.print("y ��ǥ : ");
		int y = sc.nextInt();
		System.out.print("���� : ");
		int width = sc.nextInt();
		System.out.print("�ʺ� : ");
		int height = sc.nextInt();
		System.out.println("���� : "+rc.calcArea(x, y, height, width));
	}
	
	
}
