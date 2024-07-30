package ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import dao.DAO;
import javax.swing.JButton;
import pojo.students;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <h1> pnl_rep_registration </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_rep_registration extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfNRC;
	private JDateChooser dcDob;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JTextField tfStdId;

	/**
	 * Create the panel.
	 */
	public pnl_rep_registration() {
		setSize(560,300);
		setLayout(null);
		setBackground(new Color(245, 255, 250));
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(174, 11, 61, 14);
		add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(174, 47, 46, 14);
		add(lblName);
		
		JLabel lblNrc = new JLabel("NRC");
		lblNrc.setBounds(174, 87, 46, 14);
		add(lblNrc);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(174, 125, 71, 14);
		add(lblDateOfBirth);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(174, 165, 61, 14);
		add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(174, 200, 61, 14);
		add(lblAddress);
		
		tfName = new JTextField();
		tfName.setBounds(266, 44, 115, 20);
		add(tfName);
		tfName.setColumns(10);
		
		tfNRC = new JTextField();
		tfNRC.setBounds(266, 84, 115, 20);
		add(tfNRC);
		tfNRC.setColumns(10);
		
		dcDob = new JDateChooser();
		dcDob.setBounds(266, 125, 115, 20);
		add(dcDob);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(266, 162, 115, 20);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(266, 197, 115, 20);
		add(tfAddress);
		tfAddress.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newStudent();
			}
		});
		btnRegister.setBounds(266, 239, 115, 23);
		add(btnRegister);
		
		tfStdId = new JTextField();
		tfStdId.setEditable(false);
		tfStdId.setBounds(267, 8, 86, 20);
		add(tfStdId);
		tfStdId.setColumns(10);
		setId();
	}
	/**
	 * Method to create new student
	 * 
	 */
	private void newStudent(){
		String name = tfName.getText();
		String nrc = tfNRC.getText();
		java.util.Date birthDate = dcDob.getDate();
		String phone = tfPhone.getText();
		String address = tfAddress.getText();
		
		if(checkValidate(name,nrc,phone,birthDate)){
			students std = new students();
			std.setName(name);
			std.setNrc(nrc);
			std.setDate_of_birth(birthDate);
			std.setPhone(phone);
			std.setAddress(address);
			
			studentsDAO.insertStudent(std);
			JOptionPane.showMessageDialog(null, "New student registrated!");
			clearFields();
		}
	}
	/**
	 * Method to reset the fields
	 * 
	 */
	private void clearFields(){
		tfName.setText("");
		tfNRC.setText("");
		dcDob.cleanup();
		tfPhone.setText("");
		tfAddress.setText("");
	}
	/**
	 * Method for validation fields
	 * 
	 * @param name parameter to check name validation
	 * @param nrc parameter to check nrc validation
	 * @param phone parameter to check phone validation
	 * @param rDate parameter to check registration date validation
	 * @return true for valid and false for invalid
	 */
	private boolean checkValidate(String name,String nrc,String phone,java.util.Date rDate){
		if(!validate.isName(name)){
			JOptionPane.showMessageDialog(null, "Name must start with capital letter\n E.g Abc Abc!","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!validate.isPhone(phone)){
			JOptionPane.showMessageDialog(null, "Wrong phone number.\nPlease fill in correct format.\nE.g 09-123456789","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Check brithDate
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
	    Date tDate = new Date(); 
		if(("1990".compareTo((sdf.format(rDate)))>0)||
				sdf.format(tDate).compareTo((sdf.format(rDate)))<0){
			JOptionPane.showMessageDialog(null, "Date of birth date must be greater than 1990 or less than "+sdf.format(tDate),"Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Method to set id in tfId
	 * 
	 */
	private void setId(){
		int id =studentsDAO.getAllStudents("FROM students").size()+1;
		tfStdId.setText(id+"");
	}
}
