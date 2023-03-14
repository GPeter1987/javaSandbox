package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;



public class Database {
	private final String jdbc = "jdbc:mysql://localhost/library";
	private final String userName = "root";
	private final String password = "";
	
	private List<Book> books;
	private List<Customer> customers;
	private List<Loaning> loaning;
	
	Connection conn;
	
	public Database() {
		this.books = new LinkedList<>();
		this.customers = new LinkedList<>();
		this.loaning = new LinkedList<>();
		this.conn = null;
	}
	
	// Connect to DB
	public void connectDB() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Disconnect from DB
	public void disconnectDB() throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}
	// Write Customer
	public void writeCustomer(Customer customer) {
		String sql = "INSERT INTO customer (NAME, PHONENUM, EMAIL) VALUES (?,?,?)";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhoneNum());
			pstmt.setString(3, customer.getEmail());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("New Customer record is saved in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Write Book
	public void writeBook(Book book) {
		String sql = "INSERT INTO book (TITLE, AUTHOR, TYPE, CATEGORY, ISREADED) VALUES (?,?,?,?,?)";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, String.valueOf(book.getType()));
			pstmt.setString(4, String.valueOf(book.getCategory()));
			pstmt.setBoolean(5, book.getIsReaded());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("New Book record is saved in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Write Loaning
	public void writeLoaning(Loaning loaning) {
		String sql = "INSERT INTO loaning (BOOK_ID, CUSTOMER_ID, LOANSTART, LOANEND, ISAVAILABLE) VALUES (?,?,?,?,?)";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loaning.getBookId());
			pstmt.setInt(2, loaning.getCustomerId());
			pstmt.setDate(3, Date.valueOf(loaning.getLoanStart()));
			pstmt.setDate(4, Date.valueOf(loaning.getLoanEnd()));
			pstmt.setBoolean(5, loaning.getIsAvailable());
			pstmt.executeUpdate();
			
			disconnectDB();
			
			System.out.println("New Loaning record is saved in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Update Customer
	public void updateCustomer(Customer customer) {
		String sql = "UPDATE customer SET NAME = ?, PHONENUM = ?, EMAIL = ? WHERE ID = ?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhoneNum());
			pstmt.setString(3, customer.getEmail());
			pstmt.setInt(4, customer.getId());
			pstmt.executeUpdate();
			
			disconnectDB();
			
			System.out.println("Customer record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Update Book
	public void updateBook(Book book) {
		String sql = "UPDATE book SET TITLE = ?, AUTHOR = ?, TYPE = ?, CATEGORY = ?, ISREADED = ? WHERE ID = ?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, String.valueOf(book.getType()));
			pstmt.setString(4, String.valueOf(book.getCategory()));
			pstmt.setBoolean(5, book.getIsReaded());
			pstmt.setInt(6, book.getId());
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Book record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Update Loaning
	public void updateLoaning(Loaning loaning) {
		String sql = "UPDATE loaning SET BOOK_ID=?, CUSTOMER_ID=?, LOANSTART=?, LOANEND=?, ISAVAILABLE=? WHERE ID=?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loaning.getBookId());
			pstmt.setInt(2, loaning.getCustomerId());
			pstmt.setDate(3, Date.valueOf(loaning.getLoanStart()));
			pstmt.setDate(4, Date.valueOf(loaning.getLoanEnd()));
			pstmt.setBoolean(5, loaning.getIsAvailable());
			pstmt.setInt(6, loaning.getId());
			pstmt.executeUpdate();
			
			disconnectDB();
			
			System.out.println("Loaning record is updated in the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Read Customer
	public List<Customer> readCustomers() {
		String sql = "select * from customer";
		List<Customer> customers = new LinkedList<>();
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Customer cus = new Customer(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PHONENUM"), rs.getString("EMAIL"));
				customers.add(cus);
			}
			disconnectDB();
			
			return customers;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// Read Book
	public List<Book> readBooks() {
		String sql = "select * from book";
		List<Book> books = new LinkedList<>();
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("AUTHOR"), BookType.valueOf(rs.getString("TYPE")), BookCategory.valueOf(rs.getString("CATEGORY")), rs.getBoolean("ISREADED"));
				books.add(book);
			}
			disconnectDB();
			
			return books;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// Read Loaning(all)
	public List<Loaning> readLoanings() {
		String sql = "select * from loaning";
		List<Loaning> loanings = new LinkedList<>();
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Loaning loaning = new Loaning(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("CUSTOMER_ID"), rs.getString("LOANSTART"), rs.getString("LOANEND"), rs.getBoolean("ISAVAILABLE"));
				loanings.add(loaning);
			}
			disconnectDB();
			
			return loanings;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// Read Loaning(expired)
	public List<Loaning> readLoaningsExp() {
		String sql = "select * from loaning WHERE ISAVAILABLE = 0";
		List<Loaning> loanings = new LinkedList<>();
		
		try {
			connectDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Loaning loaning = new Loaning(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("CUSTOMER_ID"), rs.getString("LOANSTART"), rs.getString("LOANEND"), rs.getBoolean("ISAVAILABLE"));
				loanings.add(loaning);
			}
			disconnectDB();
			
			return loanings;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Read loan by book id
	public Loaning getLoanByBookId(int bookId) {
		String sql = "select * from loaning WHERE ISAVAILABLE = 0 AND BOOK_ID = ?";
		Loaning loaning = null;
		try {
			connectDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loaning = new Loaning(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("CUSTOMER_ID"), rs.getString("LOANSTART"), rs.getString("LOANEND"), rs.getBoolean("ISAVAILABLE"));
			}
			disconnectDB();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return loaning;
	}
	
	// Delete Customer
	public void deleteCustomer(int id) {
		String sql = "DELETE FROM customer WHERE ID = ?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Customer with the ID:" + id + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Delete Book
	public void deleteBook(int id) {
		String sql = "DELETE FROM book WHERE ID = ?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Book with the ID:" + id + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Delete Loaning
	public void deleteLoaning(int id) {
		String sql = "DELETE FROM loaning WHERE ID = ?";
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			disconnectDB();
			System.out.println("Loaning with the ID:" + id + " is deleted from the DB!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Search Book(s)
	public List<Book> searchBooks(BookSearchType searchType, String rawParam) {
		LinkedList<Book> books = new LinkedList<>();
		int paramInt = 0;
		boolean paramBool = false;
		String param = rawParam;
		String sql = null;
		
		if(searchType == BookSearchType.BYID) {
			sql = "SELECT * FROM book WHERE ID = ?";
			paramInt = Integer.valueOf(rawParam);
		} else if(searchType == BookSearchType.BYTITLE) {
			sql = "SELECT * FROM book WHERE TITLE LIKE ?";
			param = "%" + rawParam + "%";
		} else if(searchType == BookSearchType.BYAUTHOR) {
			sql = "SELECT * FROM book WHERE AUTHOR LIKE ?";
			param = "%" + rawParam + "%";
		} else if(searchType == BookSearchType.BYTYPE) {
			sql = "SELECT * FROM book WHERE TYPE LIKE ?";
			param = "%" + rawParam + "%";
		} else if(searchType == BookSearchType.BYCATEGORY) {
			sql = "SELECT * FROM book WHERE CATEGORY LIKE ?";
			param = "%" + rawParam + "%";
		} else if(searchType == BookSearchType.BYREADED) {
			sql = "SELECT * FROM book WHERE ISREADED = ?";
			paramBool = true;
		} else if(searchType == BookSearchType.BYNOTREAD) {
			sql = "SELECT * FROM book WHERE ISREADED = ?";
			paramBool = false;
		}
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			if(searchType == BookSearchType.BYID) {
				pstmt.setInt(1, paramInt);
			} else if(searchType == BookSearchType.BYREADED || searchType == BookSearchType.BYNOTREAD) {
				pstmt.setBoolean(1, paramBool);
			} else {
				pstmt.setString(1, param);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				books.add(new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("AUTHOR"), BookType.valueOf(rs.getString("TYPE")), BookCategory.valueOf(rs.getString("CATEGORY")), rs.getBoolean("ISREADED")));
			}
			
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	// Search Customer(s)
	public List<Customer> searchCustomer(CustomerSearchType searchType , String rawParam) {
		LinkedList<Customer> customers = new LinkedList<>();
		int paramInt = 0;
		String param = "%" + rawParam + "%";
		String sql = "";
		
		// ID
		if(searchType == CustomerSearchType.BYID) {
			paramInt = Integer.valueOf(rawParam);
			sql = "SELECT * FROM customer WHERE ID = ?";
		}
		
		// NAME
		if(searchType == CustomerSearchType.BYNAME) {
			sql = "SELECT * FROM customer WHERE NAME LIKE ?";
		}
		
		// PHONENUM
		if(searchType == CustomerSearchType.BYPHONENUM) {
			sql = "SELECT * FROM customer WHERE PHONENUM LIKE ?";
		}
		
		// EMAIL
		if(searchType == CustomerSearchType.BYEMAIL) {
			sql = "SELECT * FROM customer WHERE EMAIL LIKE ?";
		}
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			if(searchType == CustomerSearchType.BYID) {
				pstmt.setInt(1, paramInt);
			} else {
				pstmt.setString(1, param);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				customers.add(new Customer(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PHONENUM"), rs.getString("EMAIL")));
			}
			
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public List<Loaning> searchLoaning(LoaningSearchType searchType, String rawParam) {
		LinkedList<Loaning> loanings = new LinkedList<>();
		int paramInt = 0;
		String param = "%" + rawParam + "%";
		String sql = "";
		
		// ID
		if(searchType == LoaningSearchType.BYID) {
			paramInt = Integer.valueOf(rawParam);
			sql = "SELECT * FROM loaning WHERE ID = ?";
		}
		
		// BOOK NAME
		if(searchType == LoaningSearchType.BYBOOKNAME) {
			sql = "SELECT * FROM loaning WHERE BOOK_ID IN (SELECT ID FROM book WHERE TITLE LIKE ?)";
		}
		// CUSTOMER NAME
		if(searchType == LoaningSearchType.BYCUSTOMERNAME) {
			sql = "SELECT * FROM loaning WHERE CUSTOMER_ID IN (SELECT ID FROM customer WHERE NAME LIKE ?)";
		}
		
		try {
			connectDB();
			
			PreparedStatement pstmt; 
			pstmt = conn.prepareStatement(sql);
			if(searchType == LoaningSearchType.BYID) {
				pstmt.setInt(1, paramInt);
			} else {
				pstmt.setString(1, param);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				loanings.add(new Loaning(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("CUSTOMER_ID"), rs.getString("LOANSTART"), rs.getString("LOANEND"), rs.getBoolean("ISAVAILABLE")));
			}
			
			disconnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loanings;
	}
}
