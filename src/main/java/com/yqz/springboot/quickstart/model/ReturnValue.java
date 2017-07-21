package com.yqz.springboot.quickstart.model;

public class ReturnValue<T> {

	private int code;
	private String message;
	private T result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public static <T> ReturnValue<T> buildSuccessResult(T t) {
		ReturnValue<T> o = new ReturnValue<>();
		o.setCode(0);
		o.setResult(t);
		return o;
	}

	public static <T> ReturnValue<T> buildErrorResult(int code, String message) {
		ReturnValue<T> o = new ReturnValue<>();
		o.setCode(code);
		o.setMessage(message);
		return o;
	}
}
