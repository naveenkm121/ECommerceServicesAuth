package com.ecommerce.ui.utils;

public interface BaseApiResponse {
	

	
	String getMessage();
	
	int getStatus();
	Object getData();
	
	void setMessage(String message);
	void setStatus(int status);
	void setData(Object data);
	

}
