package com.shize.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, CartItem> map = new LinkedHashMap<>();
	private int totalCount;// 全部购物项CartItem的总数量
	private double totalAmount;// 全部购物项CartItem的总金额

	public ShoppingCart(Map<String, CartItem> map, int totalCount, double totalAmount) {
		super();
		this.map = map;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
	}

	public ShoppingCart() {
		super();
	}

	@Override
	public String toString() {
		return "ShoppingCart [map=" + map + ", totalCount=" + getTotalCount() + ", totalAmount=" + getTotalAmount() + "]";
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	/*
	 * public void setMap(Map<String, CartItem> map) { this.map = map; }
	 */
	
	public int getTotalCount() {
		totalCount = 0;
		for (CartItem cartItem : getCartItemList()) {
			totalCount = totalCount + cartItem.getCount();
		}

		return totalCount;
	}

	/*
	 * public void setTotalCount(int totalCount) { this.totalCount = totalCount; }
	 */
	
	public double getTotalAmount() {
		totalAmount = 0;
		BigDecimal bd1 = new BigDecimal(totalAmount + "");
		for (CartItem cartItem : getCartItemList()) {
			BigDecimal bd2 = new BigDecimal("" + cartItem.getAmount());
			
			bd1 = bd1.add(bd2);
		} 
		return bd1.doubleValue();
	}
	/*
	 * public void setTotalAmount(double totalAmount) { this.totalAmount =
	 * totalAmount; }
	 */

	// 提供一个Map转List的方法
	public List<CartItem> getCartItemList() {
		return new ArrayList<CartItem>(map.values());
	}
	
	public void addBookCart(Book book){
		CartItem cartItem = map.get(book.getId().toString());
		if (cartItem == null) {
			//第一次添加图书
			int count = 1;
			map.put(book.getId().toString(), new CartItem(book,count));
		}else {
			cartItem.setCount(cartItem.getCount()+1);
		}
	}

	public void clear() {
		map.clear();
	}

	public void delCartItem(String bookId) {
		map.remove(bookId);
	}
	
	public void updateCartItem(String bookId, String countStr) {
		
		
		CartItem cartItem = map.get(bookId);
		
		int num = cartItem.getCount();
		
		try {
			num = Integer.parseInt(countStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		cartItem.setCount(num);
	}
}

