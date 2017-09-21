package com.midian.base.bean;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;

/**
 * 结果
 * 
 * @author XuYang
 * 
 */
public class NetResult extends NetBase {
	public String code = "";
	public String message = "";
	public String errorCode = "";
	public static Gson gson = new Gson();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isOK() {
		return "1".equals(code);
	}

	public static NetResult parse(String json) throws AppException {
		NetResult res = new NetResult();
		try {
			res = gson.fromJson(json, NetResult.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
		return res;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static Gson getGson() {
		return gson;
	}

	public static void setGson(Gson gson) {
		NetResult.gson = gson;
	}
}
