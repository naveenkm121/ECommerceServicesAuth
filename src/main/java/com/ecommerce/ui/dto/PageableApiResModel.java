package com.ecommerce.ui.dto;

import com.ecommerce.ui.utils.PageableApiResponse;

public class PageableApiResModel extends BaseApiResModel implements PageableApiResponse {
	

	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean isLast;


	@Override
	public void setPageNo(int pageNo) {
		this.pageNo=pageNo;
		
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize= pageSize;
	}

	@Override
	public void setTotalElements(long totalItems) {
		this.totalElements=totalItems;
		
	}

	@Override
	public void setTotalPages(int totalPages) {
		this.totalPages=totalPages;
		
	}

	@Override
	public void setIsLast(boolean isLast) {
		this.isLast=isLast;
		
	}

}
