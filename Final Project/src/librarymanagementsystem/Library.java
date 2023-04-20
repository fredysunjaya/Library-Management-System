package librarymanagementsystem;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.text.DateFormatter;

public class Library {
	private LocalDate today = LocalDate.now();
	private LoginFrame loginFrame;
	private AdminBookFrame adminBookFrame;
	private AdminMemberFrame adminMemberFrame;
	private AdminIssuedRecordsFrame adminIssuedFrame;
	private MemberBookFrame memberBookFrame;
	private MemberIssuedRecordsFrame memberIssuedRecordsFrame;

	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<Member> members = new ArrayList<>();
	private ArrayList<Admin> admins = new ArrayList<>();
	private ArrayList<IssuedBook> issuedBooks = new ArrayList<>();
	
	public void readBooks() {
		File dummyBook = new File("resources/books.txt");
		try {
			Scanner scan = new Scanner(dummyBook);
			int i = 0;
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("~");
				
				books.add(new Book(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), line[4], line[5], line[6], Integer.parseInt(line[7])));
//				System.out.println(i);
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeBooks() {
		File dummyBook = new File("resources/books2.txt");
		
		try {
			FileWriter printer = new FileWriter(dummyBook);

			for(Book book : books) {
				printer.write(String.format("%s~%s~%d~%d~%s~%s~%s~%d\n", book.getName(), book.getAuthor(), book.getPublishYear(), book.getPages(), book.getIsbn(), book.getPublisher(), book.getSynopsis(), book.getQuantity()));	
//				System.out.println(book.getName());
			}
			
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readMembers() {
		File dummyMember = new File("resources/members.txt");
		
		try {
			Scanner scan = new Scanner(dummyMember);
			
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("\"");
				LocalDate birthDate = LocalDate.parse(line[2]);
				LocalDate joinDate = LocalDate.parse(line[6]);
				
				members.add(new Member(line[0], line[1], birthDate, line[3], line[4], line[5], joinDate));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeMembers() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		File dummyMember = new File("resources/members2.txt");
		
		try {
			FileWriter printer = new FileWriter(dummyMember);

			for(Member member : members) {
				printer.write(String.format("%s~%s~%s~%s~%s~%s~%s\n", 
						member.getId(), member.getName(), dateFormat.format(member.getBirthDate()), 
						member.getEmail(), member.getPhoneNum(), member.getPhoneNum(), member.getPassword(), dateFormat.format(member.getJoinDate())));
			}
			
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readRecords() {
		File dummyRecords = new File("resources/records.txt");
		
		try {
			Scanner scan = new Scanner(dummyRecords);
			
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("~");
				Member member = searchMember(line[1]);
				Book book = searchBook(line[2]);
				LocalDate issueDate = LocalDate.parse(line[3]);
				LocalDate returnDate = LocalDate.parse(line[4]);
				double fine = 0.0;
				
				if(!line[5].equalsIgnoreCase("Returned") && returnDate.compareTo(today) >= 0) {
					line[5] = "Issued";
				} else if(!line[5].equalsIgnoreCase("Returned") && returnDate.compareTo(today) <= 0) {
					line[5] = "Late";
					fine = ChronoUnit.DAYS.between(today, returnDate) * 0.1;
				}
				
				if(!line[6].equals("-")) {
					fine = Double.parseDouble(line[6]);
				}
				
				issuedBooks.add(new IssuedBook(line[0], member, book, issueDate, returnDate, line[5], fine));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeRecords() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		File dummyRecords = new File("resources/records2.txt");
		
		try {
			FileWriter printer = new FileWriter(dummyRecords);

			for(IssuedBook issuedBook : issuedBooks) {
				printer.write(String.format("%s~%s~%s~%s~%s~%s~%s\n", 
						issuedBook.getId(), issuedBook.getMember().getName(),
						issuedBook.getBook().getIsbn(), dateFormat.format(issuedBook.getIssueDate()),
						dateFormat.format(issuedBook.getReturnDate()), issuedBook.getStatus(),
						issuedBook.getFine()));
			}
			
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int checkAccount(String[] account) {
		for(Admin admin : admins) {
			if(account[0].equals(admin.getId()) && account[1].equals(admin.getPassword())) {
				return -1;
			}
		}
		
		int i = 0;
		
		for(Member member : members) {
			if(account[0].equals(member.getId()) && account[1].equals(member.getPassword())) {
				return i;
			}
			i++;
		}
		
		return -2;
	}
	
	public String generateIdMember() {
		return String.valueOf(Integer.parseInt(members.get(members.size() - 1).getId()) + 1);
	}
	
	public String generateIdRecord(String memberId, String isbn, LocalDate issueDate, LocalDate returnDate) {
		String id = memberId + isbn.substring((isbn.length() / 2) - 1, (isbn.length() / 2) + 3) + issueDate.getDayOfMonth() + returnDate.getDayOfMonth();
		
		return id;
	}
	
	public void addBook(String name, String author, int publishYear, int pages, String isbn, String publisher,
			String synopsis, int quantity) {
		books.add(new Book(name, author, publishYear, pages, isbn, publisher, synopsis, quantity));
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
	public void updateBook(int selectedRow, String name, String author, int publishYear, int pages, String isbn, String publisher,
			String synopsis, int quantity) {
		Book book = books.get(selectedRow);
		
		book.setName(name);
		book.setAuthor(author);
		book.setPublishYear(publishYear);
		book.setPages(pages);
		book.setIsbn(isbn);
		book.setPublisher(publisher);
		book.setSynopsis(synopsis);
		book.setQuantity(quantity);
		
		books.set(selectedRow, book);
	}
	
	public void addMember(String id, String name, LocalDate birthDate, String email, String phoneNum, String password, LocalDate joinDate) {
		members.add(new Member(id, name, birthDate, email, phoneNum, password, joinDate));
	}
	
	public void updateMember(int selectedRow, String email, String phoneNum, String password) {
		Member member = members.get(selectedRow);
		
//		member.setId(id);
//		member.setName(name);
//		member.setBirthDate(birthDate);
		member.setEmail(email);
		member.setPhoneNum(phoneNum);
		member.setPassword(password);
//		member.setJoinDate(joinDate);
		
		members.set(selectedRow, member);
	}
	
	public void removeMember(Member member) {
		members.remove(member);
	}
	
	public void addIssuedBook(Member member, Book book, LocalDate issueDate, LocalDate returnDate, String status, double fine) {
		String id = generateIdRecord(member.getId(), book.getIsbn(), issueDate, returnDate);
		
		issuedBooks.add(new IssuedBook(id, member, book, issueDate, returnDate, status, fine));
//		System.out.println(id);
//		System.out.println(member.getName());
	}
	
	public void changeIssuedBook(IssuedBook issuedBook) {
		if(issuedBook.getReturnDate().compareTo(today) != 0) {
			issuedBook.setReturnDate(today);
		}
		issuedBook.setStatus("Returned");
	}
	
	public String[] checkNewBook(String name, String author, String publishYear, String pages, String isbn, String publisher,
			String synopsis, String quantity) {
		String error = "";
		String[] result = new String[9];
		
		if(isbn.equals("")) {
			error = error.concat("ISBN can't be empty");			
//			publishYear = (int) (Math.random() * ((2023 - 1400) + 1));
		} else {
			result[4] = isbn;
		}
		
		if(name.equals("")) {
			error = error.concat("\nBook title can't be empty");
		} else {
			result[0] = name;
		}
		
		if(author.equals("")) {
			result[1] = "Anonymous";
		} else {
			result[1] = author;
		}
		
		if(String.valueOf(pages).equals("") || Integer.parseInt(pages) <= 0) {
			error = error.concat("\nPages can't be empty or smaller than 0");
		} else { 
			result[3] = pages;
		}
		
		if(publisher.equals("")) {
			error = error.concat("\nPublisher's name can't be empty");
		} else {
			result[5] = publisher;
		}
		
		if(publishYear.equals("") || Integer.parseInt(publishYear) <= 1400) {
			error = error.concat("\nPublication Year can't be empty or under 1400");
		} else {
			result[2] = publishYear;
		}
		
		if(synopsis.equals("")) {
			result[6] = "There is no synopsis";
		} else {
			result[6] = synopsis;
		}
		
		if(String.valueOf(quantity).equals("") || Integer.parseInt(quantity) <= 0) {
			error = error.concat("\nBook's quantity can't be empty or smaller than 0");				
		} else {
			result[7] = quantity;
		}
		
		result[8] = error;
		
		return result;
	}
	
	public String[] checkNewMember(String name, String birthDate, String email, String phoneNum, String password) {
		String error = "";
		String[] result = new String[6];
		
		if(name.equals("")) {
			error = error.concat("Name can't be empty");
		} else {
			result[0] = name;
		}
		
		if(birthDate.equals("")) {
			error = error.concat("\nBirthdate can't be empty");
		} else {
			result[1] = birthDate;
		}
		
		if(phoneNum.equals("") && email.equals("")) {
			error = error.concat("\nEither email or phone number cannot be empty");
		} else {
			if(!email.equals("") && email.contains("@")) {
				result[2] = email;
			} else if(!email.contains("@")) {
				error = error.concat("\nEmail must contain @");
			} else {
				result[2] = "-";
			}
			if(!phoneNum.equals("")) {
				result[3] = phoneNum;
			} else {
				result[3] = "-";
			}
		}
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		
		if(password.equals("")) {
			error = error.concat("\nPasswords can't be empty");
		} else if(!password.matches(regex)) {
			error = error.concat("\nPassword is at least 8 characters long\n Password must contains at least 1 lowercase, upercase, and special character");
		} else {
			result[4] = password;
		}
		
		result[5] = error;
		
		return result;
	}
	
	public String[] checkIssuedBook(String isbn, String name, LocalDate issueDate, LocalDate returnDate) {
		String error = "";
		String[] result = new String[5];
		
		if(isbn.equals("")) {
			error = error.concat("ISBN can't be empty");
		} else {
			result[0] = isbn;
		}
		
		if(name.equals("")) {
			error = error.concat("\nBook title can't be empty");
		} else {
			result[1] = name;
		}
		
		if(issueDate != null && returnDate != null) {
			if(returnDate.compareTo(issueDate) < 0) {
				error = error.concat("\nReturn date can't be set before the issue date");
			} else if(issueDate.compareTo(today) < 0 || returnDate.compareTo(today) < 0) {
				error = error.concat("\nIssue Date or Return Date can't be set before today");			
			} else if(issueDate.compareTo(returnDate) == 0) {
				error = error.concat("\nIssue Date and Return Date can't be on the same day");
			} 
		} else {
			if(issueDate == null) {
				error = error.concat("\nIssue Date can't be empty");
			} else {
				result[2] = issueDate.toString();			
			}
			
			if(returnDate == null) {
				error = error.concat("\nreturn Date can't be empty");
			} else {
				result[3] = returnDate.toString();			
			}
		}	
		
		result[4] = error;
		
		return result;
	}
	
	public Member searchMember(String id) {
		for(Member member : members) {
			if(member.getId().equals(id)) {
				return member;
			}
		}
		return null;
	}
	
	public int searchMemberPos(String id) {
		int i = 0;
		
		for(Member member : members) {
			if(member.getId().equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public Book searchBook(String isbn) {
		for(Book book : books) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	public int searchBookPos(String isbn) {
		int i = 0;
		
		for(Book book : books) {
			if(book.getIsbn().equals(isbn)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public IssuedBook searchRecord(String id) {
		for(IssuedBook issuedBook : issuedBooks) {
			if(issuedBook.getId().equals(id)) {
				return issuedBook;
			}
		}
		return null;
	}
	
	public IssuedBook searchRecord(String id, ArrayList<IssuedBook> memberIssuedBooks) {
		for(IssuedBook issuedBook : memberIssuedBooks) {
			if(issuedBook.getId().equals(id)) {
				return issuedBook;
			}
		}
		return null;
	}
	
	public ArrayList<Book> filterBooks(String value) {
		ArrayList<Book> searchedBooks = new ArrayList<>();
		
		for(Book book : books) {
			if(book.getIsbn().toLowerCase().contains(value.toLowerCase()) || 
					book.getName().toLowerCase().contains(value.toLowerCase()) || 
					book.getAuthor().toLowerCase().contains(value.toLowerCase()) ||
					book.getPublisher().toLowerCase().contains(value.toLowerCase())) {
				searchedBooks.add(book);
			}
		}
		
		return searchedBooks;
	}
	
	public ArrayList<Member> filterMembers(String value) {
		ArrayList<Member> searchedMembers = new ArrayList<>();
		
		for(Member member : members) {
			if(member.getName().toLowerCase().contains(value.toLowerCase()) || 
					member.getId().toLowerCase().contains(value.toLowerCase())) {
				searchedMembers.add(member);
			}
		}
		
		return searchedMembers;
	}
	
	public ArrayList<IssuedBook> filterRecords(String value, boolean type) {
		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
		
		if(type) {
			for(IssuedBook issuedBook : issuedBooks) {
				if(!(issuedBook.getStatus().equals("Returned")) &&
						(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase()))) {
					searchedRecords.add(issuedBook);
				}
			}			
		} else {
			for(IssuedBook issuedBook : issuedBooks) {
				if(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase())) {
					searchedRecords.add(issuedBook);
				}
			}
		}
		
		return searchedRecords;
	}
	
	public ArrayList<IssuedBook> filterRecords(String value, boolean type, ArrayList<IssuedBook> memberIssuedBooks) {
		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
		
		if(type) {
			for(IssuedBook issuedBook : memberIssuedBooks) {
				if(!(issuedBook.getStatus().equals("Returned")) &&
						(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase()))) {
					searchedRecords.add(issuedBook);
				}
			}			
		} else {
			for(IssuedBook issuedBook : memberIssuedBooks) {
				if(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase())) {
					searchedRecords.add(issuedBook);
				}
			}
		}
		
		return searchedRecords;
	}
	
	public ArrayList<IssuedBook> filterRecordsByDate(LocalDate fromDate, LocalDate toDate, boolean type) {
		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
		
		if(type) {
			for(IssuedBook issuedBook : issuedBooks) {
				if(!issuedBook.getStatus().equals("Returned") && 
						issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
					
					searchedRecords.add(issuedBook);
				}
			}
		} else {
			for(IssuedBook issuedBook : issuedBooks) {
				if(issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
					
					searchedRecords.add(issuedBook);
				}
			}
		}
		
		return searchedRecords;
	}
	
	public ArrayList<IssuedBook> filterRecordsByDate(LocalDate fromDate, LocalDate toDate, boolean type, ArrayList<IssuedBook> memberIssuedBooks) {
		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
		
		if(type) {
			for(IssuedBook issuedBook : memberIssuedBooks) {
				if(!issuedBook.getStatus().equals("Returned") && 
						issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
					
					searchedRecords.add(issuedBook);
				}
			}
		} else {
			for(IssuedBook issuedBook : memberIssuedBooks) {
				if(issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
					
					searchedRecords.add(issuedBook);
				}
			}
		}
		
		return searchedRecords;
	}
	
	public void logout(JFrame frame, int width, int height) {
		frame.dispose();
		
		loginFrame = new LoginFrame(this, width, height);
	}
	
	public void loginAdmin(int width, int height, Point point) {
		loginFrame.dispose();
		
		adminBookFrame = new AdminBookFrame(this, width, height, point);
		adminBookFrame.setVisible(true);
	}
	
	public void loginMember(int width, int height, Point point, Member member) {
		loginFrame.dispose();
		
		memberBookFrame = new MemberBookFrame(this, width, height, point, member);
		memberBookFrame.setVisible(true);
	}
	
	public void changeFrame(JFrame dest, JFrame src) {
		dest.setVisible(true);
		src.dispose();
	}
	
	public Library() {
		admins.add(new Admin("ariya", "ariya"));
		admins.add(new Admin("fredy", "fredy"));
		admins.add(new Admin("neisya", "neisya"));
		
		readBooks();
		readMembers();
		readRecords();
		loginFrame = new LoginFrame(this, 1280, 720);
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}
	
	public ArrayList<IssuedBook> getIssuedBooks() {
		return issuedBooks;
	}

	public void setIssuedBooks(ArrayList<IssuedBook> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Library();
	}

}
