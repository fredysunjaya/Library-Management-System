package librarymanagementsystem;

import java.time.LocalDate;

public class Book {
	private String name;
	private String author;
	private int publishYear;
	private int pages;
	private String isbn;
	private String publisher;
	private String synopsis;
	private int quantity;
	
	public Book() {
		
	}

	public Book(String name, String author, int publishYear, int pages, String isbn, String publisher,
			String synopsis, int quantity) {
		this.name = name;
		this.author = author;
		this.publishYear = publishYear;
		this.pages = pages;
		this.isbn = isbn;
		this.publisher = publisher;
		this.synopsis = synopsis;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getPublishYear() {
		return publishYear;
	}
	
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}