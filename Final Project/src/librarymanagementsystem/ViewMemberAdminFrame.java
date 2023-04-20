package librarymanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
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
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewMemberAdminFrame extends JDialog implements ActionListener, WindowListener {
	private AdminMemberFrame adminMemberFrame;
	
	// JDialog Form
	private JPanel containerForm = new JPanel();
	private JLabel imageLbl = new JLabel();
	private JLabel idLbl = new JLabel("ID");
	private JLabel idValLbl = new JLabel();
	private JLabel nameLbl = new JLabel("Name");
	private JLabel nameValLbl = new JLabel();
	private JLabel birthDateLbl = new JLabel("Birthdate");
	private JLabel birthDateValLbl = new JLabel();
	private JLabel emailLbl = new JLabel("Email");
	private JLabel emailValLbl = new JLabel();
	private JLabel phoneNumLbl = new JLabel("Phonenum");
	private JLabel phoneNumValLbl = new JLabel();
	private JLabel joinDateLbL = new JLabel("Join Date");
	private JLabel joinDateValLbl = new JLabel();
	private JButton okBtn = new JButton("OK");

	
	public void initComponent() {	
		addWindowListener(this);	
		
		// JDialog Form
		GridBagLayout containerLayout = new GridBagLayout();
		GridBagConstraints containerConst = new GridBagConstraints();
		containerForm.setLayout(containerLayout);
		
		// Member Image
		containerConst.gridx = 0;	
		containerConst.gridy = 0;
		containerConst.fill = GridBagConstraints.VERTICAL;
//		containerConst.anchor = GridBagConstraints.CENTER;
		containerConst.gridwidth = 2;
		containerConst.weightx= 1;
		containerConst.weighty= 1;
		containerForm.add(imageLbl, containerConst);
		
		// Id Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.gridwidth = 1;
		containerConst.weightx = 0.25;
		containerConst.weighty = 0;
		containerConst.insets = new Insets(5, 5, 5, 5);
		containerConst.fill = GridBagConstraints.HORIZONTAL; 
		containerForm.add(idLbl, containerConst);
		
		// Id Value Label
		containerConst.weightx = 0.75;
		containerConst.gridx++;
		containerForm.add(idValLbl, containerConst);
		
		// Name Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.25;
		containerForm.add(nameLbl, containerConst);
		
		// Name Value Label
		containerConst.gridx++;
		containerConst.weightx = 0.75;
		containerForm.add(nameValLbl, containerConst);
		
		// BirthDate Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.25;
		containerForm.add(birthDateLbl, containerConst);
		
		// BirthDate Value Label
		containerConst.gridx++;
		containerConst.weightx = 0.75;
		containerForm.add(birthDateValLbl, containerConst);
		
		// Email Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.25;
		containerForm.add(emailLbl, containerConst);
		
		// Email Value Label
		containerConst.gridx++;
		containerConst.weightx = 0.75;
		containerForm.add(emailValLbl, containerConst);
		
		// Phone number Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.25;
		containerForm.add(phoneNumLbl, containerConst);
		
		// Phone Number Value Label
		containerConst.gridx++;
		containerConst.weightx = 0.75;
		containerForm.add(phoneNumValLbl, containerConst);
		
		// Join Date Label
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.weightx = 0.25;
		containerForm.add(joinDateLbL, containerConst);
		
		// Join Date Value Label
		containerConst.gridx++;
		containerConst.weightx = 0.75;
		containerForm.add(joinDateValLbl, containerConst);
		
		// Ok Button
		containerConst.gridx = 0;
		containerConst.gridy++;
		containerConst.ipadx = 15;
		containerConst.gridwidth = 2;
		containerConst.fill = GridBagConstraints.NONE;
		containerConst.gridwidth = 2;
		containerConst.insets = new Insets(20, 0, 0, 0);
		okBtn.addActionListener(this);
		containerForm.add(okBtn, containerConst);		
		
		setLayout(new GridBagLayout());
		GridBagConstraints formConst = new GridBagConstraints();
		formConst.insets = new Insets(0, 50, 0, 50);
		formConst.weightx = 1.0;
		formConst.fill = GridBagConstraints.HORIZONTAL;
		add(containerForm, formConst);
//		setResizable(false);
	}

	public ViewMemberAdminFrame(AdminMemberFrame adminMemberFrame, int width, int height, Member member) {
		super(adminMemberFrame);
		
		this.adminMemberFrame = adminMemberFrame;
		width = (int)(width / 1.125);
		height = (int)(height / 1.125);
		setSize(width, height);
		setLocationRelativeTo(adminMemberFrame);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("resources/userImage.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image imgResize = img.getScaledInstance(height / 4, width / 4, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imgResize);
		imageLbl.setIcon(imageIcon);
		
		setTitle("View Member");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		setVisible(true);
		
		imageLbl.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.3)));
		
		idValLbl.setText(member.getId());
		nameValLbl.setText(member.getName());
		birthDateValLbl.setText(member.getBirthDate().toString());
		emailValLbl.setText(member.getEmail());
		phoneNumValLbl.setText(member.getPhoneNum());
		joinDateValLbl.setText(member.getJoinDate().toString());
	}
	
	public ViewMemberAdminFrame() {
		
	}
	
	public void exitFrame() {
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
