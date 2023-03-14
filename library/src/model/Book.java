package model;

public class Book {
	private int id;
	private String title;
	private String author;
	private BookType type;
	private BookCategory category;
	private Boolean isReaded;
	
	public Book(int id, String title, String author, BookType type, BookCategory category, Boolean isReaded) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.type = type;
		this.category = category;
		this.isReaded = isReaded;
	}
	
	public Book(String title, String author, BookType type, BookCategory category, Boolean isReaded) {
		this.title = title;
		this.author = author;
		this.type = type;
		this.category = category;
		this.isReaded = isReaded;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Boolean getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}
	
	
}
