package com.kh.practice.token.controller;

import java.util.StringTokenizer;

public class TokenController {
	// 필드부
	
	// 생성자부
	public TokenController() {
	}
	
	// 메소드부
	public String afterToken(String str) { //공백을 토큰으로 처리한 문자열 반환
		StringTokenizer st = new StringTokenizer(str, " "); // 공백을 토큰으로 처리함
		String[] arr = new String[st.countTokens()];
		
		int i =0;
		while(st.hasMoreTokens()) {
			arr[i] = st.nextToken();
			i++;
		}
		String st2 = "";
		for(int j=0 ; j<arr.length ; j++) {
			st2 += arr[j];
		}
		
		return st2;	
	}
	
	public String firstCap(String input) { // 첫 글자만 대문자로 바꾼 문자열 반환
		
		StringBuffer ic = new StringBuffer(input);
		StringBuffer ic2 = ic.delete(0,1);
		char ic3 = input.charAt(0);
		String ch = Character.valueOf(ic3).toString();
		String ch2 = ch.toUpperCase();
		
		String input2 = ch2 + ic2; // 대문자로 바꾼 문자열 집어넣기
		
		
		return input2;
	}
	
	
	public int findChar(String input, char one) { // 문자열 안에 찾을 문자 개수가 몇 개 들어가있는지 반환

		int count =0;
		char[] arr = new char[input.length()];
		for(int k=0; k<arr.length ; k++) {
			arr[k] = input.charAt(k);
		}
		
		for(int k=0; k<arr.length ; k++) {
			if(one == arr[k]) {
				count ++;
			}
		}
		
		
		return count;
	}
	
	
	

}
