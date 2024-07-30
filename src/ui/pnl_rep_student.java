package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.students;

import dao.DAO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_rep_student </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_rep_student extends JPanel implements DAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfName;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_rep_student() {
		setSize(560,300);
		setLayout(null);
		setBackground(new Color(245, 255, 250));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 540, 245);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 46, 14);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(71, 8, 86, 20);
		add(tfName);
		tfName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(pnl_rep_student.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameSearch();
			}
		});
		btnSearch.setBounds(170, 7, 101, 23);
		add(btnSearch);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		btnShowAll.setBounds(281, 7, 89, 23);
		add(btnShowAll);
		loadData();
	}
	/**
	 * Method to load data from database add to table
	 * 
	 */
	public void loadData(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		List<students> stdList = studentsDAO.getAllStudents("FROM students");
		
		for(students std : stdList){
			model.addRow(new Object[]{std.getStudent_id(),std.getName(),std.getNrc(),std.getDate_of_birth(),std.getPhone(),std.getAddress()});
		}
	}
	
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
