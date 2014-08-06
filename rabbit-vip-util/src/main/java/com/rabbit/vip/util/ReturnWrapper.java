package com.rabbit.vip.util;

public class ReturnWrapper<T> {
	private T value;
	private String msg;

	public T getValue() {
		return value;
	}

	public ReturnWrapper<T> setValue(T value) {
		this.value = value;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ReturnWrapper<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

}
