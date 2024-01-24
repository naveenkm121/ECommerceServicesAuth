package com.ecommerce.ui.dto;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.utils.BaseApiResponse;
import com.ecommerce.ui.utils.PageableApiResponse;

public class BaseApiResModel  implements BaseApiResponse{
	
	private String message;
	private int status;
	private Object data;
	

	public BaseApiResModel() {
		super();
		this.message=AppConstants.FAILURE_MSG;
		this.status=AppConstants.FAILURE_STATUS;
		this.data=null;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void setMessage(String message) {
		this.message=message;
		
	}

	@Override
	public void setStatus(int status) {
		this.status=status;
		
	}

	@Override
	public void setData(Object data) {
		this.data=data;
		
	}

	@Override
	public String toString() {
		return "BaseApiResModel [message=" + message + ", status=" + status + ", data=" + data + "]";
	}


}
