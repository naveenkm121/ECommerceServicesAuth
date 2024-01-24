package com.ecommerce.ui.utils;

public interface PageableApiResponse {
	
	void setPageNo(int pageNo);
	void setPageSize(int pageSize);
	void setTotalElements(long totalItems);
	void setTotalPages(int totalPages);
	void setIsLast(boolean isLast);

}
