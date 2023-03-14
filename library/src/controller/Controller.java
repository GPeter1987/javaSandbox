package controller;

import java.util.Scanner;

import model.Book;
import model.BookCategory;
import model.BookSearchType;
import model.BookType;
import model.Customer;
import model.CustomerSearchType;
import model.Database;
import model.Loaning;
import model.LoaningSearchType;
import view.View;

public class Controller {
	private View view;
	private Database db;
	
	public Controller() {
		this.view = new View();
		this.db = new Database();
	}
	
	public void appStart() {
		view.printStart();
		startMenu();
		//view.printMenu();
		//view.printLoaningMenu();
		//db.writeCustomer(new Customer("Teszt Jolán", "+70-765-0514", "jolantesz@gmai.com"));;
		//db.writeBook(new Book("Wicca", "Scott Cunningham", BookType.PRINT, BookCategory.PILOSOPHY, false));
		//db.writeLoaning(new Loaning(3,2, "2023-03-09", "2023-03-14", true));
		//db.updateCustomer(new Customer(1,"Teszt Géza", "+20-125-0014", "gezatesz@gmai.com"));
		//db.updateBook(new Book(1,"Java", "Burd", BookType.EBOOK, BookCategory.PROGRAMMING, true));
		//db.updateLoaning(new Loaning(1,1,1, "2023-03-01", "2023-03-12", false));
		
		//db.deleteCustomer(5);
		//db.deleteBook(3);
		//db.deleteLoaning(3);
		
		/*
		view.printCustomerTable(db.readCustomers());
		view.printBookTable(db.readBooks());
		view.printLoaningTable(db.readLoanings());
		*/
		
		view.printEnd();
	}
	
	public void startMenu() {
		Scanner sc = new Scanner(System.in);
		view.printMenu();
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = sc.nextInt();
		
		switch(choosenMenuPoint) {
			case 1:
				view.printBookTable(db.readBooks());
				startMenu();
				break;
			case 2:
				addBook();			
				startMenu();
				break;
			case 3:
				deleteBook();
				startMenu();
				break;
			case 4:
				view.printCustomerTable(db.readCustomers());
				startMenu();
				break;
			case 5:
				addCustomer();
				startMenu();
				break;
			case 6:
				deleteCustomer();
				startMenu();
				break;
			case 7:
				startLoaningMenu();
				break;
			case 8:
				startSearchMenu();
				startMenu();
				break;
			case 9:
				break;
			default:
				System.out.println("The choosen menupoint is not a valid menupoint!");
				startMenu();
		}
		sc.close();
	}
	
	public void startLoaningMenu() {
		Scanner sc = new Scanner(System.in);
		view.printLoaningMenu();
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = sc.nextInt();
		
		switch(choosenMenuPoint) {
		case 1:
			addLoaning();
			startLoaningMenu();
			break;
		case 2:
			bookIsBack();
			startLoaningMenu();
			break;
		case 3:
			view.printLoaningTable(db.readLoanings());
			startLoaningMenu();
			break;
		case 4:
			view.printLoaningTable(db.readLoaningsExp());
			startLoaningMenu();
			break;
		case 5:
			startMenu();
			break;
		default:
			System.out.println("The choosen menupoint is not a valid menupoint!");
			startLoaningMenu();
		}
		sc.close();
	}
	
	public void startSearchMenu() {
		Scanner sc = new Scanner(System.in);
		view.printSearchMenu();
		
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = sc.nextInt();
		
		switch(choosenMenuPoint) {
		case 1:
			startBookSearchMenu();
			break;
		case 2:
			startCustomerSearchMenu();
			break;
		case 3:
			startLoaningSearchMenu();
			break;
		case 4:
			startMenu();
			break;
		default:
			System.out.println("The choosen menupoint is not a valid menupoint!");
			startSearchMenu();
		}
	}
	
