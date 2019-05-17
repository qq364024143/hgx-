package com.hgx.my_boke_api.common.responseResult;

/**
 * 接口响应实体
 * @author hgx
 *
 * @param <T>
 */
public class ResponseResult<T> {
	
	private String code;
	private String message;
	private T data;
	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseResult(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", message=" + message
				+ ", data=" + data + "]";
	}
	
	
}
