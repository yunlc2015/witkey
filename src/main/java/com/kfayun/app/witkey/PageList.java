/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * 数据分页列表类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class PageList<T> {

	/*
	 * 页码
	 */
	private int pageNo;
	/*
	 * 页大小
	 */
	private int pageSize;
	/*
	 * 总记寻数
	 */
	private int total;
	/*
	 * 分页数据
	 */
	private List<T> list;

	public PageList() {

	}

	public PageList(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list != null ? list : new ArrayList<T>();
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@JsonIgnore
	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

}
