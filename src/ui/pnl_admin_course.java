package ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.courses;

import dao.DAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_admin_course </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_admin_course extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private dialog_newCourse dialog_newC;
	private dialog_editCourse dialog_editC;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_admin_course() {
		setSize(560,300);
		setLayout(null);
		setBackground(new Color(245, 255, 250));
		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon(pnl_admin_course.class.getResource("/images/new.png")));
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newCourse();
			}
		});
		btnNew.setBounds(10, 11, 89, 23);
		add(btnNew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(pnl_admin_course.class.getResource("/images/edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editCourse();
			}
		});
		btnEdit.setBounds(123, 11, 89, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(pnl_admin_course.class.getResource("/images/deletei.png")));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCourse();
			}
		});
		btnDelete.setBounds(239, 11, 100, 23);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 540, 245);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course id", "Title", "Fee", "Type", "Duration(Hour)"
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
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
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
		List<courses> courseList = coursesDAO.getAllCourses("FROM courses");
		
		for(courses cou : courseList){
			model.addRow(new Object[]{cou.getCourse_id(),cou.getTitle(),cou.getFee(),cou.getType(),cou.getDuration()});
		}	
	}
	
	/**
	 * Method to create new course
	 * 
	 */
	private void newCourse(){
		final dialog_newCourse dialog_newC = new dialog_newCourse();
		dialog_newC.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		int id = coursesDAO.getAllCourses("FROM courses").size()+1;
		dialog_newC.tfCourseId.setText(id+"");
		dialog_newC.setVisible(true);
		dialog_newC.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String title = dialog_newC.tfTitle.getText();
					String type = dialog_newC.tfType.getText();
					double fee = Double.parseDouble(dialog_newC.tfFee.getText());
					int duration = Integer.parseInt(dialog_newC.tfDuration.getText());
					if(checkValidate(title,duration)){
						courses course = new courses();
						course.setTitle(title);
						course.setFee(fee);
						course.setType(type);
						course.setDuration(duration);
						
						coursesDAO.insertCourse(course);
						JOptionPane.showMessageDialog(null, "New course created!");
						loadData();
						dialog_newC.dispose();
					}
					
				}catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "Please Fill all fields!");
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Fee and duration must be number!");
				}				
			}
		});
	}
	/**
	 * Method to check validation of fields 
	 *
	 * @param title parameter to check title is valid or not
	 * @param hour parameter to check hour is valid or not
	 * @return true for valid and false for invalid
	 */
	private boolean checkValidate(String title,int hour){
		if(!validate.isName(title)){
			JOptionPane.showMessageDialog(null, "Title must start with capital letter\n E.g Abcd!","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(hour<1){
			JOptionPane.showMessageDialog(null, "Duration must greater than or equal 1","Invalid",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	private int selectedId=-1;
	/**
	 * Method to edit selected course
	 * 
	 */
	private void editCourse(){
		dialog_editC = new dialog_editCourse();
		dialog_editC.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		if(!(table.getSelectedRow()<0)){
			selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
			courses cou = coursesDAO.getAllCourses("FROM courses WHERE course_id = '" + selectedId + "'").get(0);
			
			//set textfield values
			dialog_editC.tfCourseId.setText(selectedId+"");
			dialog_editC.tfTitle.setText(cou.getTitle());
			dialog_editC.tfType.setText(cou.getType());
			dialog_editC.tfFee.setText(cou.getFee()+"");
			dialog_editC.tfDuration.setText(cou.getDuration()+"");
			dialog_editC.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Please select a course to edit!","Incomplete",JOptionPane.ERROR_MESSAGE);
		}
		
		dialog_editC.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try{
					courses cou = coursesDAO.getAllCourses("FROM courses WHERE course_id = '" + selectedId + "'").get(0);
					String title = dialog_editC.tfTitle.getText();
					String type = dialog_editC.tfType.getText();
					double fee = Double.parseDouble(dialog_editC.tfFee.getText());
					int duration = Integer.parseInt(dialog_editC.tfDuration.getText());
					if(checkValidate(title,duration)){
						int res = JOptionPane.showConfirmDialog(null, "Are you sure want to edit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

		            	if (res == JOptionPane.YES_OPTION) {
		            		cou.setTitle(title);
							cou.setFee(fee);
							cou.setType(type);
							cou.setDuration(duration);
								
							coursesDAO.updateCourse(cou);
							JOptionPane.showMessageDialog(null, "Course edited!");
							loadData();
							dialog_editC.dispose();
		            	} 
					}
						
				}catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "Please Fill all fields!");
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Fee and duration must be number!");
				}							
			}
		});
	}
	/**
	 * Method to delete selected course
	 * 
	 */
	private void deleteCourse(){

		if(!(table.getSelectedRow()<0)){
			selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
			courses cou = coursesDAO.getAllCourses("FROM courses WHERE course_id = '" + selectedId + "'").get(0);
			
			if(cou.getClasses().size()!=0){
				JOptionPane.showMessageDialog(null, "This course joined classes\n Can't delete!","Incomplete",JOptionPane.ERROR_MESSAGE);
			}
			else{
				int res = JOptionPane.showConfirmDialog(null, "Are you sure want to delete?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

            	if (res == JOptionPane.YES_OPTION) {
            		coursesDAO.deleteCourse(cou);
					JOptionPane.showMessageDialog(null, "Course " + cou.getTitle() + " is deleted!");
					loadData();			
            	}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please select a course to delete!","Incomplete",JOptionPane.ERROR_MESSAGE);
		}
	}
}