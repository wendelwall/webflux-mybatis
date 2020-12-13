package com.wendel.page;

import com.wendel.utils.Constants;

import lombok.Data;

/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2019 riskeys Corporation. All rights reserved.
 * @create ：2019/11/6 19:43
 */
@Data
public class PageInfo implements java.io.Serializable {

	private static final long serialVersionUID = -5214737781026620938L;

	private int pageIndex = Constants.DEFAULT_PAGE_INDEX;
	private int pageSize = Constants.DEFAULT_PAGE_SIZE;

	private long total;

	private String order;

	public PageInfo() {}

	public PageInfo(Integer pageIndex, Integer pageSize) {
		if (pageIndex == null || pageIndex == 0) {
			this.pageIndex = Constants.DEFAULT_PAGE_INDEX;
		} else {
			this.pageIndex = pageIndex;
		}
		if (pageSize == null || pageSize == 0) {
			this.pageSize = Constants.DEFAULT_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}
	
	// public PageInfo(Integer pageIndex, Integer pageSize, String order) {
	// 	this(pageIndex, pageSize);
	// 	if (StringUtils.isNotBlank(order)) {
	// 		this.order = StringUtil.camelhumpToUnderline(order);
	// 	}
	// }

	// public void setOrder(String order){
	// 	if (StringUtils.isNotBlank(order)) {
	// 		this.order = StringUtil.camelhumpToUnderline(order);
	// 	}
	// }
}