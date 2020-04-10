package com.test.action;

/*
 * ActionForward 클래스
 * 	1. Action 인터페이스에서 리턴타입으로 사용될 클래스.
 * 	2. isRedirect() 메소드
 * 		- jsp 페이지(false)
 * 		- *.do 페이지(true)
 * 	3. path() 메소드 : 파일 경로 지정.
 */

public class ActionForward {
	
	private boolean isRedirect;
	private String path;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
