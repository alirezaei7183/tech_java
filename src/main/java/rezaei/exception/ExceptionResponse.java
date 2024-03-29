package rezaei.exception;

import java.util.Date;

public class ExceptionResponse {
	private String msg;
	private String data;

	public ExceptionResponse(String msg, String data) {
		this.msg = msg;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
