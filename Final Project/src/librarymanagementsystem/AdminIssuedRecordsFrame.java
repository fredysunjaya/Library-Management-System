package librarymanagementsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class AdminIssuedRecordsFrame extends JFrame implements ActionListener{
	private Library library;
	private boolean type;
	
	// North
	private JPanel menubar = new JPanel();
	private JButton bookBtn = new JButton("Books", new ImageIcon("resources/bookIcon.png"));
	private JButton memberBtn = new JButton("Members", new ImageIcon("resources/membersIcon.png"));
	private JButton issuedBtn = new JButton("Issued Books", new ImageIcon("resources/issuedIcon.png"));
	private JButton recordBtn = new JButton("Records", new ImageIcon("resources/recordIcon.png"));
	private JButton logoutBtn = new JButton("Log Out", new ImageIcon("resources/logoutIcon.png"));
	
	// Center 
	private JTable issuedTable = new JTable();
	private Object[] issuedColumn = {"ID", "Name", "ISBN", "Title", "Issue Date", "Return Date", "Status", "Fine"};
	private JScrollPane issuedScrollPane = new JScrollPane(issuedTable);
	private DefaultTableModel dtmIssued;
	private DefaultTableModel dtmRecords;
	private DefaultTableModel dtmSearchByDateIssued;
	private DefaultTableModel dtmSearchIssued;
	
	// South
	private JPanel formPanel = new JPanel();
	private JPanel fromToPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel fromLbl = new JLabel("From");
	private JDateChooser fromDateChooser = new JDateChooser();
	private JLabel toLbl = new JLabel("To");
	private JDateChooser toDateChooser = new JDateChooser();
	private JButton searchBtnFromTo = new JButton("Search");
	private JTextField searchTxt = new JTextField();
	private JButton searchBtnTxt = new JButton("Search");
	private JButton returnedBtn = new JButton("Returned");
	private JButton viewBtn = new JButton("View Details");
	private JButton resetBtn = new JButton("Reset");
	
	public void loadTable(ArrayList<IssuedBook> issuedBooks) {
		issuedTable.getTableHeader().setReorderingAllowed(false);
		issuedTable.getTableHeader().setResizingAllowed(false);
		issuedTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		if(type) {
			dtmIssued = new DefaultTableModel(issuedColumn, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			
			for(IssuedBook issuedBook : issuedBooks) {
				if(issuedBook.getStatus().equalsIgnoreCase("Returned")) {
					continue;
				}
				
				Object[] issuedFile = {issuedBook.getId(), issuedBook.getMember().getName(), issuedBook.getBook().getIsbn(),
						issuedBook.getBook().getName(), issuedBook.getIssueDate(), issuedBook.getReturnDate(), 
						issuedBook.getStatus(), issuedBook.getFine()};
				
				dtmIssued.addRow(issuedFile);
			}
			issuedTable.setModel(dtmIssued);
		} else {
			dtmRecords = new DefaultTableModel(issuedColumn, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			
			for(IssuedBook issuedBook : issuedBooks) {
				Object[] issuedFile = {issuedBook.getId(), issuedBook.getMember().getName(), issuedBook.getBook().getIsbn(),
						issuedBook.getBook().getName(), issuedBook.getIssueDate(), issuedBook.getReturnDate(), 
						issuedBook.getStatus(), issuedBook.getFine()};
				
				dtmRecords.addRow(issuedFile);
			}
			issuedTable.setModel(dtmRecords);
		}
		
	} 
	
	public void initComponent(ArrayList<IssuedBook> issuedBooks) {
		setLayout(new BorderLayout());
		
		GridBagLayout menubarLayout = new GridBagLayout();
		GridBagConstraints menubarConst = new GridBagConstraints();
		menubar.setLayout(menubarLayout);
		
		// Books button
		menubarConst.gridx = 0;
		menubarConst.weightx = 0.25;
		menubarConst.insets = new Insets(10, 0, 10, 0);
		bookBtn.addActionListener(this);
		bookBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    bookBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		menubar.add(bookBtn, menubarConst);
		
		// Members Button
		menubarConst.gridx++;
		memberBtn.addActionListener(this);
		memberBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    memberBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		menubar.add(memberBtn, menubarConst);
		
		// Issued Book Button
		menubarConst.gridx++;
		issuedBtn.addActionListener(this);
		issuedBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    issuedBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		menubar.add(issuedBtn, menubarConst);
		
		// Records of Issued Book Button
		menubarConst.gridx++;
		recordBtn.addActionListener(this);
		recordBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    recordBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		menubar.add(recordBtn, menubarConst);
		
		// Logout Button
		menubarConst.gridx++;
		logoutBtn.addActionListener(this);
		logoutBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    logoutBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		menubar.add(logoutBtn, menubarConst);
		
		// Table of Issued
		loadTable(issuedBooks);
		
		// Issued Book Form
		GridBagLayout formLayout = new GridBagLayout();
		GridBagConstraints formConst = new GridBagConstraints();
		formPanel.setLayout(formLayout);
		
		// From To Panel
		GridBagLayout fromToLayout = new GridBagLayout();
		GridBagConstraints fromToConst = new GridBagConstraints();
		fromToPanel.setLayout(fromToLayout);
		fromToPanel.setBackground(Color.CYAN);
		
		// From Label
		fromToConst.gridx = 0;
		fromToConst.gridy = 0;
		fromToConst.insets = new Insets(0, 5, 0, 5);
		fromToConst.weightx = 0.05;
		fromToConst.fill = GridBagConstraints.HORIZONTAL;
		fromToPanel.add(fromLbl, fromToConst);
		
		// From Date Chooser
		fromToConst.gridx++;
		fromToConst.weightx = 0.35;
		fromToPanel.add(fromDateChooser, fromToConst);
		
		// To Label4
		fromToConst.gridx++;
		fromToConst.weightx = 0.05;
		fromToPanel.add(toLbl, fromToConst);
		
		// To Date Chooser
		fromToConst.gridx++;
		fromToConst.weightx = 0.35;
		fromToPanel.add(toDateChooser, fromToConst);
		
		// Search Button
		fromToConst.gridx++;
		fromToConst.weightx = 0.1;
		searchBtnFromTo.addActionListener(this);
		fromToPanel.add(searchBtnFromTo, fromToConst);
		
		// Search Panel
		GridBagLayout searchLayout = new GridBagLayout();
		GridBagConstraints searchFormConst = new GridBagConstraints();
		searchPanel.setLayout(searchLayout);
		searchPanel.setBackground(Color.GREEN);
		
		// Search Text Field
		searchFormConst.gridx = 0;
		searchFormConst.gridy = 0;
		searchFormConst.weightx = 0.8;
		searchFormConst.fill = GridBagConstraints.HORIZONTAL;
		searchFormConst.insets = new Insets(0, 5, 0, 5);
		searchPanel.add(searchTxt, searchFormConst);
		
		// Search Button
		searchFormConst.gridx++;
		searchFormConst.weightx = 0.2;
		searchBtnTxt.addActionListener(this);
		searchPanel.add(searchBtnTxt, searchFormConst);
		
		// Button Panel
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints buttonFormConst = new GridBagConstraints();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setBackground(Color.RED);
		buttonFormConst.gridx = 0;
		buttonFormConst.gridy = 0;
		buttonFormConst.weightx = 0.5;
		buttonFormConst.fill = GridBagConstraints.HORIZONTAL;
		buttonFormConst.insets = new Insets(0, 5, 0, 5);
		
		// Returned Button
		if(type) {
			returnedBtn.addActionListener(this);
			buttonPanel.add(returnedBtn, buttonFormConst);			
			buttonFormConst.gridx++;
		}

		// View Button
		viewBtn.addActionListener(this);
		buttonPanel.add(viewBtn, buttonFormConst);

		issuedTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		formConst.gridx = 0;
		formConst.gridy = 0;
		formConst.gridwidth = 2;
		formConst.insets = new Insets(20, 100, 0, 100);
		formConst.fill = GridBagConstraints.HORIZONTAL;
		formPanel.add(fromToPanel, formConst);
		
		formConst.gridwidth = 1;
		formConst.gridy++;
		formConst.weightx = 0.75;
		formConst.insets = new Insets(10, 100, 0, 100);
		formPanel.add(searchPanel, formConst);
		
		formConst.gridx++;
		formConst.weightx = 0.25;
		formPanel.add(buttonPanel, formConst);
		
		// Reset Button
		formConst.insets = new Insets(30, 100, 10, 20);
		formConst.anchor = GridBagConstraints.LINE_END;
		formConst.gridy++;
		formConst.fill = GridBagConstraints.NONE;
		resetBtn.addActionListener(this);
		formPanel.add(resetBtn, formConst);
		
		add(menubar, "North");
		add(issuedScrollPane, "Center");
		add(formPanel, "South");
	}
	
	public AdminIssuedRecordsFrame(Library library, int width, int height, Point point, boolean type) {
		// true = issued book
		// false = record
		
		this.library = library;
		this.type = type;
		
		initComponent(library.getIssuedBooks());
		setSize(width, height);
		setTitle("Library Management System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(point);
//		setVisible(true);
	}

	public AdminIssuedRecordsFrame() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(bookBtn)) {
			Point point = getLocationOnScreen();
			library.changeFrame(new AdminBookFrame(library, getWidth(), getHeight(), point), this);
			
		} else if(e.getSource().equals(memberBtn)) {
			Point point = getLocationOnScreen();
			library.changeFrame(new AdminMemberFrame(library, getWidth(), getHeight(), point), this);
			
		} else if(e.getSource().equals(issuedBtn)) {
			if(!type) {
				Point point = getLocationOnScreen();
				library.changeFrame(new AdminIssuedRecordsFrame(library, getWidth(), getHeight(), point, true), this);				
			}
			
		} else if(e.getSource().equals(recordBtn)) {
			if(type) {
				Point point = getLocationOnScreen();
				library.changeFrame(new AdminIssuedRecordsFrame(library, getWidth(), getHeight(), point, false), this);				
			}
			
		} else if(e.getSource().equals(logoutBtn)) {
			library.logout(this, getWidth(), getHeight());
			
		} else if(e.getSource().equals(viewBtn)) {			
			int selectedRow = issuedTable.getSelectedRow();
			
			if(selectedRow != -1) {
				setEnabled(false);

				ViewRecordFrame viewRecord = new ViewRecordFrame(this, getWidth(), getHeight(), 
						library.searchRecord(issuedTable.getValueAt(selectedRow, 0).toString()));
			} else {
				JOptionPane.showMessageDialog(this, "Choose Data to View", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(e.getSource().equals(searchBtnFromTo)) {
			if(fromDateChooser.getDate() != null && toDateChooser.getDate() != null) {
				LocalDate fromDate = fromDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate toDate = toDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				dtmSearchByDateIssued = new DefaultTableModel(issuedColumn, 0) {
					@Override
					public boolean isCellEditable(int row, int column) {
						// TODO Auto-generated method stub
						return false;
					}
				};
				ArrayList<IssuedBook> searchedRecords = library.filterRecordsByDate(fromDate, toDate, type);
				
				for(IssuedBook issuedBook : searchedRecords) {
					Object[] issuedFile = {issuedBook.getId(), issuedBook.getMember().getName(), issuedBook.getBook().getIsbn(),
							issuedBook.getBook().getName(), issuedBook.getIssueDate(), issuedBook.getReturnDate(), 
							issuedBook.getStatus(), issuedBook.getFine()};
					dtmSearchByDateIssued.addRow(issuedFile);
				}
				
				issuedTable.setModel(dtmSearchByDateIssued);
				issuedTable.invalidate();
			}			
		} else if(e.getSource().equals(searchBtnTxt)) {
			String searchValue = searchTxt.getText();
			
			if(!searchValue.equals("")) {
				dtmSearchIssued = new DefaultTableModel(issuedColumn, 0) {
					@Override
					public boolean isCellEditable(int row, int column) {
						// TODO Auto-generated method stub
						return false;
					}
				};
				ArrayList<IssuedBook> searchedRecords = library.filterRecords(searchValue, type);
				
				for(IssuedBook issuedBook : searchedRecords) {
					Object[] issuedFile = {issuedBook.getId(), issuedBook.getMember().getName(), issuedBook.getBook().getIsbn(),
							issuedBook.getBook().getName(), issuedBook.getIssueDate(), issuedBook.getReturnDate(), 
							issuedBook.getStatus(), issuedBook.getFine()};
					dtmSearchIssued.addRow(issuedFile);
				}
				
				issuedTable.setModel(dtmSearchIssued);
				issuedTable.invalidate();
			}
		} else if(e.getSource().equals(returnedBtn)) {
			int selectedRow = issuedTable.getSelectedRow();
			
			if(selectedRow != -1) {
				int answer = JOptionPane.showConfirmDialog(this, "Are you sure this book has been returned?", "Returning Book", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(answer == JOptionPane.YES_OPTION) {
					IssuedBook issuedBook = library.searchRecord(issuedTable.getValueAt(selectedRow, 0).toString());
					
					library.changeIssuedBook(issuedBook);
					loadTable(library.getIssuedBooks());
					issuedTable.setModel(dtmIssued);
					issuedTable.invalidate();				
				}
			} else {
				JOptionPane.showMessageDialog(this, "Choose entry that want to be returned", "Error", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("asd");
			}
		}  else if(e.getSource().equals(resetBtn)) {
			if(!searchTxt.getText().equals("")) {
				issuedTable.setModel(dtmIssued);
				issuedTable.invalidate();				
				searchTxt.setText("");
			}
		}
	}
}