	public void startBookSearchMenu() {
		Scanner sc = new Scanner(System.in);
		view.printBookSearchMenu();
		
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = Integer.valueOf(sc.nextLine());
		
		switch(choosenMenuPoint) {
		case 1:
			// Search Book by ID
			getBooksBy(BookSearchType.BYID);
			break;
		case 2:
			// Search Books by Title
			getBooksBy(BookSearchType.BYTITLE);
			break;
		case 3:
			// Search Books by Author
			getBooksBy(BookSearchType.BYAUTHOR);
			break;
		case 4:
			// Search Books by Type
			getBooksBy(BookSearchType.BYTYPE);
			break;
		case 5:
			// Search Books by Category
			getBooksBy(BookSearchType.BYCATEGORY);
			break;
		case 6:
			// Search Books by readed
			getBooksBy(BookSearchType.BYREADED);
			break;
		case 7:
			// Search Books by not readed
			getBooksBy(BookSearchType.BYNOTREAD);
			break;
		case 8:
			// Quit to an upper menu
			break;
		default:
			System.out.println("The choosen menupoint is not a valid menupoint!");
			startSearchMenu();
		}

	}
	
	public void startCustomerSearchMenu() {
		Scanner sc = new Scanner(System.in);
		view.printCustomerSearchMenu();
		
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = Integer.valueOf(sc.nextLine());
		
		switch(choosenMenuPoint) {
		case 1:
			// Search Customer by ID
			getCustomerBy(CustomerSearchType.BYID);
			break;
		case 2:
			// Search Customer by Name
			getCustomerBy(CustomerSearchType.BYNAME);
			break;
		case 3:
			// Search Customer by Phone number
			getCustomerBy(CustomerSearchType.BYPHONENUM);
			break;
		case 4:
			// Search Customer by E-mail
			getCustomerBy(CustomerSearchType.BYEMAIL);
			break;
		case 5:
			// Quit to Search menu
			break;
		default:
			System.out.println("The choosen menupoint is not a valid menupoint!");
			startSearchMenu();
		}
	}
	
	public void startLoaningSearchMenu() {
		Scanner sc = new Scanner(System.in);
		view.printLoaningSearchMenu();
		
		System.out.println("Please Choose a menu number:");
		int choosenMenuPoint = Integer.valueOf(sc.nextLine());
		
		switch(choosenMenuPoint) {
		case 1:
			// Search Loaning by ID
			getLoaningBy(LoaningSearchType.BYID);
			break;
		case 2:
			// Search Loaning by Book Name
			getLoaningBy(LoaningSearchType.BYBOOKNAME);
			break;
		case 3:
			// Search Loaning by Customer's name
			getLoaningBy(LoaningSearchType.BYCUSTOMERNAME);
			break;
		case 4:
			// Quit to Search menu
			break;
		default:
			System.out.println("The choosen menupoint is not a valid menupoint!");
			startSearchMenu();
		}
	}
	
	public void addBook() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Adding a new Book:");
		System.out.println("Please enter the books title:");
		String title = sc.nextLine();
		
		System.out.println("Please enter the books author:");
		String author = sc.nextLine();
		
		System.out.println("Please choose the books type from the following list: PRINT, PDF, EBOOK");
		String type = sc.nextLine().toUpperCase();
		
		System.out.println("Please choose the books category from the following list: SCIFY, FANTASY, INSTRUCTIONAL, PROGRAMMING, MARTIALART, PILOSOPHY, NONE");
		String cathegory = sc.nextLine().toUpperCase();
		
		System.out.println("Are you already read this book? If yes enter true else enter false.");
		boolean isReaded = sc.nextBoolean();
		sc.nextLine();
		
		Book book = new Book(	title, 
								author, 
								BookType.valueOf(type), 
								BookCategory.valueOf(cathegory), 
								isReaded);
		
		System.out.println("Heres your new book:");
		view.printBookRow(book);
		
