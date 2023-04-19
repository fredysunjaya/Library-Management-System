package librarymanagementsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class AddUpdateBookFormAdminFrame extends JDialog implements ActionListener, WindowListener {
	private NumberFormat format = NumberFormat.getInstance();
    private NumberFormatter formatter = new NumberFormatter(format) {
    	public Object stringToValue(String text) throws ParseException {
    		if(text.equals("") || text.equals("-")) {
    			return null;
    		}
    		return super.stringToValue(text);
    	};
    };
	
    private Library library;
	private AdminBookFrame adminBookFrame = new AdminBookFrame();
	
	// Add/Update Form
	private JLabel titleLbl = new JLabel("ADD BOOK");
	private JLabel isbnLbl = new JLabel("ISBN");
	private JTextField isbnTxt = new JTextField();
	private JLabel nameLbl = new JLabel("Title");
	private JTextField nameTxt = new JTextField();
	private JLabel authorLbl = new JLabel("Author");
	private JTextField authorTxt = new JTextField();
	private JLabel pagesLbl = new JLabel("Pages");
	private JFormattedTextField pagesTxt = new JFormattedTextField(formatter);
	private JLabel publisherLbl = new JLabel("Publisher");
	private JTextField publisherTxt = new JTextField();
	private JLabel publicationYearLbl = new JLabel("Publication Year");
	private JFormattedTextField publicationYearTxt = new JFormattedTextField(formatter);
	private JLabel synopsisLbl = new JLabel("Synopsis");
	private JTextArea synopsisArea = new JTextArea();
	private JScrollPane synopsisPane = new JScrollPane(synopsisArea);
	private JLabel quantityLbl = new JLabel("Quantity");
	private JTextField quantityTxt = new JTextField();
	private JButton addBtn = new JButton("Add");
	private JButton cancelBtn = new JButton("Cancel");
	
	
	public void initComponent() {
		// Agar saat number dimasukkan tidak ada grouping seperti ribu, juta, miliar, dst		
		format.setGroupingUsed(false);
		formatter.setValueClass(Long.class);
		// Agar input yang dimasukkan hanya bisa yang valid saja yaitu angka, apabila true maka huruf bisa diinputkan juga
	    formatter.setAllowsInvalid(false);
	    // Untuk setiap pengetikkan maka nilainya langsung dicommit
	    formatter.setCommitsOnValidEdit(true);
		
		addWindowListener(this);
		
		JPanel containerForm = new JPanel();
		
		// JInternal Frame Form
		GridBagLayout leftFormLayout = new GridBagLayout();
		GridBagConstraints leftFormConst = new GridBagConstraints();
		containerForm.setLayout(leftFormLayout);
		
		// Title Label
		leftFormConst.gridx = 0;	
		leftFormConst.gridy = 0;
		leftFormConst.gridwidth = 2;
		leftFormConst.weightx= 1; 
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 32));
		leftFormConst.insets = new Insets(0, 0, 30, 0);
		containerForm.add(titleLbl, leftFormConst);
		
		// ISBN Label
//		leftFormConst.anchor = GridBagConstraints.LINE_END;
		leftFormConst.fill = GridBagConstraints.HORIZONTAL; 
		leftFormConst.gridwidth = 1;
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormConst.ipady = 10;
		leftFormConst.insets = new Insets(5, 0, 5, 0);
		containerForm.add(isbnLbl, leftFormConst);
		
		// ISBN text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(isbnTxt, leftFormConst);
		
		// Title Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(nameLbl, leftFormConst);
		
		// Title text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(nameTxt, leftFormConst);	
		
		// Author Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(authorLbl, leftFormConst);
		
		// Author text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(authorTxt, leftFormConst);
		
		// Pages Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(pagesLbl, leftFormConst);
		
		// Pages field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(pagesTxt, leftFormConst);
		
		// Publisher Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(publisherLbl, leftFormConst);
		
		// Publisher field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(publisherTxt, leftFormConst);
		
		// Publication Year Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(publicationYearLbl, leftFormConst);
		
		// Publication Year field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(publicationYearTxt, leftFormConst);
		
		// Synopsis Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		containerForm.add(synopsisLbl, leftFormConst);
		
		// Synopsis field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		synopsisArea.setRows(5);
		synopsisArea.setColumns(20);
		// line wrapping
		synopsisArea.setLineWrap(true);
		// word wrapping
		synopsisArea.setWrapStyleWord(true);
		containerForm.add(synopsisPane, leftFormConst);
		
		// Quantity Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormConst.ipady = 10;
		containerForm.add(quantityLbl, leftFormConst);
		
		// Quantity field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		containerForm.add(quantityTxt, leftFormConst);
		
		JPanel buttonForm = new JPanel();
		buttonForm.setLayout(new GridBagLayout());
		GridBagConstraints buttonConst = new GridBagConstraints();
		
		// Add Button
		buttonConst.gridx = 0;
		buttonConst.gridy++;
		buttonConst.weightx = 0.5;
		buttonConst.insets = new Insets(30, 0, 0, 0);
		buttonConst.fill = GridBagConstraints.NONE;
		addBtn.addActionListener(this);
		buttonForm.add(addBtn, buttonConst);
		
		// Cancel Button
		buttonConst.gridx++;
		cancelBtn.addActionListener(this);
		buttonForm.add(cancelBtn, buttonConst);
		
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.gridwidth = 2;
		containerForm.add(buttonForm, leftFormConst);

		setLayout(new GridBagLayout());
		GridBagConstraints containerConst = new GridBagConstraints();
		containerConst.insets = new Insets(0, 100, 0, 100);
		containerConst.weightx = 1.0;
		containerConst.fill = GridBagConstraints.HORIZONTAL;
		add(containerForm, containerConst);
