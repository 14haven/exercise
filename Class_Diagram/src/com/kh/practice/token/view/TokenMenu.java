package com.kh.practice.token.view;

import java.util.Scanner;
import com.kh.practice.token.controller.TokenController;

public class TokenMenu {
	
	private Scanner sc = new Scanner(System.in);
	private TokenController tc = new TokenController();
	
	// �޼ҵ��
	
	public void mainMenu() { // ���� �޴� ���
		while(true) {
		System.out.println("1. ���� ���ڿ�");
		System.out.println("2. �Է� ���ڿ�");
		System.out.println("9. ���α׷� ������");
		System.out.print("�޴� ���� : ");
		int select = sc.nextInt();
		
		switch(select) {
			case 1: tokenMenu();
				break;
			case 2: inputMenu();
				break;
			case 9: System.out.println("���α׷��� �����մϴ�.");
				return;
			default: System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				break;
		}
	}
		
	}
	
	public void tokenMenu() { // ��ū ó�� ��, ��ū ó�� �� ���
		System.out.print("��ū ó�� �� ���� : ");
		sc.skip("[\\r\\n]+");
		String str = sc.nextLine();
		System.out.println("��ū ó�� �� ���� : "+str.length());
		System.out.println("��ū ó�� �� ���� : "+tc.afterToken(str));
		System.out.println("��ū ó�� �� ���� : "+tc.afterToken(str).length());
		System.out.println("��� �빮�ڷ� ��ȯ : "+tc.afterToken(str.toUpperCase()));
		
	}

	public void inputMenu() { // ù ���� �빮�ڿ� ã�� ���� �� �� �ִ��� ���
		System.out.print("���ڿ��� �Է��ϼ��� : ");
		sc.skip("[\\r\\n]+");
		String input = sc.nextLine();
		System.out.println("ù ���� �빮�� : "+tc.firstCap(input));
		System.out.print("ã�� ���� �ϳ��� �Է��ϼ��� : ");
		String s = sc.next();
		char one = s.charAt(0);
		System.out.println(one+" ���ڰ� �� ���� : "+tc.findChar(input,one));
		
	}
	
	
	
}
