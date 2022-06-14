package com.ceco.common.exception;

import lombok.extern.slf4j.Slf4j;


/**
 * 自定义异常类
 *
 */
@Slf4j
public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer errcode;
	private String mymessage;

	public CommonException(Exception e){
		this(e.getMessage());
	}
	public CommonException(String message){
		this(ErrCode.ERR_CODE_UNKNOWN,ErrCode.ERR_MSG_UNKNOWN + String.format("(%s)",message));
	}

	public CommonException(Integer errcode, String message){
		this.setErrcode(errcode);
		this.setMymessage(message);
		log.error("错误编码：{}，错误描述：{}",errcode,message);
	}
	
	public Integer getErrcode() {
		return errcode;
	}
	
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	
	public String getMymessage() {
		return mymessage;
	}
	
	public void setMymessage(String mymessage) {
		this.mymessage = mymessage;
	}
	
	@Override
	public String getMessage(){
		return "错误编码：" + this.getErrcode() + "，错误描述：" + this.getMymessage();
	}
	
}

