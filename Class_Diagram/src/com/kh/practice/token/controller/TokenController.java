package com.kh.practice.token.controller;

import java.util.StringTokenizer;

public class TokenController {
	// �ʵ��
	
	// �����ں�
	public TokenController() {
	}
	
	// �޼ҵ��
	public String afterToken(String str) { //������ ��ū���� ó���� ���ڿ� ��ȯ
		StringTokenizer st = new StringTokenizer(str, " "); // ������ ��ū���� ó����
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
	
	public String firstCap(String input) { // ù ���ڸ� �빮�ڷ� �ٲ� ���ڿ� ��ȯ
		
		StringBuffer ic = new StringBuffer(input);
		StringBuffer ic2 = ic.delete(0,1);
		char ic3 = input.charAt(0);
		String ch = Character.valueOf(ic3).toString();
		String ch2 = ch.toUpperCase();
		
		String input2 = ch2 + ic2; // �빮�ڷ� �ٲ� ���ڿ� ����ֱ�
		
		
		return input2;
	}
	
	
	public int findChar(String input, char one) { // ���ڿ� �ȿ� ã�� ���� ������ �� �� ���ִ��� ��ȯ

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
