package view;

import java.util.Iterator;
import java.util.List;

import model.Book;
import model.Customer;
import model.Loaning;

public class View {
	private String innerLine = "|======================|";
	
	public View() {
		
	}
	
	public void printStart() {
		System.out.println("|Program Start: Library|");
		System.out.println(innerLine);
	}
	
	// ======== Main menu
	public void printMenu() {
		System.out.println("|-------> MENU <-------|");
		System.out.println(innerLine);
		System.out.println("| 1 - List all Book    |");
		System.out.println("| 2 - Add a Book       |");
		System.out.println("| 3 - Delete a Book    |");
		System.out.println("| 4 - List all Customer|");
		System.out.println("| 5 - Add a Customer   |");
		System.out.println("| 6 - Delete a Customer|");
		System.out.println("| 7 - Loan a Book      |");
		System.out.println("| 8 - Search           |");
		System.out.println("| 9 - Quit             |");
		System.out.println(innerLine);
	}
	
	// ======== Loaning menu
	public void printLoaningMenu() {
		System.out.println(innerLine);
		System.out.println("|---> LOANING MENU <---|");
		System.out.println(innerLine);
		System.out.println("| 1 - Loan Book        |");
		System.out.println("| 2 - Book is Back     |");
		System.out.println("| 3 - Loan List        |");
		System.out.println("| 4 - Loan List (Late) |");
		System.out.println("| 5 - Back to the menu |");
		System.out.println(innerLine);
	}
	
	// ====== Menus for Searching
	public void printSearchMenu() {
		System.out.println(innerLine);
		System.out.println("|----> SEARCH MENU <----|");
		System.out.println(innerLine);
		System.out.println("| 1 - Search Book       |");
		System.out.println("| 2 - Search Customer 	|");
		System.out.println("| 3 - Search Loaning 	|");
		System.out.println("| 4 - Quit to main menu |");
		System.out.println(innerLine);
	}
	
	public void printBookSearchMenu() {
		System.out.println(innerLine);
		System.out.println("|------> BOOK SEARCH MENU <------|");
		System.out.println(innerLine);
		System.out.println("| 1 - Search Book by ID          |");
		System.out.println("| 2 - Search Book by Title       |");
		System.out.println("| 3 - Search Books by Author     |");
		System.out.println("| 4 - Search Books by Type       |");
		System.out.println("| 5 - Search Books by Category   |");
		System.out.println("| 6 - Search Books by readed     |");
		System.out.println("| 7 - Search Books by not readed |");
		System.out.println("| 8 - Quit to Search menu        |");
		System.out.println(innerLine);
	}
	
	public void printCustomerSearchMenu() {
		System.out.println(innerLine);
		System.out.println("|-------> CUSTOMER SEARCH MENU <------|");
		System.out.println(innerLine);
		System.out.println("| 1 - Search Customer by ID		      |");
		System.out.println("| 2 - Search Customer by Name         |");
		System.out.println("| 3 - Search Customer by Phone number |");
		System.out.println("| 4 - Search Customer by E-mail       |");
		System.out.println("| 5 - Quit to Search menu 		      |");
		System.out.println(innerLine);
	}
	
	public void printLoaningSearchMenu() {
		System.out.println(innerLine);
		System.out.println("|-------> LOANING SEARCH MENU <--------|");
		System.out.println(innerLine);
		System.out.println("| 1 - Search Loaning by ID             |");
		System.out.println("| 2 - Search Loanings by Book Name     |");
		System.out.println("| 3 - Search Loanings by Customer Name |");
		System.out.println("| 4 - Quit to Search menu              |");
		System.out.println(innerLine);
	}
	
	public void printEnd() {
		System.out.println(innerLine);
		System.out.println("|------> GOODBYE <-----|");
	}
	
	public void printBookRow(Book book) {
		String row = "| " + String.valueOf(book.getId()) + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getType() + " | " + book.getCategory() + " | " + book.getIsReaded();
		System.out.println(row);
	}
	
	public void printCustomerRow(Customer customer) {
		String row = "| " + String.valueOf(customer.getId()) + " | " + customer.getName() + " | " + customer.getPhoneNum() + " | " + customer.getEmail();
		System.out.println(row);
	}
	
	public void printLoaningRow(Loaning loaning) {
		String row = "| " + String.valueOf(loaning.getId() + " | " + String.valueOf(loaning.getBookId()) + " | " + String.valueOf(loaning.getCustomerId() + " | " + loaning.getLoanStart() + " | " + loaning.getLoanEnd() + " | " + loaning.getIsAvailable()));
		System.out.println(row);
	}
	
	public void printCustomerTable(List<Customer> customers) {
		String tableHeader = "| ID|    NAME    | PHONE NUMBER | E-MAIL";
		Iterator<Customer> cusIter = customers.iterator();
		
		System.out.println(tableHeader);

		while(cusIter.hasNext()) {
			printCustomerRow(cusIter.next());
		}
		System.out.println("|TABLE END!|");
		System.out.println("");
	}
	
	public void printBookTable(List<Book> books) {
		String tableHeader = "| ID| TITLE | AUTHOR | TYPE | CATEGORY | ISREADED";
		Iterator<Book> bookIter = books.iterator();
		
		System.out.println(tableHeader);
		while(bookIter.hasNext()) {
			printBookRow(bookIter.next());
		}
		System.out.println("|TABLE END!|");
		System.out.println("");
	}
	
	public void printLoaningTable(List<Loaning> loaning) {
		String tableHeader = "| ID| BOOK_ID | CUSTOMER_ID | LOAN_START | LOAN_END | ISAVAILABLE";
		Iterator<Loaning> loanIter = loaning.iterator();
		
		System.out.println(tableHeader);
		while(loanIter.hasNext()) {
			printLoaningRow(loanIter.next());
		}
		System.out.println("|TABLE END!|");
		System.out.println("");
	}
}