//		setResizable(false);
	}

	public AddUpdateBookFormAdminFrame(Library library, AdminBookFrame adminBookFrame, int width, int height) {
		super(adminBookFrame);
		
		this.library = library;
		this.adminBookFrame = adminBookFrame;
		setSize((int)(width / 1.125), (int)(height / 1.125));
		setLocationRelativeTo(adminBookFrame);
		
		addBtn.setText("Add");
		setTitle("Add Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
	}
	
	public AddUpdateBookFormAdminFrame(Library library, AdminBookFrame adminBookFrame, int width, int height, Book book) {
		super(adminBookFrame);
		
		this.library = library;
		this.adminBookFrame = adminBookFrame;
		setSize((int)(width / 1.125), (int)(height / 1.125));
		setLocationRelativeTo(adminBookFrame);
		
		setTitle("Update Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
		
		addBtn.setText("Update");
		isbnTxt.setText(book.getIsbn());
		nameTxt.setText(book.getName());
		authorTxt.setText(book.getAuthor());
		pagesTxt.setText(String.valueOf(book.getPages()));
		publisherTxt.setText(book.getPublisher());
		publicationYearTxt.setText(String.valueOf(book.getPublishYear()));
		synopsisArea.setText(book.getSynopsis());
		quantityTxt.setText(String.valueOf(book.getQuantity()));
	}
	
	public AddUpdateBookFormAdminFrame() {
		
	}
	
	public void resetForm() {
		nameTxt.setText("");
		authorTxt.setText("");
		publicationYearTxt.setText("");
		pagesTxt.setText("");
		isbnTxt.setText("");
		publisherTxt.setText("");
		synopsisArea.setText("");
		quantityTxt.setText("");
	}
	
	public void exitFrame() {
		resetForm();
		adminBookFrame.setEnabled(true);
		adminBookFrame.setAlwaysOnTop(true);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		adminBookFrame.setAlwaysOnTop(false);
		dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(cancelBtn)) {
			exitFrame();
			
		} else if(e.getSource().equals(addBtn)) {
			String[] result = library.checkNewBook(nameTxt.getText(), authorTxt.getText(), 
					publicationYearTxt.getText(), pagesTxt.getText(),
					isbnTxt.getText(), publisherTxt.getText(), synopsisArea.getText(), 
					quantityTxt.getText());
			
			if(result[8].equals("")) {
				if(addBtn.getText().equals("Add")) {
					adminBookFrame.addData(result[0], result[1], 
							Integer.parseInt(result[2]), Integer.parseInt(result[3]), 
							result[4], result[5], result[6], 
							Integer.parseInt(result[7]));
					
				} else if(addBtn.getText().equals("Update")) {
					adminBookFrame.updateData(result[0], result[1], 
							Integer.parseInt(result[2]), Integer.parseInt(result[3]), 
							result[4], result[5], result[6], 
							Integer.parseInt(result[7]));
					
				}	
				resetForm();
				exitFrame();				
				
			} else {
				JOptionPane.showMessageDialog(this, result[8], "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} 
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		exitFrame();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}