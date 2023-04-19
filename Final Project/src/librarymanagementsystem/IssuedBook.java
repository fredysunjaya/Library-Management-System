package librarymanagementsystem;

import java.time.LocalDate;

public class IssuedBook {
	// 3 status
	//	Issued
	//	Late
	//	Returned
	
	private String id;
	private Member member;
	private Book book;
	private LocalDate issueDate;
	private LocalDate returnDate;
	private String status;
	private double fine;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public LocalDate getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getFine() {
		return fine;
	}
	
	public void setFine(double fine) {
		this.fine = fine;
	}

	public IssuedBook(String id, Member member, Book book, LocalDate issueDate, LocalDate returnDate, String status,double fine) {
		this.id = id;
		this.member = member;
		this.book = book;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.status = status;
		this.fine = fine;
	}

	public IssuedBook() {
		
	}
	
}
