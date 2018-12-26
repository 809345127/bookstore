package com.shize.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	/**dfsa
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book;
	private int count;// 书本数量
	private double amount;// 购物项总金额 = count*book.getPrice();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		return new BigDecimal(count+"").multiply(new BigDecimal(book.getPrice()+"")).doubleValue();
	}

/*	public void setAmount(double amount) {
		this.amount = amount;
	}*/

	

	public CartItem() {
		super();
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + getAmount() + "]";
	}

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

}
