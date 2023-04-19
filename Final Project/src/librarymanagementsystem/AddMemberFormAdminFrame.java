package librarymanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class AddMemberFormAdminFrame extends JDialog implements ActionListener, WindowListener {
	private Library library;
	private AdminMemberFrame adminMemberFrame;
	
	// Add Form
	private JPanel leftFormPanel = new JPanel();
	private JLabel titleLbl = new JLabel("Add Member");
	private JLabel idLbl = new JLabel("ID");
	private JTextField idTxt = new JTextField();
	private JLabel nameLbl = new JLabel("Name");
	private JTextField nameTxt = new JTextField();
	private JLabel birthDateLbl = new JLabel("Birthdate");
	private JDateChooser birthDateChooser = new JDateChooser();
	private JLabel emailLbl = new JLabel("Email");
	private JTextField emailTxt = new JTextField();
	private JLabel phoneLbl = new JLabel("Phone Number");
	private JTextField phoneTxt = new JTextField();
	private JLabel passwordLbl = new JLabel("Password");
	private JPasswordField passwordTxt = new JPasswordField();
	private JLabel joinDateLbl = new JLabel("Join Date");
	private JTextField joinDateTxt = new JTextField();
	private JButton addBtn = new JButton("Add");
	private JButton cancelBtn = new JButton("Cancel");
	
	public void initComponent() {
		addWindowListener(this);
		
		// Left Form
		GridBagLayout leftFormLayout = new GridBagLayout();
		GridBagConstraints leftFormConst = new GridBagConstraints();
		leftFormPanel.setLayout(leftFormLayout);
		leftFormPanel.setBackground(Color.GRAY);
		
		// Title Label
		leftFormConst.gridx = 0;	
		leftFormConst.gridy = 0;
		leftFormConst.gridwidth = 2;
		leftFormConst.weightx= 1; 
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 32));
		leftFormConst.insets = new Insets(0, 0, 30, 0);
		leftFormPanel.add(titleLbl, leftFormConst);
		
		// ID Label
		leftFormConst.fill = GridBagConstraints.HORIZONTAL;
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.gridwidth = 1;
		leftFormConst.weightx = 0.2;
		leftFormConst.ipady = 10;
		leftFormConst.insets = new Insets(5, 0, 5, 0);
		leftFormPanel.add(idLbl, leftFormConst);
		
		// ID text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(idTxt, leftFormConst);
		
		// Name Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(nameLbl, leftFormConst);
		
		// Name text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(nameTxt, leftFormConst);	
		
		// BirthDate Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(birthDateLbl, leftFormConst);
		
		// BirthDate Chooser
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(birthDateChooser, leftFormConst);
	
		// Email Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(emailLbl, leftFormConst);
		
		// Email Text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(emailTxt, leftFormConst);

		// Phone Number Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(phoneLbl, leftFormConst);
		
		// Phone Number Text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(phoneTxt, leftFormConst);
		
		// Password Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(passwordLbl, leftFormConst);
		
		// Password Text field
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(passwordTxt, leftFormConst);
		
		// Join Date Label
		leftFormConst.gridx = 0;
		leftFormConst.gridy++;
		leftFormConst.weightx = 0.2;
		leftFormPanel.add(joinDateLbl, leftFormConst);
		
		// Join Date Chooser
		leftFormConst.gridx++;
		leftFormConst.weightx = 0.8;
		leftFormPanel.add(joinDateTxt, leftFormConst);
		
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
		leftFormPanel.add(buttonForm, leftFormConst);
		
		setLayout(new GridBagLayout());
		GridBagConstraints formConst = new GridBagConstraints();
		formConst.insets = new Insets(0, 100, 0, 100);
		formConst.weightx = 1.0;
		formConst.fill = GridBagConstraints.HORIZONTAL;
		add(leftFormPanel, formConst);
	}
	
	public void exitFrame() {
		resetForm();
		adminMemberFrame.setEnabled(true);
		adminMemberFrame.setAlwaysOnTop(true);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		adminMemberFrame.setAlwaysOnTop(false);
		dispose();
	}
	
	public void resetForm() {
		idTxt.setText("");
		nameTxt.setText("");
		birthDateChooser.setDate(null);
		emailTxt.setText("");
		phoneTxt.setText("");
		passwordTxt.setText("");
	}
	
	public AddMemberFormAdminFrame(Library library, AdminMemberFrame adminMemberFrame,int width, int height) {
		super(adminMemberFrame);
		
		this.library = library;
		this.adminMemberFrame= adminMemberFrame; 
		setSize((int)(width / 1.125), (int)(height / 1.125));
		setLocationRelativeTo(adminMemberFrame);
		idTxt.setText(library.generateIdMember());
		idTxt.setEditable(false);
		joinDateTxt.setText(LocalDate.now().toString());
		joinDateTxt.setEditable(false);
		
		setTitle("Add Member");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
	}
	
	public AddMemberFormAdminFrame() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(cancelBtn)) {
			exitFrame();
			
		} else if(e.getSource().equals(addBtn)) {
			String temp;
			if(birthDateChooser.getDate() == null) {
				temp = "";
			} else {
				temp = birthDateChooser.getDate().toString();
			}
			
			String result[] = library.checkNewMember(nameTxt.getText(), temp, emailTxt.getText(), phoneTxt.getText(), String.valueOf(passwordTxt.getPassword()));
			
			if(result[5].equals("")) {
				Date date1 = birthDateChooser.getDate();
				LocalDate birthDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();			
				
				adminMemberFrame.addData(idTxt.getText(), result[0], birthDate, result[2], result[3], result[4], LocalDate.parse(joinDateTxt.getText()));
				resetForm();
				exitFrame();
			} else {
				JOptionPane.showMessageDialog(this, result[5], "Error", JOptionPane.INFORMATION_MESSAGE);
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
