package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import pojo.users;
import dao.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
/**
 * <h1> dialog_login </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@SuppressWarnings("serial")
public class dialog_login extends JDialog implements DAO{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton okButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_login dialog = new dialog_login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_login.class.getResource("/images/logo.png")));
		setTitle("Login");
		setBounds(100, 100, 383, 303);
		setLocationRelativeTo(null);

		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(245, 255, 250));
		JLabel lblInetCollege = new JLabel("iNet College");
		lblInetCollege.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInetCollege.setBounds(141, 100, 104, 27);
		contentPanel.add(lblInetCollege);
		
		JLabel lblNewLabel = new JLabel("Student Registration and Enrollment System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 125, 335, 27);
		contentPanel.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(48, 170, 104, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(48, 212, 95, 14);
		contentPanel.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfUsername.setBounds(176, 167, 161, 20);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		pfPassword.setBounds(176, 210, 161, 20);
		contentPanel.add(pfPassword);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(dialog_login.class.getResource("/images/iNet-College-Seal 1.gif")));
		lblNewLabel_1.setBounds(146, 11, 83, 79);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 8, 8));
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tfUsername.setText("");
						pfPassword.setText("");
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						checkLogin();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfUsername, pfPassword, okButton}));
	}
	
	/**
	 * Method to create default user 
	 * 
	 */
	private void createDefault(){
			if(usersDAO.getAllUsers("FROM users").size()==0){
				//Creating default user
				users user = new users();
					
				user.setUsername("Admin");
				user.setPassword("pass1234");
				usersDAO.insertUser(user);
					
				user.setUsername("Receptionist");
				user.setPassword("pass1234");
				usersDAO.insertUser(user);
			}
	}

	/**
	 * Method to validate login
	 * 
	 */
	private void checkLogin(){
		createDefault();
		String user = tfUsername.getText();
		@SuppressWarnings("deprecation")
		String pass = pfPassword.getText();
		
		List<users> usersList = usersDAO.getAllUsers("FROM users WHERE username = '" + user + "' and  password = '" + pass +"'");
		
		if(usersList.size()==0){
			JOptionPane.showMessageDialog(null, "Login failed! Try again");
		}
		else {
			this.dispose();
			if(user.equals("Admin")){
				String [] options = {"Admin","Receptionist"};
				int res = JOptionPane.showOptionDialog(null, "Choose page to continue", "Choose",0,JOptionPane.QUESTION_MESSAGE,null, options,null);

            	if (res == 0) {
            		ui_admin admin = new ui_admin();
    				admin.setVisible(true);
            	}
            	else{
            		ui_rep rep = new ui_rep();
    				rep.setVisible(true);
            	}
			}else{
				ui_rep rep = new ui_rep();
				rep.setVisible(true);
			}
			JOptionPane.showMessageDialog(null, "Welcome to iNet College\nStudent Registration and Enrollment System!");
		}
	}
}