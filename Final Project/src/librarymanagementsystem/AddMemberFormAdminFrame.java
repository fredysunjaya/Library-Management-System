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
	private JPanel containerPanel = new JPanel();
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
		
		// Container Form
		GridBagLayout containerLayout = new GridBagLayout();
		GridBagConstraints containerConst = new GridBagConstraints();
		containerPanel.setLayout(containerLayout);
		containerPanel.setBackground(Color.GRAY);
		
		// Title Label
		containerConst.gridx = 0;	
		containerConst.gridy = 0;
		containerConst.gridwidth = 2;
		containerConst.weightx= 1; 
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 32));
		containerConst.insets = new Insets(0, 0, 30, 0);
		containerPanel.add(titleLbl, containerConst);
		
		// ID Label
		containerConst.fill = GridBagConstraints.HORIZONTAL;
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.gridwidth = 1;
		containerConst.weightx = 0.2;
		containerConst.ipady = 10;
		containerConst.insets = new Insets(5, 0, 5, 0);
		containerPanel.add(idLbl, containerConst);
		
		// ID text field
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(idTxt, containerConst);
		
		// Name Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(nameLbl, containerConst);
		
		// Name text field
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(nameTxt, containerConst);	
		
		// BirthDate Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(birthDateLbl, containerConst);
		
		// BirthDate Chooser
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(birthDateChooser, containerConst);
	
		// Email Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(emailLbl, containerConst);
		
		// Email Text field
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(emailTxt, containerConst);

		// Phone Number Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(phoneLbl, containerConst);
		
		// Phone Number Text field
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(phoneTxt, containerConst);
		
		// Password Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(passwordLbl, containerConst);
		
		// Password Text field
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(passwordTxt, containerConst);
		
		// Join Date Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.2;
		containerPanel.add(joinDateLbl, containerConst);
		
		// Join Date Chooser
		containerConst.gridx++;
		containerConst.weightx = 0.8;
		containerPanel.add(joinDateTxt, containerConst);
		
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
		
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.gridwidth = 2;
		containerPanel.add(buttonForm, containerConst);
		
		setLayout(new GridBagLayout());
		GridBagConstraints formConst = new GridBagConstraints();
		formConst.insets = new Insets(0, 100, 0, 100);
		formConst.weightx = 1.0;
		formConst.fill = GridBagConstraints.HORIZONTAL;
		add(containerPanel, formConst);
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
		idTxt.setEnabled(false);
		idTxt.setDisabledTextColor(Color.RED);
		joinDateTxt.setText(LocalDate.now().toString());
		joinDateTxt.setEnabled(false);
		joinDateTxt.setDisabledTextColor(Color.RED);
		
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
