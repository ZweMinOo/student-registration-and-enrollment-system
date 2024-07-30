package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

import pojo.courses;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * <h1> dialog_newClass </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class dialog_newClass extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextField tfClassId;
	courses course = new courses();
	JComboBox<String> cbBStatus;
	JDateChooser dcStart,dcEnd;
	private dialog_chooseCourse dialog_cCourse;
	JButton btnSave;
	private JTextField tfCourse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_newClass dialog = new dialog_newClass();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_newClass() {
		setTitle("New Class");
		setResizable(false);
		setBounds(100, 100, 378, 209);
		getContentPane().setBackground(new Color(245, 255, 250));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblClassId = new JLabel("Class Id");
			lblClassId.setBounds(10, 11, 46, 14);
			contentPanel.add(lblClassId);
		}
		{
			tfClassId = new JTextField();
			tfClassId.setEditable(false);
			tfClassId.setBounds(69, 8, 86, 20);
			contentPanel.add(tfClassId);
			tfClassId.setColumns(10);
		}
		{
			JLabel lblCourse = new JLabel("Course");
			lblCourse.setBounds(10, 52, 46, 14);
			contentPanel.add(lblCourse);
		}
		{
			JLabel lblStatus = new JLabel("Status");
			lblStatus.setBounds(10, 111, 46, 14);
			contentPanel.add(lblStatus);
		}
		{
			JButton btnChoose = new JButton("Choose..");
			btnChoose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final dialog_chooseCourse dialog_cCourse = new dialog_chooseCourse();
					dialog_cCourse.setVisible(true);
					dialog_cCourse.btnChoose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							course = dialog_cCourse.course;
							if(course==null){
								JOptionPane.showMessageDialog(null, "Please select a course to choose");
							}else{
								tfCourse.setText(course.getTitle());
								dialog_cCourse.dispose();
							}
						}
					});
				}
			});
			btnChoose.setBounds(69, 74, 86, 23);
			contentPanel.add(btnChoose);
		}
		
		cbBStatus = new JComboBox<String>();
		cbBStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Active", "Delete", "Closed", "On Schedule"}));
		cbBStatus.setBounds(69, 108, 100, 20);
		contentPanel.add(cbBStatus);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(206, 52, 58, 14);
		contentPanel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(206, 111, 58, 14);
		contentPanel.add(lblEndDate);
		
		dcStart = new JDateChooser();
		dcStart.setBounds(274, 49, 91, 20);
		contentPanel.add(dcStart);
		
		dcEnd = new JDateChooser();
		dcEnd.setBounds(274, 105, 91, 20);
		contentPanel.add(dcEnd);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(274, 148, 89, 23);
		contentPanel.add(btnSave);
		
		tfCourse = new JTextField();
		tfCourse.setEditable(false);
		tfCourse.setBounds(69, 49, 100, 20);
		contentPanel.add(tfCourse);
		tfCourse.setColumns(10);
	}
}
