package ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
/**
 * <h1> dialog_editCourse </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class dialog_editCourse extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tfCourseId;
	JTextField tfTitle;
	JTextField tfFee;
	JTextField tfType;
	JTextField tfDuration;
	JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_editCourse dialog = new dialog_editCourse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_editCourse() {
		setTitle("Edit Course");
		setBounds(100, 100, 408, 215);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(245, 255, 250));
		JLabel lblCourseId = new JLabel("Course Id");
		lblCourseId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourseId.setBounds(10, 14, 54, 14);
		getContentPane().add(lblCourseId);
		
		tfCourseId = new JTextField();
		tfCourseId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCourseId.setEditable(false);
		tfCourseId.setBounds(74, 11, 86, 20);
		getContentPane().add(tfCourseId);
		tfCourseId.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(10, 57, 46, 14);
		getContentPane().add(lblTitle);
		
		JLabel lblFee = new JLabel("Fee(MMK)");
		lblFee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFee.setBounds(10, 96, 66, 14);
		getContentPane().add(lblFee);
		
		tfTitle = new JTextField();
		tfTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTitle.setBounds(74, 54, 98, 20);
		getContentPane().add(tfTitle);
		tfTitle.setColumns(10);
		
		tfFee = new JTextField();
		tfFee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfFee.setText("");
		tfFee.setBounds(74, 93, 98, 20);
		getContentPane().add(tfFee);
		tfFee.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(182, 57, 46, 14);
		getContentPane().add(lblType);
		
		tfType = new JTextField();
		tfType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfType.setBounds(286, 54, 96, 20);
		getContentPane().add(tfType);
		tfType.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration(Hour)");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDuration.setBounds(182, 96, 94, 14);
		getContentPane().add(lblDuration);
		
		tfDuration = new JTextField();
		tfDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfDuration.setBounds(286, 93, 96, 20);
		getContentPane().add(tfDuration);
		tfDuration.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(286, 132, 96, 23);
		getContentPane().add(btnSave);
	}
}
