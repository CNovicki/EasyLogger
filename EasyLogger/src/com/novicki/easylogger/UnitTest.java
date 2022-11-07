package com.novicki.easylogger;

public class UnitTest {

	public static void main(String[] args) {		
		LogTest.passArgs(args);
		
		LogTest.d("d", 34);
		LogTest.i("info");
		LogTest.v("verbose");
		LogTest.w("warning");
		LogTest.e("error");
		LogTest.f("fatal");
		
	}
	
}