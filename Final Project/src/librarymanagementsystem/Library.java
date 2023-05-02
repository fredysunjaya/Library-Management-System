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
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
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
	private ArrayList<Author> authors = new ArrayList<>();
	
	public void readBooks() {
		File dummyBook = new File("resources/books.txt");
		try {
			Scanner scan = new Scanner(dummyBook);
			int i = 0;
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("~");
				String[] authorsName = line[1].split("#");
				String[] desc = line[2].split("#");
				ArrayList<Author> authorsTemp = new ArrayList<>();
				
				for(int j = 0; j < authorsName.length; j++) {
					String id = String.valueOf(authors.size() + 1);
					
					authors.add(new Author(id, authorsName[j], desc[j]));
					authorsTemp.add(new Author(id, authorsName[j], desc[j]));
				}
				
				
				books.add(new Book(line[0], authorsTemp, Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], line[6], line[7], Integer.parseInt(line[8])));
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
				printer.write(String.format("%s~", book.getName()));
				for(int i = 0; i < book.getAuthors().size(); i++) {
					if(i == book.getAuthors().size() - 1) {
						printer.write(String.format("%s~", book.getAuthors().get(i).getName()));
					} else {
						printer.write(String.format("%s#", book.getAuthors().get(i).getName()));
					}
				}
				for(int i = 0; i < book.getAuthors().size(); i++) {
					if(i == book.getAuthors().size() - 1) {
						printer.write(String.format("%s~", book.getAuthors().get(i).getDescription()));
					} else {
						printer.write(String.format("%s#", book.getAuthors().get(i).getDescription()));
					}
				}
				printer.write(String.format("%d~%d~%s~%s~%s~%d\n", book.getPublishYear(), book.getPages(), book.getIsbn(), book.getPublisher(), book.getSynopsis(), book.getQuantity()));	
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
		ArrayList<IssuedBook> issuedBooks = readRecords();
		
		try {
			Scanner scan = new Scanner(dummyMember);
			
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("\"");
				LocalDate birthDate = LocalDate.parse(line[2]);
				LocalDate joinDate = LocalDate.parse(line[6]);
				ArrayList<IssuedBook> issuedBooksTemp = new ArrayList<>();
				int[] removedIndex = new int[issuedBooks.size()];
				Arrays.fill(removedIndex, -1);
				
//				System.out.println(issuedBooks.size());
				if(issuedBooks.size() > 0) {
					int j = 0;
					int k = 0;
					for(IssuedBook issuedBook : issuedBooks) {
						String[] issuedId = issuedBook.getId().split("-");
//						System.out.println("IssuedId: " + issuedId[1]);
						if(issuedId[1].equalsIgnoreCase(line[0])) {
							issuedBooksTemp.add(issuedBook);
							
							IssuedBook issueBookTemporary = issuedBooksTemp.get(k);
							issueBookTemporary.setId(generateIdRecord(line[0], issueBookTemporary.getBook().getIsbn(), 
									issueBookTemporary.getIssueDate(), issueBookTemporary.getReturnDate()));
							
							removedIndex[j] = k;
							j++;
						}
						k++;
					}
					for(int i = removedIndex.length - 1; i >= 0; i--) {
//						System.out.println("Index: " + removedIndex[i]);													
						if(removedIndex[i] != -1) {
							issuedBooks.remove(removedIndex[i]);
//							System.out.println("removed Index: " + removedIndex[i]);							
						}
					}
				}
				
				members.add(new Member(line[0], line[1], birthDate, line[3], line[4], line[5], joinDate, issuedBooksTemp));
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
	
	public ArrayList<IssuedBook> readRecords() {
		File dummyRecords = new File("resources/records.txt");
		ArrayList<IssuedBook> issuedBooks = new ArrayList<>();
		
		try {
			Scanner scan = new Scanner(dummyRecords);
			
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("~");
				Book book = searchBook(line[1]);
				LocalDate issueDate = LocalDate.parse(line[2]);
				LocalDate returnDate = LocalDate.parse(line[3]);
				Fine fine = new Fine(generateFineId(line[0]), 0.0, "None");
				
				if(!line[4].equalsIgnoreCase("Returned") && returnDate.compareTo(today) >= 0) {
					line[4] = "Issued";
					fine.setStatus("None");
				} else if(!line[4].equalsIgnoreCase("Returned") && returnDate.compareTo(today) < 0) {
					line[4] = "Late";
					fine.setTotal(Math.abs(ChronoUnit.DAYS.between(today, returnDate) * 0.1));
					
				} else if(line[4].equalsIgnoreCase("Returned") && (!line[5].equalsIgnoreCase("None") && !line[5].equalsIgnoreCase("Paid"))) {
					fine.setTotal(Double.valueOf(line[5]));
					fine.setStatus("Not Paid");
				} else {
					fine.setStatus("Paid");
				}
				
//				System.out.println(fine.getTotal());
				issuedBooks.add(new IssuedBook(line[0], book, issueDate, returnDate, line[4], fine));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return issuedBooks;
	}
	
	public void writeRecords() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		File dummyRecords = new File("resources/records2.txt");
		
		try {
			FileWriter printer = new FileWriter(dummyRecords);

			for(Member member : members) {
				ArrayList<IssuedBook> issuedBooks = member.getIssuedBooks();
				for(IssuedBook issuedBook : issuedBooks) {
					printer.write(String.format("%s~%s~%s~%s~%s~%s", 
							issuedBook.getId(), issuedBook.getBook().getIsbn(), 
							dateFormat.format(issuedBook.getIssueDate()), dateFormat.format(issuedBook.getReturnDate()), 
							issuedBook.getStatus()));
					if(issuedBook.getStatus().equalsIgnoreCase("Paid")) {
						printer.write(String.format("~%s\n", String.valueOf(issuedBook.getFine())));	
					} else {
						printer.write(String.format("~%s\n", issuedBook.getStatus()));
					}
				}				
			}
			
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int checkAccount(String[] account) {
		return -1;
		
//		for(Admin admin : admins) {
//			if(account[0].equals(admin.getId()) && account[1].equals(admin.getPassword())) {
//				return -1;
//			}
//		}
//		
//		int i = 0;
//		
//		for(Member member : members) {
//			if(account[0].equals(member.getId()) && account[1].equals(member.getPassword())) {
//				return i;
//			}
//			i++;
//		}
//		
//		return -2;
	}
	
	public String generateIdMember() {
		return String.valueOf(Integer.parseInt(members.get(members.size() - 1).getId()) + 1);
	}
	
	public static String generateIdRecord(String memberId, String isbn, LocalDate issueDate, LocalDate returnDate) {
		String id = isbn.substring((isbn.length() / 2) - 1, (isbn.length() / 2) + 3) + issueDate.getDayOfMonth() + returnDate.getDayOfMonth() + "-" + memberId;
		return id;
	}
	
	public static String generateFineId(String recordId) {
		return recordId + "//" +String.valueOf((int)(Math.random() * 1001));
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
	public void updateBook(int selectedRow, String name, ArrayList<Author> authors, int publishYear, int pages, String isbn, String publisher,
			String synopsis, int quantity) {
		Book book = books.get(selectedRow);
		
		book.setName(name);
		book.setAuthors(authors);
		book.setPublishYear(publishYear);
		book.setPages(pages);
		book.setIsbn(isbn);
		book.setPublisher(publisher);
		book.setSynopsis(synopsis);
		book.setQuantity(quantity);
		
		books.set(selectedRow, book);
	}
	
	public void addMember(String id, String name, LocalDate birthDate, String email, String phoneNum, String password, LocalDate joinDate, ArrayList<IssuedBook> issuedBooks) {
		members.add(new Member(id, name, birthDate, email, phoneNum, password, joinDate, issuedBooks));
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
	
	public void addMemberIssuedBook() {
		
	}
	
	public Member searchMember(String id) {
		for(Member member : members) {
			if(member.getId().equals(id)) {
				return member;
			}
		}
		return null;
	}
	
	public Book searchBook(String isbn) {
		for(Book book : books) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	public Author searchAuthor(String name) {
		for(Author author : authors) {
			if(author.getName().toLowerCase().equals(name.toLowerCase())) {
				return author;
			}
		}
		return new Author(String.valueOf(authors.size()) + 1, name, "No Description Found");
	}
	
	// search record member
	public IssuedBook searchRecord(String id, ArrayList<IssuedBook> memberIssuedBooks) {
		for(IssuedBook issuedBook : memberIssuedBooks) {
			if(issuedBook.getId().equals(id)) {
				return issuedBook;
			}
		}
		return null;
	}
	
	// search record admin
	public IssuedBook searchRecord(String id) {
		for(Member member : members) {
			for(IssuedBook issuedBook : member.getIssuedBooks()) {
				if(issuedBook.getId().equals(id)) {
					return issuedBook;
				}
			}			
		}
		
		return null;
	}
	
//	public ArrayList<IssuedBook> filterRecords(String value, boolean type, ArrayList<IssuedBook> memberIssuedBooks) {
//		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
//		
//		if(type) {
//			for(IssuedBook issuedBook : memberIssuedBooks) {
//				if(!(issuedBook.getStatus().equals("Returned")) &&
//						(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
//						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
//						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
//						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase()))) {
//					searchedRecords.add(issuedBook);
//				}
//			}			
//		} else {
//			for(IssuedBook issuedBook : memberIssuedBooks) {
//				if(issuedBook.getId().toLowerCase().contains(value.toLowerCase()) ||
//						issuedBook.getMember().getName().toLowerCase().contains(value.toLowerCase()) ||
//						issuedBook.getBook().getIsbn().toLowerCase().contains(value.toLowerCase()) || 
//						issuedBook.getBook().getName().toLowerCase().contains(value.toLowerCase())) {
//					searchedRecords.add(issuedBook);
//				}
//			}
//		}
//		
//		return searchedRecords;
//	}
	
//	public ArrayList<IssuedBook> filterRecordsByDate(LocalDate fromDate, LocalDate toDate, boolean type, ArrayList<IssuedBook> memberIssuedBooks) {
//		ArrayList<IssuedBook> searchedRecords = new ArrayList<>();
//		
//		if(type) {
//			for(IssuedBook issuedBook : memberIssuedBooks) {
//				if(!issuedBook.getStatus().equals("Returned") && 
//						issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
//						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
//					
//					searchedRecords.add(issuedBook);
//				}
//			}
//		} else {
//			for(IssuedBook issuedBook : memberIssuedBooks) {
//				if(issuedBook.getIssueDate().compareTo(fromDate) >= 0 && 
//						issuedBook.getReturnDate().compareTo(toDate) <= 0) {
//					
//					searchedRecords.add(issuedBook);
//				}
//			}
//		}
//		
//		return searchedRecords;
//	}
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Library();
	}

}
