package librarymanagementsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewRecordFrame extends JDialog implements ActionListener, WindowListener {
	private AdminIssuedRecordsFrame adminIssuedFrame;
	private MemberIssuedRecordsFrame memberIssuedFrame;
	
	// JDialog Form
	private JPanel containerForm = new JPanel();
	private JLabel userImageLbl = new JLabel();
	private JLabel bookImageLbl = new JLabel();
	private JLabel idLbl = new JLabel("ID");
	private JLabel idValLbl = new JLabel();
	private JLabel nameLbl = new JLabel("Name");
	private JLabel nameValLbl = new JLabel();
	private JLabel isbnLbl = new JLabel("Birthdate");
	private JLabel isbnValLbl = new JLabel();
	private JLabel titleLbl = new JLabel("Email");
	private JLabel titleValLbl = new JLabel();
	private JLabel issueDateLbl = new JLabel("Phonenum");
	private JLabel issueDateValLbl = new JLabel();
	private JLabel returnDateLbl = new JLabel("Join Date");
	private JLabel returnDateValLbl = new JLabel();
	private JLabel statusLbl = new JLabel("Status");
	private JLabel statusValLbl = new JLabel();
	private JLabel fineLbl = new JLabel("Fine");
	private JLabel fineValLbl = new JLabel();
	private JButton okBtn = new JButton("OK");
	
	public void initComponent() {	
		addWindowListener(this);
		
		// JDialog Form
		GridBagLayout formLayout = new GridBagLayout();
		GridBagConstraints formConst = new GridBagConstraints();
		containerForm.setLayout(formLayout);
		
		// Image Panel
		JPanel imgPanel = new JPanel();
		GridBagLayout imgLayout = new GridBagLayout();
		GridBagConstraints imgConst = new GridBagConstraints();
		imgPanel.setLayout(imgLayout);
		imgConst.gridx = 0;	
		imgConst.gridy = 0;
		imgConst.fill = GridBagConstraints.VERTICAL;
		imgConst.insets = new Insets(0, 50, 0, 50);
		
		if(adminIssuedFrame != null) {
			// Member Image
			imgPanel.add(userImageLbl, imgConst);			
			imgConst.gridx++;
		}
		
		// Book Image
		imgPanel.add(bookImageLbl, imgConst);
		
		formConst.gridx = 0;
		formConst.gridy = 0;
		formConst.gridwidth = 2;
		containerForm.add(imgPanel, formConst);
		
		// Id Label	
		formConst.gridwidth = 1;
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		formConst.weighty = 0;
		formConst.insets = new Insets(5, 5, 5, 5);
		formConst.fill = GridBagConstraints.HORIZONTAL; 
		containerForm.add(idLbl, formConst);
		
		// Id Value Label
		formConst.weightx = 0.75;
		formConst.gridx++;
		containerForm.add(idValLbl, formConst);
		
		// Name Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(nameLbl, formConst);
		
		// Name Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(nameValLbl, formConst);
		
		// ISBN Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(isbnLbl, formConst);
		
		// ISBN Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(isbnValLbl, formConst);
		
		// Title Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(titleLbl, formConst);
		
		// Title Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(titleValLbl, formConst);
		
		// Issue Date Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(issueDateLbl, formConst);
		
		// Issue Date Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(issueDateValLbl, formConst);
		
		// Return Date Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(returnDateLbl, formConst);
		
		// Return Date Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(returnDateValLbl, formConst);
		
		// Status Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(statusLbl, formConst);
		
		// Status Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(statusValLbl, formConst);
				
		// Fine Label
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.weightx = 0.25;
		containerForm.add(fineLbl, formConst);
		
		// Fine Value Label
		formConst.gridx++;
		formConst.weightx = 0.75;
		containerForm.add(fineValLbl, formConst);
		
		// Ok Button
		formConst.gridx = 0;
		formConst.gridy++;
		formConst.ipadx = 15;
		formConst.gridwidth = 2;
		formConst.fill = GridBagConstraints.NONE;
		formConst.gridwidth = 2;
		formConst.insets = new Insets(20, 0, 0, 0);
		okBtn.addActionListener(this);
		containerForm.add(okBtn, formConst);		
		
		setLayout(new GridBagLayout());
		GridBagConstraints containerConst = new GridBagConstraints();
		containerConst.insets = new Insets(0, 50, 0, 50);
		containerConst.weightx = 1.0;
		containerConst.fill = GridBagConstraints.HORIZONTAL;
		add(containerForm, containerConst);
//		setResizable(false);
	}

	public ViewRecordFrame(AdminIssuedRecordsFrame adminIssuedFrame, int width, int height, IssuedBook issuedBook, Member member) {
		super(adminIssuedFrame);
		
		this.adminIssuedFrame = adminIssuedFrame;
		width = (int)(width / 1.125);
		height = (int)(height / 1.125);
		setSize(width, height);
		setLocationRelativeTo(adminIssuedFrame);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("resources/userImage.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image imgResize = img.getScaledInstance(height / 4, width / 4, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imgResize);
		userImageLbl.setIcon(imageIcon);
		
		try {
		    img = ImageIO.read(new File("resources/bookImage.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		imgResize = img.getScaledInstance(height / 4, width / 4, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(imgResize);
		bookImageLbl.setIcon(imageIcon);
		
		setTitle("View Record");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
		
		userImageLbl.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.3)));
		bookImageLbl.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.3)));
		
		idValLbl.setText(issuedBook.getId());
		nameValLbl.setText(member.getName());
		isbnValLbl.setText(issuedBook.getBook().getIsbn());
		titleValLbl.setText(issuedBook.getBook().getName());
		issueDateValLbl.setText(issuedBook.getIssueDate().toString());
		returnDateValLbl.setText(issuedBook.getReturnDate().toString());
		statusValLbl.setText(issuedBook.getStatus());
		fineValLbl.setText(String.valueOf(issuedBook.getFineStatus()));
	}
	
	public ViewRecordFrame(MemberIssuedRecordsFrame memberIssuedFrame, int width, int height, IssuedBook issuedBook, Member member) {
		super(memberIssuedFrame);
		
		this.memberIssuedFrame = memberIssuedFrame;
		width = (int)(width / 1.125);
		height = (int)(height / 1.125);
		setSize(width, height);
		setLocationRelativeTo(memberIssuedFrame);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("resources/bookImage.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image imgResize = img.getScaledInstance(height / 4, width / 4, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imgResize);
		bookImageLbl.setIcon(imageIcon);
		
		setTitle("View Record");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
		
		bookImageLbl.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.3)));
		
		idValLbl.setText(issuedBook.getId());
		nameValLbl.setText(member.getName());
		isbnValLbl.setText(issuedBook.getBook().getIsbn());
		titleValLbl.setText(issuedBook.getBook().getName());
		issueDateValLbl.setText(issuedBook.getIssueDate().toString());
		returnDateValLbl.setText(issuedBook.getReturnDate().toString());
		statusValLbl.setText(issuedBook.getStatus());
		fineValLbl.setText(String.valueOf(issuedBook.getFineStatus()));
	}
	
	public ViewRecordFrame() {
		
	}
	
	public void exitFrame(JFrame frame) {
		frame.setEnabled(true);
		frame.setAlwaysOnTop(true);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setAlwaysOnTop(false);
		dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(okBtn)) {
			if(adminIssuedFrame != null) {
				exitFrame(adminIssuedFrame);				
			} else if(memberIssuedFrame != null) {
				exitFrame(memberIssuedFrame);								
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
		if(adminIssuedFrame != null) {
			exitFrame(adminIssuedFrame);				
		} else if(memberIssuedFrame != null) {
			exitFrame(memberIssuedFrame);								
		}
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
