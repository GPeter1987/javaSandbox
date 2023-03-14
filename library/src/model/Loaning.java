package model;

import java.util.Date;

public class Loaning {
	// Kölcsönzés
	private int id;
	private int bookId;
	private int customerId;
	private String loanStart;
	private String loanEnd;
	private boolean isAvailable;
	
	public Loaning(int id, int bookId, int customerId, String loanStart, String loanEnd, boolean isAvailable) {
		this.id = id;
		this.bookId = bookId;
		this.customerId = customerId;
		this.loanStart = loanStart;
		this.loanEnd = loanEnd;
		this.isAvailable = isAvailable;
	}
	
	public Loaning( int bookId, int customerId, String loanStart, String loanEnd, boolean isAvailable) {
		this.bookId = bookId;
		this.customerId = customerId;
		this.loanStart = loanStart;
		this.loanEnd = loanEnd;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getLoanStart() {
		return loanStart;
	}

	public void setLoanStart(String loanStart) {
		this.loanStart = loanStart;
	}

	public String getLoanEnd() {
		return loanEnd;
	}

	public void setLoanEnd(String loanEnd) {
		this.loanEnd = loanEnd;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
