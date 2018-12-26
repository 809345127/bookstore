package com.shize.bookstore.beans;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> list; // 查询出来的每页的记录所放的集合
	public static final int PAGE_SIZE = 4; // 指定每页显示的记录数
	private int pageNo; // 当前页
	// private int totalPageNo; //总页数，通过计算得到
	private int totalRecord; // 总记录数，通过查询数据库得到

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNo() {
		// 如果当前页是小于1的数，直接返回1
		if (pageNo < 1) {
			return 1;
			// 如果当前页是大于总页数的，则返回最后一页
		} else if (pageNo > getTotalPageNo()) {
			return getTotalPageNo();
		} else {
			// 如果不小于1并且不大于总页数则正常返回
			return pageNo;
		}
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	// 总页数是由总记录数与每页显示的条数计算得到
	public int getTotalPageNo() {
		if (totalRecord % PAGE_SIZE == 0) {
			return totalRecord / PAGE_SIZE;
		} else {
			return totalRecord / PAGE_SIZE + 1;
		}
	}

	// public void setTotalPageNo(int totalPageNo) {
	// this.totalPageNo = totalPageNo;
	// }
	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	// 判断是否有上一页
	public boolean isHasPrev() {
		return getPageNo() > 1;
	}

	// 获取上一页
	public int getPrev() {
		return isHasPrev() ? getPageNo() - 1 : 1;
	}

	// 判断是否有下一页
	public boolean isHasNext() {
		return getPageNo() < getTotalPageNo();
	}

	// 获取下一页
	public int getNext() {
		return isHasNext() ? getPageNo() + 1 : getTotalPageNo();
	}
}
