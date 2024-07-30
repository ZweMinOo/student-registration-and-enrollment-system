package ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;


import dao.DAO;

import pojo.classes;
import pojo.courses;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_admin_class </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_admin_class extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private dialog_newClass dialog_newC;
	private dialog_editClass dialog_editC;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_admin_class() {
		setSize(560,300);
		setLayout(null);
		setBackground(new Color(245, 255, 250));
		
		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon(pnl_admin_class.class.getResource("/images/new.png")));
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newClass();
			}
		});
		btnNew.setBounds(10, 11, 89, 23);
		add(btnNew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(pnl_admin_class.class.getResource("/images/edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editClass();
			}
		});
		btnEdit.setBounds(123, 11, 89, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(pnl_admin_class.class.getResource("/images/deletei.png")));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteClass();
			}
		});
		btnDelete.setBounds(239, 11, 96, 23);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 44, 540, 245);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Class id", "Course", "Status", "Start date", "End date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		scrollPane.setViewportView(table);
		loadData();
	}
	/**
	 * Method to load data from database add to table
	 * 
	 */
	private void loadData(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		List<classes> classList = classesDAO.getAllClasses("FROM classes");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		
		for(classes cla : classList){
			model.addRow(new Object[]{cla.getClass_id(),cla.getCourses().getTitle(),cla.getStatus(),sdf.format(cla.getStart_date()),sdf.format(cla.getEnd_date())});
		}
	}
	/**
	 * Method to create new class
	 */
	private void newClass(){
		dialog_newC = new dialog_newClass();
		dialog_newC.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int id = classesDAO.getAllClasses("FROM classes").size()+1;
		dialog_newC.tfClassId.setText(id+"");
		dialog_newC.setVisible(true);
		dialog_newC.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					courses course = dialog_newC.course;
					String status = dialog_newC.cbBStatus.getSelectedItem()+"";
					Date startDate = dialog_newC.dcStart.getDate();
					Date endDate = dialog_newC.dcEnd.getDate();
					if(checkValidate(startDate,endDate)){
						classes class1 = new classes();
						class1.setCourses(course);
						class1.setStatus(status);
						class1.setStart_date(startDate);
						class1.setEnd_date(endDate);
						
						classesDAO.insertClass(class1);
						JOptionPane.showMessageDialog(null, "New class created!");
						loadData();
						dialog_newC.dispose();
					}
					
				}catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "Please Fill all fields!");
				}	
				dialog_newC.dispose();
			}
		});
	}
	
	private int selectedId=-1;
	courses course;
	/**
	 * Method to edit class details of selected row
	 * 
	 */
	private void editClass(){
		dialog_editC = new dialog_editClass();
		dialog_editC.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if(!(table.getSelectedRow()<0)){
			selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
			classes clas = classesDAO.getAllClasses("FROM classes WHERE class_id = '" + selectedId + "'").get(0);
			
			//set textfield values
			dialog_editC.tfClassId.setText(selectedId+"");
			dialog_editC.course = clas.getCourses();
			dialog_editC.tfCourse.setText(clas.getCourses().getTitle());
			dialog_editC.cbBStatus.setSelectedItem(clas.getStatus());
			dialog_editC.dcStart.setDate(clas.getStart_date());
			dialog_editC.dcEnd.setDate(clas.getEnd_date());
			dialog_editC.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Please select a class to edit!","Incomplete",JOptionPane.ERROR_MESSAGE);
		}
		
		dialog_editC.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					course = dialog_editC.course;
					String status = dialog_editC.cbBStatus.getSelectedItem()+"";
					Date startDate = dialog_editC.dcStart.getDate();
					Date endDate = dialog_editC.dcEnd.getDate();
					if(checkValidate(startDate,endDate)){
						int res = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

		            	if (res == JOptionPane.YES_OPTION) {
							selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
							classes class1 = classesDAO.getAllClasses("FROM classes WHERE class_id = '" + selectedId + "'").get(0);						
							class1.setStatus(status);
							class1.setStart_date(startDate);
							class1.setEnd_date(endDate);
							class1.setCourses(course);
							classesDAO.updateClass(class1);
							JOptionPane.showMessageDialog(null,"Class " + class1.getClass_id() + " edited!");
							loadData();
							dialog_editC.dispose();
		            	}
					}
					
				}catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "Please Fill all fields!");
				}	
			}
		});
	}
	
	/**
	 * Method to delete classes details of selected row
	 */
	private void deleteClass(){
		
		if(!(table.getSelectedRow()<0)){
			selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
			classes clas = classesDAO.getAllClasses("FROM classes WHERE class_id = '" + selectedId + "'").get(0);
			
			int isExist = students_classesDAO.getAllStudents_Classes("FROM students_classes WHERE class_id ='" + selectedId +"'").size();
			if(isExist!=0){
				JOptionPane.showMessageDialog(null, "This classes has students informations\n Can't delete!","Incomplete",JOptionPane.ERROR_MESSAGE);
			}
			else{
				int res = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

            	if (res == JOptionPane.YES_OPTION) {
					classesDAO.deleteClass(clas);
					JOptionPane.showMessageDialog(null, "Class Id " + selectedId + " is deleted!");
					loadData();	
            	}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please select a class to delete!","Incomplete",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to check validation of fields
	 * 
	 * @param sDate parameter to check start date is valid or not
	 * @param eDate parameter to check end date is valid or not
	 * @return true for valid and false for invalid  
	 */
	private boolean checkValidate(java.util.Date sDate,java.util.Date eDate){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
	    Date tDate = new Date();  
		if(sdf.format(tDate).compareTo((sdf.format(sDate)))>0){
			JOptionPane.showMessageDialog(null, "Start date must be today date or after today date","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(sdf.format(sDate).compareTo((sdf.format(eDate)))>0){
			JOptionPane.showMessageDialog(null, "End date must be after start date","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}