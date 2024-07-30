package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;

import pojo.users;

import dao.DAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * <h1> dialog_changePassword </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class dialog_changePassword extends JDialog implements DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField pfOldPass;
	private JPasswordField pfNewPass;
	private JPasswordField pfConfirmPass;
	private JTextField tfUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_changePassword dialog = new dialog_changePassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_changePassword() {
		setResizable(false);
		setTitle("Change Password");
		setBounds(100, 100, 307, 189);
		getContentPane().setBackground(new Color(245, 255, 250));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(4, 2, 5, 5));
			{
				JLabel lblUsername = new JLabel("Username");
				lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblUsername);
			}
			{
				tfUser = new JTextField();
				tfUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(tfUser);
				tfUser.setColumns(10);
			}
			{
				JLabel lblOldPassword = new JLabel("Old password");
				lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblOldPassword);
			}
			{
				pfOldPass = new JPasswordField();
				pfOldPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(pfOldPass);
				pfOldPass.setColumns(10);
			}
			{
				JLabel lblNewPassword = new JLabel("New password");
				lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblNewPassword);
			}
			{
				pfNewPass = new JPasswordField();
				pfNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(pfNewPass);
				pfNewPass.setColumns(10);
			}
			{
				JLabel lblConfirmPassword = new JLabel("Confirm password");
				lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblConfirmPassword);
			}
			{
				pfConfirmPass = new JPasswordField();
				pfConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(pfConfirmPass);
				pfConfirmPass.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnChange = new JButton("Change");
				btnChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						changePassword();
					}
				});
				btnChange.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnChange.setBackground(Color.GREEN);
				btnChange.setForeground(Color.BLACK);
				btnChange.setActionCommand("change");
				buttonPane.add(btnChange);
				getRootPane().setDefaultButton(btnChange);
			}
		}
	}

	/**
	 * Method to change password of users table in database
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void changePassword(){
		
		/* Written by Zwe Min Oo HM25705 */
		/* Code Start */
		// get username
		// get passwords
		// check old password and username
		//valid
			//check password length is 6 ?
			//valid
				//check new password and confirm password
				//valid
					//encode with md5
					//save
					//show valid message
					//dispose
				//invalid
					//show invalid message
			//invalid
				//show invalid message
		//invalid
			//clear password fields
			//show invalid message
		/* Code End */
		
		String user = tfUser.getText();
		String oldP = pfOldPass.getText(), 
			   newP = pfNewPass.getText(), 
			   confirmP = pfConfirmPass.getText();
		
		String q = "FROM users WHERE username = '" + user + "' AND password = '" + oldP +"'";
		List<users> userList = usersDAO.getAllUsers(q);
		if(userList.size()!=0){
			if(newP.toCharArray().length >= 6){
				if(newP.equals(confirmP)){
					userList.get(0).setPassword(newP);
					usersDAO.updateUser(userList.get(0));
					JOptionPane.showMessageDialog(null, "Password change successfully!","Completed",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {				
					JOptionPane.showMessageDialog(null, "Confirm password must be same with new password!","Invalid",JOptionPane.ERROR_MESSAGE);
					pfConfirmPass.setText("");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Password length must be at least 6 words!","Invalid",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Username or Password Incorrect!","Invalid",JOptionPane.ERROR_MESSAGE);
		}
	}
}