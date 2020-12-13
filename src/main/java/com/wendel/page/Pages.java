package com.wendel.page;

import java.util.List;

import com.github.pagehelper.PageHelper;

/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2019 riskeys Corporation. All rights reserved.
 * @create ：2019/11/6 19:43
 */
public class Pages {

	public static<T> PagingModel<T> query(PageInfo condition, Query<T> query) {
		if (condition.getOrder() == null) {
			PageHelper.startPage(condition.getPageIndex(), condition.getPageSize());
		} else {
			PageHelper.startPage(condition.getPageIndex(), condition.getPageSize(), condition.getOrder());
		}
		List<T> results = query.doQuery();
		com.github.pagehelper.Page pageList = (com.github.pagehelper.Page) results;
		PagingModel pm = new PagingModel<>();
		pm.setPageIndex(pageList.getPageNum());
		pm.setPageSize(pageList.getPageSize());
		pm.setTotal(pageList.getTotal());
		pm.setData(pageList.getResult());
		return pm;
	}
}
