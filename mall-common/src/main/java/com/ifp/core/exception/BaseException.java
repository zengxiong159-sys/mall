package com.ifp.core.exception;

public class BaseException extends Exception {
	private static final long serialVersionUID = 7254894132443453561L;
	private String errorCode = "SYEC0001";
	private String errorMessage = "系统繁忙,请稍后再试";

	public BaseException() {
	}

	public BaseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public BaseException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BaseException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		String errorMsg = this.getErrorMessage();
		if (this.errorCode != null) {
			if (errorMsg != null) {
				errorMsg = "[" + this.errorCode + "] " + errorMsg;
			} else {
				errorMsg = "[" + this.errorCode + "]";
			}
		}

		return errorMsg;
	}
@Override
	public String toString() {
		return this.getCause() == null ? super.toString() : super.toString() + " cause: " + this.getCause().toString();
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return null == this.errorMessage ? super.getMessage() : this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