		db.writeBook(book);
	}
	
	public void addCustomer() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Adding a new Customer:");
		System.out.println("Please enter the customers name:");
		String name = sc.nextLine();
		
		System.out.println("Please enter the customers phone number:");
		String phoneNum = sc.nextLine();
		
		System.out.println("Please enter the customers email address:");
		String email = sc.nextLine();
		
		Customer customer = new Customer(name, phoneNum, email);
		
		System.out.println("Heres your new Customer:");
		view.printCustomerRow(customer);
		
		
		db.writeCustomer(customer);
	}
	
	public void deleteBook() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Deleting a Book:");
		System.out.println("Please enter the books id number:");
		int id = sc.nextInt();
		db.deleteBook(id);
	}
	
	public void deleteCustomer() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Deleting a Customer:");
		System.out.println("Please enter the Customers id number:");
		int id = sc.nextInt();
		db.deleteCustomer(id);
	}
	
	public void addLoaning() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Loan a Book:");
		System.out.println("Please enter the Books ID:");
		int bookId = Integer.valueOf(sc.nextLine());
		System.out.println("Please enter the Customers ID:");
		int cusId = Integer.valueOf(sc.nextLine());
		System.out.println("Please enter the Loanings starting date in the valid format(yyyy-mm-dd):");
		String loanStart = sc.nextLine();
		System.out.println("Please enter the Loanings ending date in the valid format(yyyy-mm-dd):");
		String loanEnd = sc.nextLine();
		boolean isAvailable = false;
		Loaning loaning = new Loaning(bookId, cusId, loanStart, loanEnd, isAvailable);
		
		db.writeLoaning(loaning);
	}
	
	public void bookIsBack() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Book is Back:");
		System.out.println("Enter the books ID number:");
		int bookId = Integer.valueOf(sc.nextLine());
		
		Loaning loaning = db.getLoanByBookId(bookId);
		if(loaning != null) {
			view.printLoaningRow(loaning);
			db.deleteLoaning(loaning.getId());
			
		} else {
			System.out.println("The added Book ID in the loaning (" + bookId + ") was not found!");
		}
	}
	
	public void getBooksBy(BookSearchType searchType) {
		String question = "";
		String answ = "";
		
		if(searchType == BookSearchType.BYID) {
			question = "Please enter the Book's ID number:";
		}
		
		if(searchType == BookSearchType.BYTITLE) {
			question = "Please enter the Book's title or a title part:";
		}
		
		if(searchType == BookSearchType.BYAUTHOR) {
			question = "Please enter the Book's Author or a part of the Author's name:";
		}
		
		if(searchType == BookSearchType.BYTYPE) {
			question = "Please enter the Book's type:";
		}
		
		if(searchType == BookSearchType.BYCATEGORY) {
			question = "Please enter the Book's category:";
		}
		
		if(searchType != BookSearchType.BYNOTREAD && searchType != BookSearchType.BYREADED) {
			Scanner sc = new Scanner(System.in);
			System.out.println(question);
			answ = sc.nextLine();
		}
		
		view.printBookTable(db.searchBooks(searchType, answ));
	}
	
	public void getCustomerBy(CustomerSearchType searchType) {
		String question = "";
		String answ = "";
		
		if(searchType == CustomerSearchType.BYID) {
			question = "Please enter the Customer's ID number:";
		}
		
		if(searchType == CustomerSearchType.BYNAME) {
			question = "Please enter the Customer's name or a name part:";
		}
		
		if(searchType == CustomerSearchType.BYPHONENUM) {
			question = "Please enter the Customer's phone number or a number part:";
		}
		
		if(searchType == CustomerSearchType.BYEMAIL) {
			question = "Please enter the Customer's e-mail or an e-mail part:";
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println(question);
		answ = sc.nextLine();
		
		view.printCustomerTable(db.searchCustomer(searchType, answ));
	}
	
	public void getLoaningBy(LoaningSearchType searchType) {
		String question = "";
		String answ = "";
		
		// By ID
		if(searchType == LoaningSearchType.BYID) {
			question = "Please enter the Loaning's ID number:";
		}
		
		// By Book name
		if(searchType == LoaningSearchType.BYBOOKNAME) {
			question = "Please enter the Book's name or a name part:";
		}
		
		// By Customer name
		if(searchType == LoaningSearchType.BYCUSTOMERNAME) {
			question = "Please enter the Customer's name or a name part:";
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println(question);
		answ = sc.nextLine();
		
		view.printLoaningTable(db.searchLoaning(searchType, answ));
	}
}
