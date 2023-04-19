package librarymanagementsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewBookAdminFrame extends JDialog implements ActionListener, WindowListener { 
	
	private AdminBookFrame adminBookFrame;
	
	// JInternalFrame Add Form
	private JLabel imageLbl = new JLabel();
	private JLabel titleLbl = new JLabel();
	private JLabel isbnLbl = new JLabel("ISBN");
	private JLabel isbnValLbl = new JLabel();
	private JLabel authorLbl = new JLabel("Author");
	private JLabel authorValLbl = new JLabel();
	private JLabel pagesLbl = new JLabel("Pages");
	private JLabel pagesValLbl = new JLabel();
	private JLabel publisherLbl = new JLabel("Publisher");
	private JLabel publisherValLbl = new JLabel();
	private JLabel publicationYearLbl = new JLabel("Publication Year");
	private JLabel publicationYearValLbl = new JLabel();
	private JLabel synopsisLbl = new JLabel("Synopsis");
	private JTextArea synopsisArea = new JTextArea();
	private JScrollPane synopsisPane = new JScrollPane(synopsisArea);
	private JLabel quantityLbl = new JLabel("Quantity");
	private JLabel quantityValLbl = new JLabel();
	private JButton okBtn = new JButton("OK");

	
	public void initComponent() {	
		addWindowListener(this);
		
		JPanel containerForm = new JPanel();
		
		// JInternal Frame Form
		GridBagLayout leftFormLayout = new GridBagLayout();
		GridBagConstraints leftFormConst = new GridBagConstraints();
		containerForm.setLayout(leftFormLayout);
		
		// Book Image
		leftFormConst.gridx = 0;	
		leftFormConst.gridy = 0;
		leftFormConst.fill = GridBagConstraints.VERTICAL;
		leftFormConst.anchor = GridBagConstraints.CENTER;
		leftFormConst.gridwidth = 2;
		leftFormConst.weightx= 1;
		leftFormConst.weighty= 1;
		containerForm.add(imageLbl, leftFormConst);
		
		// Title Label
		leftFormConst.fill = GridBagConstraints.NONE;
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.insets = new Insets(10, 0, 20, 0);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		containerForm.add(titleLbl, leftFormConst);
		
		// ISBN Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.gridwidth = 1;
//		leftFormConst.anchor = GridBagConstraints.LINE_START;
		leftFormConst.weightx = 0.25;
		leftFormConst.weighty = 0;
		leftFormConst.insets = new Insets(5, 5, 5, 5);
		leftFormConst.fill = GridBagConstraints.HORIZONTAL; 
		containerForm.add(isbnLbl, leftFormConst);
		
		// ISBN Value Label
		leftFormConst.weightx = 0.75;
		leftFormConst.gridx++;
		containerForm.add(isbnValLbl, leftFormConst);
		
		// Author Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		containerForm.add(authorLbl, leftFormConst);
		
		// Author Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		containerForm.add(authorValLbl, leftFormConst);
		
		// Pages Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		containerForm.add(pagesLbl, leftFormConst);
		
		// Pages Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		containerForm.add(pagesValLbl, leftFormConst);
		
		// Publisher Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		containerForm.add(publisherLbl, leftFormConst);
		
		// Publisher Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		containerForm.add(publisherValLbl, leftFormConst);
		
		// Publication Year Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		containerForm.add(publicationYearLbl, leftFormConst);
		
		// Publication Year Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		containerForm.add(publicationYearValLbl, leftFormConst);
		
		// Synopsis Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		containerForm.add(synopsisLbl, leftFormConst);
		
		// Synopsis Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		synopsisArea.setRows(5);
		synopsisArea.setColumns(20);
		synopsisArea.setEditable(false);
		// line wrapping
		synopsisArea.setLineWrap(true);
		// word wrapping
		synopsisArea.setWrapStyleWord(true);
//		synopsisArea.setEnabled(false);
		containerForm.add(synopsisPane, leftFormConst);
		
		// Quantity Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.25;
		leftFormConst.ipady = 10;
		containerForm.add(quantityLbl, leftFormConst);
		
		// Quantity Value Label
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.75;
		containerForm.add(quantityValLbl, leftFormConst);
		
		// Ok Button
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.ipadx = 15;
		leftFormConst.gridwidth = 2;
		leftFormConst.fill = GridBagConstraints.NONE;
		leftFormConst.gridwidth = 2;
		okBtn.addActionListener(this);
		containerForm.add(okBtn, leftFormConst);		
		
		setLayout(new GridBagLayout());
		GridBagConstraints containerConst = new GridBagConstraints();
		containerConst.insets = new Insets(0, 50, 0, 50);
		containerConst.weightx = 1.0;
		containerConst.fill = GridBagConstraints.HORIZONTAL;
		add(containerForm, containerConst);
//		setResizable(false);
	}

	public ViewBookAdminFrame(AdminBookFrame adminBookFrame, int width, int height, Book book) {
		super(adminBookFrame);
		
		this.adminBookFrame = adminBookFrame;
		width = (int)(width / 1.125);
		height = (int)(height / 1.125);
		setSize(width, height);
		setLocationRelativeTo(adminBookFrame);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("resources/bookImage.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image imgResize = img.getScaledInstance(height / 4, width / 4, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imgResize);
		imageLbl.setIcon(imageIcon);
		
		setTitle("View Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
		
		imageLbl.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.3)));
		
		titleLbl.setText(book.getName());
		isbnValLbl.setText(book.getIsbn());
		authorValLbl.setText(book.getAuthor());
		pagesValLbl.setText(String.valueOf(book.getPages()));
		publisherValLbl.setText(book.getPublisher());
		publicationYearValLbl.setText(String.valueOf(book.getPublishYear()));
		synopsisArea.setText(book.getSynopsis());
		quantityValLbl.setText(String.valueOf(book.getQuantity()));
	}
	
	public ViewBookAdminFrame() {
		
	}
	
	public void exitFrame() {
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
		if(e.getSource().equals(okBtn)) {
			exitFrame();
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
