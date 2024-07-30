package ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

import pojo.students;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_admin_student </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_admin_student extends JPanel implements DAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTable table;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_admin_student() {
		setSize(560,300);
		setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(10, 11, 85, 14);
		add(lblStudentName);
		
		tfName = new JTextField();
		tfName.setBounds(105, 8, 86, 20);
		add(tfName);
		tfName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(pnl_admin_student.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameSearch();
			}
		});
		btnSearch.setBounds(210, 7, 101, 23);
		add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 540, 245);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Student id", "Name", "NRC", "Date of Birth", "Phone", "Address"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		scrollPane.setViewportView(table);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		btnShowAll.setBounds(321, 7, 89, 23);
		add(btnShowAll);
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
		List<students> stdList = studentsDAO.getAllStudents("FROM students");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		
		for(students std : stdList){
			model.addRow(new Object[]{std.getStudent_id(),std.getName(),std.getNrc(),sdf.format(std.getDate_of_birth()),std.getPhone(),std.getAddress()});
		}
	}
	/**
	 * Method for adding search results by specific name to table 
	 * 
	 */
	private void nameSearch(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		List<students> stdList = studentsDAO.getAllStudents("FROM students");
		List<students> searchedList = new ArrayList<students>();
		
		String key = tfName.getText().toLowerCase();
		for(students std : stdList){
			String name = std.getName().toLowerCase();
			if(name.contains(key)){
				searchedList.add(std);
			}
		}
		for(students std : searchedList){
			model.addRow(new Object[]{std.getStudent_id(),std.getName(),std.getNrc(),std.getDate_of_birth(),std.getPhone(),std.getAddress()});
		}
	}
}
