package librarymanagementsystem;

import java.time.LocalDate;

public class IssuedBook {
	// 3 status
	//	Issued
	//	Late
	//	Returned
	
	private String id;
	private Book book;
	private LocalDate issueDate;
	private LocalDate returnDate;
	private String status;
	private Fine fine;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public Fine getFine() {
		return fine;
	}
	
	public void setFine(Fine fine) {
		this.fine = fine;
	}

	public IssuedBook(String id, Book book, LocalDate issueDate, LocalDate returnDate, String status,Fine fine) {
		this.id = id;
		this.book = book;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.status = status;
		this.fine = fine;
	}

	public IssuedBook() {
		
	}
	
	public String getFineStatus() {
		if((fine.getStatus().equalsIgnoreCase("None") && fine.getTotal() == 0.0) || fine.getStatus().equalsIgnoreCase("Paid")) {
			return fine.getStatus();
		} 
		
		if(fine.getStatus().equalsIgnoreCase("Not Paid")) {
			return fine.getStatus() + "($" + String.valueOf(fine.getTotal()) + ")";			
		}
		return "$" + String.valueOf(fine.getTotal());
	}
}
