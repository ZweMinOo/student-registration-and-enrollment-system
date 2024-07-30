package ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import dao.DAO;

import pojo.classes;
import pojo.payments;
import pojo.students;
import pojo.students_classes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.Color;
/**
 * <h1> pnl_rep_enrollment </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_rep_enrollment extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfStdId;
	private JTextField tfTotalFee;
	private JTextField tfFeeToPay;
	private JTextField tfStdId1;
	private JTextField tfTotalFee1;
	private JTextField tfFeePaid;
	private JTextField tfFeeToPay1;
	private JTextField tfClass;
	private JTextField tfClass1;
	private dialog_chooseClass  dialog_cClass;
	private classes class1;
	private JRadioButton rdbtnFull,rdbtnInstallment;
	private JDateChooser dcPayDate, dcPayDate1;
	private payments payment;
	/**
	 * Create the panel.
	 */
	public pnl_rep_enrollment() {
		setSize(560,300);
		setLayout(new GridLayout(1, 2, 3, 3));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBorder(new TitledBorder(null, "First Enrollment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student id");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStudentId.setBounds(10, 29, 85, 14);
		panel.add(lblStudentId);
		
		tfStdId = new JTextField();
		tfStdId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfStdId.setBounds(105, 26, 86, 20);
		panel.add(tfStdId);
		tfStdId.setColumns(10);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClass.setBounds(10, 64, 62, 14);
		panel.add(lblClass);
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dialog_cClass = new dialog_chooseClass();
					dialog_cClass.setVisible(true);
					dialog_cClass.btnChoose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
						try{
							class1 = dialog_cClass.classes;
								
							int stdId = Integer.parseInt(tfStdId.getText());
							int isExist = students_classesDAO.getAllStudents_Classes("FROM students_classes WHERE student_id ='"+stdId+"' AND class_id = '"+class1.getClass_id() +"'").size();
							if(isExist==1){
							JOptionPane.showMessageDialog(null,"Student Id "+ stdId + " already registered at class id " + class1.getClass_id(),"Invalid",JOptionPane.ERROR_MESSAGE);
						}else{
							tfClass.setText(class1.getClass_id()+","+class1.getCourses().getTitle());
							tfTotalFee.setText(class1.getCourses().getFee()+"");
							tfFeeToPay.setText((class1.getCourses().getFee())+"");
							dialog_cClass.dispose();
						}
						}catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(null,"Student Id must be number","Invalid Id",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
		});
		btnChoose.setBounds(105, 83, 111, 23);
		panel.add(btnChoose);
		
		JLabel lblTotalFee = new JLabel("Total Fee");
		lblTotalFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalFee.setBounds(10, 120, 85, 14);
		panel.add(lblTotalFee);
		
		tfTotalFee = new JTextField();
		tfTotalFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfTotalFee.setEditable(false);
		tfTotalFee.setBounds(105, 117, 111, 20);
		panel.add(tfTotalFee);
		tfTotalFee.setColumns(10);
		
		JLabel lblMmk = new JLabel("MMK");
		lblMmk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMmk.setBounds(226, 120, 46, 14);
		panel.add(lblMmk);
		
		JLabel lblPaymentType = new JLabel("Payment Type");
		lblPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPaymentType.setBounds(10, 148, 85, 14);
		panel.add(lblPaymentType);
		
		ButtonGroup bGroup = new ButtonGroup();
		
		rdbtnFull = new JRadioButton("Full");
		rdbtnFull.setBackground(new Color(245, 255, 250));
		rdbtnFull.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFull.setSelected(true);
		rdbtnFull.setBounds(105, 144, 56, 23);
		rdbtnFull.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(class1!=null)
				tfFeeToPay.setText((class1.getCourses().getFee())+"");
			}
		});
		bGroup.add(rdbtnFull);
		panel.add(rdbtnFull);
		
		rdbtnInstallment = new JRadioButton("Installment");
		rdbtnInstallment.setBackground(new Color(245, 255, 250));
		rdbtnInstallment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnInstallment.setBounds(163, 144, 109, 23);
		rdbtnInstallment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(class1!=null)
				tfFeeToPay.setText((class1.getCourses().getFee()/2)+"");
			}
		});
		bGroup.add(rdbtnInstallment);
		panel.add(rdbtnInstallment);
		
		JLabel lblFeeToPaid = new JLabel("Fee to paid now");
		lblFeeToPaid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFeeToPaid.setBounds(10, 212, 94, 14);
		panel.add(lblFeeToPaid);
		
		tfFeeToPay = new JTextField();
		tfFeeToPay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfFeeToPay.setEditable(false);
		tfFeeToPay.setBounds(105, 209, 111, 20);
		panel.add(tfFeeToPay);
		tfFeeToPay.setColumns(10);
		
		JLabel lblMmk_1 = new JLabel("MMK");
		lblMmk_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMmk_1.setBounds(226, 212, 46, 14);
		panel.add(lblMmk_1);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enrollFirst();
			}
		});
		btnEnroll.setBounds(105, 240, 111, 23);
		panel.add(btnEnroll);
		
		tfClass = new JTextField();
		tfClass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfClass.setEditable(false);
		tfClass.setBounds(105, 61, 111, 20);
		panel.add(tfClass);
		tfClass.setColumns(10);
		
		JLabel lblPayDate = new JLabel("Pay date");
		lblPayDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPayDate.setBounds(10, 180, 77, 14);
		panel.add(lblPayDate);
		
		dcPayDate = new JDateChooser();
		dcPayDate.setBounds(105, 178, 111, 20);
		panel.add(dcPayDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 255, 250));
		panel_1.setBorder(new TitledBorder(null, "Second Enrollment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStudentId_1 = new JLabel("Student id");
		lblStudentId_1.setBounds(10, 30, 70, 14);
		panel_1.add(lblStudentId_1);
		
		tfStdId1 = new JTextField();
		tfStdId1.setBounds(114, 27, 86, 20);
		panel_1.add(tfStdId1);
		tfStdId1.setColumns(10);
		
		JLabel lblClass_1 = new JLabel("Class");
		lblClass_1.setBounds(10, 67, 46, 14);
		panel_1.add(lblClass_1);
		
		JButton btnChoose_1 = new JButton("Choose");
		btnChoose_1.setBounds(114, 87, 110, 23);
		btnChoose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog_cClass = new dialog_chooseClass();
				dialog_cClass.setVisible(true);
				dialog_cClass.btnChoose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
							int stdId = Integer.parseInt(tfStdId1.getText());
						
							class1 = dialog_cClass.classes;
							int isExist = paymentsDAO.getAllPayments("FROM payments WHERE student_id = '" + stdId + "' AND class_id ='" + class1.getClass_id() +"' AND remaining_amount != 0").size();
							if(isExist==0){
								
								int isFullPaid = paymentsDAO.getAllPayments("FROM payments WHERE student_id = '" + stdId + "' AND class_id ='" + class1.getClass_id() +"' AND remaining_amount = 0").size();
								if(isFullPaid==0)
									JOptionPane.showMessageDialog(null,"Student Id " + stdId + " was not registarted with class " + class1.getClass_id(),"Invalid",JOptionPane.ERROR_MESSAGE);
								else
									JOptionPane.showMessageDialog(null,"Student Id " + stdId + " was fully paid at class " + class1.getClass_id());
							}else{
								payment = paymentsDAO.getAllPayments("FROM payments WHERE student_id = '" + stdId + "' AND class_id ='" + class1.getClass_id() +"' AND remaining_amount != 0").get(0);
								tfClass1.setText(class1.getClass_id()+","+class1.getCourses().getTitle());
								tfTotalFee1.setText(payment.getPaid_amount()*2+"");
								tfFeePaid.setText(payment.getPaid_amount()+"");
								tfFeeToPay1.setText(payment.getRemaining_amount()+"");
								dialog_cClass.dispose();
							}
						}catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(null,"Student Id must be number","Invalid Id",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
		});
		panel_1.add(btnChoose_1);
		
		JLabel lblTotalFee_1 = new JLabel("Total Fee");
		lblTotalFee_1.setBounds(10, 124, 58, 14);
		panel_1.add(lblTotalFee_1);
		
		JLabel lblFeePaid = new JLabel("Fee paid");
		lblFeePaid.setBounds(10, 160, 70, 14);
		panel_1.add(lblFeePaid);
		
		JLabel lblFeeToPaid_1 = new JLabel("Fee to paid now");
		lblFeeToPaid_1.setBounds(10, 212, 94, 14);
		panel_1.add(lblFeeToPaid_1);
		
		tfTotalFee1 = new JTextField();
		tfTotalFee1.setEditable(false);
		tfTotalFee1.setBounds(114, 121, 110, 20);
		panel_1.add(tfTotalFee1);
		tfTotalFee1.setColumns(10);
		
		tfFeePaid = new JTextField();
		tfFeePaid.setEditable(false);
		tfFeePaid.setBounds(114, 157, 110, 20);
		panel_1.add(tfFeePaid);
		tfFeePaid.setColumns(10);
		
		tfFeeToPay1 = new JTextField();
		tfFeeToPay1.setEditable(false);
		tfFeeToPay1.setBounds(114, 209, 110, 20);
		panel_1.add(tfFeeToPay1);
		tfFeeToPay1.setColumns(10);
		
		JButton btnEnroll_1 = new JButton("Enroll");
		btnEnroll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enrollSecond();
			}
		});
		btnEnroll_1.setBounds(114, 240, 110, 23);
		panel_1.add(btnEnroll_1);
		
		tfClass1 = new JTextField();
		tfClass1.setEditable(false);
		tfClass1.setBounds(114, 64, 110, 20);
		panel_1.add(tfClass1);
		tfClass1.setColumns(10);
		
		JLabel lblMmk_2 = new JLabel("MMK");
		lblMmk_2.setBounds(234, 160, 46, 14);
		panel_1.add(lblMmk_2);
		
		JLabel lblMmk_3 = new JLabel("MMK");
		lblMmk_3.setBounds(234, 124, 31, 14);
		panel_1.add(lblMmk_3);
		
		JLabel lblMmk_4 = new JLabel("MMK");
		lblMmk_4.setBounds(234, 212, 46, 14);
		panel_1.add(lblMmk_4);
		
		dcPayDate1 = new JDateChooser();
		dcPayDate1.setBounds(114, 184, 110, 20);
		panel_1.add(dcPayDate1);
		
		JLabel lblPayDate_1 = new JLabel("Pay date");
		lblPayDate_1.setBounds(10, 187, 70, 14);
		panel_1.add(lblPayDate_1);
	}
	
	students student=new students();
	/**
	 * Method to process the first enrollment
	 * 
	 */
	private void enrollFirst(){
		try{
			int stdId = Integer.parseInt(tfStdId.getText());
			student = studentsDAO.getAllStudents("FROM students WHERE student_id = '" + stdId +"'").get(0);
			if(student.getStudent_id()==0){
				JOptionPane.showMessageDialog(null,"Student Id " + stdId + " not found!","Invalid Id",JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(checkValidateFirst()){
					double pAmt=0,rAmt = 0;
					if(rdbtnFull.isSelected()){
						pAmt = class1.getCourses().getFee();
					}else {
						if(class1.getCourses().getFee()!=0)
							pAmt = class1.getCourses().getFee()/2;
							rAmt = pAmt;
					}
					java.util.Date pDate = dcPayDate.getDate();
					//insert into student class table
					students_classes std_clas = new students_classes();
					std_clas.setStudents(student);
					std_clas.setClasses(class1);
					std_clas.setReg_date(pDate);
					students_classesDAO.insertStudents_Classes(std_clas);
					
					//insert into payment table
					payments payment = new payments();
					payment.setClasses(class1);
					payment.setStudents(student);
					payment.setPaid_amount(pAmt);
					payment.setRemaining_amount(rAmt);
					payment.setPayment_date(pDate);
					paymentsDAO.insertPayment(payment);
					
					JOptionPane.showMessageDialog(null,"First enrollment completed!");
					clearFirstField();
				}
			}
		}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null,"Student Id must be number","Invalid Id",JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Method to reset the feild in fisrt enrollment
	 * 
	 */
	private void clearFirstField(){
		tfStdId.setText("");
		tfClass.setText("");
		tfTotalFee.setText("");
		dcPayDate.setDate(null);
		tfFeeToPay.setText("");
	}
	/**
	 * Method to process second enrollment
	 * 
	 */
	private void enrollSecond(){
		if(payment==null){
			JOptionPane.showMessageDialog(null,"Plese select a valid class for added student id","Invalid",JOptionPane.ERROR_MESSAGE);
		}else {
			try{
				if(checkValidateSecond()){
					//int stdId = Integer.parseInt(tfStdId1.getText());
					student = payment.getStudents();
					java.util.Date pDate = dcPayDate1.getDate();
					
					payment.setRemaining_amount(0);
					payment.setPayment_date(pDate);
					paymentsDAO.insertPayment(payment);
						
					JOptionPane.showMessageDialog(null,"Second enrollment completed!");
					clearSecondField();
				}
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null,"Student Id must be number","Invalid Id",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * Method to reset the field in second enrollment
	 * 
	 */
	private void clearSecondField(){
		tfStdId1.setText("");
		tfClass1.setText("");
		tfTotalFee1.setText("");
		tfFeePaid.setText("");
		dcPayDate1.setDate(null);
		tfFeeToPay1.setText("");
	}
	
	/**
	 * Method for validation first enrollment
	 * 
	 * @return true for valid and false for invalid
	 */
	private boolean checkValidateFirst(){
		if(tfClass.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Please select a class","Incomplete",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(tfTotalFee.getText().equals("")){
			return false;
		}
		else if(tfFeeToPay.getText().equals("")){
			return false;
		}
		else if(dcPayDate.getDate() == null) {
			JOptionPane.showMessageDialog(null,"Please choose payment date","Incomplete",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * Method for validation second enrollment 
	 * 
	 * @return true for valid and false of invalid
	 */
	private boolean checkValidateSecond(){
		if(tfClass1.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Please select a class","Incomplete",JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(tfTotalFee1.getText().equals("")){
			return false;
		}
		else if(tfFeePaid.getText().equals("")){
			return false;
		}
		else if(tfFeeToPay1.getText().equals("")){
			return false;
		}
		else if(dcPayDate1.getDate() == null){
			JOptionPane.showMessageDialog(null,"Please choose payment date","Incomplete",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else{
			return true;
		}
	}
}