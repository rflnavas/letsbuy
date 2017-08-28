package com.rnr.letsbuy.model.error;

import java.util.Date;

public class ErrorMessage{
	
	private String reason;
	
	private int status;
	
	private Date timestamp;
	
	private String path;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String reason, int status, Date timestamp) {
		super();
		this.reason = reason;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ErrorMessage [reason=" + reason + ", status=" + status + ", timestamp=" + timestamp + ", path=" + path
				+ "]";
	}
}