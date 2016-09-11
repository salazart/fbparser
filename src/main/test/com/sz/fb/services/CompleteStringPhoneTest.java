package com.sz.fb.services;

public class CompleteStringPhoneTest {

	public static void main(String[] args) {
		
		String phone = String.format("%07d", 7776080);
		String fullPhone = "38067" + phone;
		System.out.println(fullPhone);
	}

}
