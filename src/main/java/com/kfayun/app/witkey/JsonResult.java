/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey;

/*
 * JSON返回类
 * 封装JSON请求的返回内容。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class JsonResult<T> {

	private JsonResult() {

	}

	/*
	 * 错误码
	 */
	private int errCode;
	/*
	 * 错误消息
	 */
	private String errMsg;
	/*
	 * 数据
	 */
	private T data;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> JsonResult<T> ok(T data) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setData(data);
		return jr;
	}

	public static <T> JsonResult<T> ok(int errCode, T data) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setErrCode(errCode);
		jr.setData(data);
		return jr;
	}

	public static <T> JsonResult<T> fail(int errCode, String errMsg) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setErrCode(errCode);
		jr.setErrMsg(errMsg);
		return jr;
	}

	public static <T> JsonResult<T> fail(int errCode, String errMsg, T data) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setErrCode(errCode);
		jr.setErrMsg(errMsg);
		jr.setData(data);
		return jr;
	}
}
