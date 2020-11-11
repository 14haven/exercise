package com.kh.practice.token.view;

import java.util.Scanner;
import com.kh.practice.token.controller.TokenController;

public class TokenMenu {
	
	private Scanner sc = new Scanner(System.in);
	private TokenController tc = new TokenController();
	
	// 메소드부
	
	public void mainMenu() { // 메인 메뉴 출력
		while(true) {
		System.out.println("1. 지정 문자열");
		System.out.println("2. 입력 문자열");
		System.out.println("9. 프로그램 끝내기");
		System.out.print("메뉴 선택 : ");
		int select = sc.nextInt();
		
		switch(select) {
			case 1: tokenMenu();
				break;
			case 2: inputMenu();
				break;
			case 9: System.out.println("프로그램을 종료합니다.");
				return;
			default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
		}
	}
		
	}
	
	public void tokenMenu() { // 토큰 처리 전, 토큰 처리 후 출력
		System.out.print("토큰 처리 전 글자 : ");
		sc.skip("[\\r\\n]+");
		String str = sc.nextLine();
		System.out.println("토큰 처리 전 개수 : "+str.length());
		System.out.println("토큰 처리 후 글자 : "+tc.afterToken(str));
		System.out.println("토큰 처리 후 개수 : "+tc.afterToken(str).length());
		System.out.println("모두 대문자로 변환 : "+tc.afterToken(str.toUpperCase()));
		
	}

	public void inputMenu() { // 첫 글자 대문자와 찾을 문자 몇 개 있는지 출력
		System.out.print("문자열을 입력하세요 : ");
		sc.skip("[\\r\\n]+");
		String input = sc.nextLine();
		System.out.println("첫 글자 대문자 : "+tc.firstCap(input));
		System.out.print("찾을 문자 하나를 입력하세요 : ");
		String s = sc.next();
		char one = s.charAt(0);
		System.out.println(one+" 문자가 들어간 개수 : "+tc.findChar(input,one));
		
	}
	
	
	
}
