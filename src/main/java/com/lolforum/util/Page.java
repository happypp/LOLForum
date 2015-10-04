package com.lolforum.util;

import java.util.ArrayList;
import java.util.List;

import com.lolforum.constant.Constant;

public class Page {
	private int pageCount;
	private int pageItemCount;
	private int currentIndex;
	private List<Integer> navItems;
	private Object object;
	
	public Page(int pageIndex, int count) {
		this.currentIndex = pageIndex;
		this.pageCount = (int) Math.ceil((double)count / Constant.LIST_ITEM_NUM);
		this.pageItemCount = Constant.LIST_ITEM_NUM;
		this.navItems = new ArrayList<Integer>();
		initNavItems();
	}
	
	public void initNavItems() {
		int length = Constant.LIST_PAGES;
		if(pageCount <= length) {
			for (int i = 1; i <= pageCount; i++) {
				navItems.add(i);
			}
			return;
		}
		if(currentIndex - length/2 <= 0) {
			for (int i = 1; i <= length; i++) {
				navItems.add(i);
			}
			return;
		}
		if(currentIndex + length/2 > pageCount) {
			for (int i = pageCount-length+1; i <= pageCount; i++) {
				navItems.add(i);
			}
			return;
		}
		for (int i = currentIndex-length/2; i <= currentIndex+length/2; i++) {
			navItems.add(i);
		}
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getPageItemCount() {
		return pageItemCount;
	}
	
	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public List<Integer> getNavItems() {
		return navItems;
	}
	
	public void setNavItems(List<Integer> navItems) {
		this.navItems = navItems;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObj(Object obj) {
		this.object = obj;
	}
	
}
