package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

import pojo.courses;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_rep_course </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_rep_course extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfTitle;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_rep_course() {
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
				"Course id", "Title", "Fee(MMK)", "Type", "Duration(Hour)"
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
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(10, 11, 46, 14);
		add(lblTitle);
		
		tfTitle = new JTextField();
		tfTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTitle.setBounds(52, 8, 103, 20);
		add(tfTitle);
		tfTitle.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(pnl_rep_course.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titleSearch();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(165, 7, 108, 23);
		add(btnSearch);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShowAll.setBounds(283, 7, 89, 23);
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
		List<courses> courseList = coursesDAO.getAllCourses("FROM courses");
		
		for(courses cou : courseList){
			model.addRow(new Object[]{cou.getCourse_id(),cou.getTitle(),cou.getFee(),cou.getType(),cou.getDuration()});
		}	
	}
	/**
	 * Method for adding result to table according to search results
	 * 
	 */
	private void titleSearch(){
		String key = tfTitle.getText().toLowerCase();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		List<courses> courseList = coursesDAO.getAllCourses("FROM courses");
		List<courses> searchedList = new ArrayList<courses>();
		
		for(courses cou : courseList){
			String title = cou.getTitle().toLowerCase();
			System.out.println(title);
			if(title.contains(key)){
				searchedList.add(cou);
			}
		}	
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		
		for(courses cou : searchedList){
			model.addRow(new Object[]{cou.getCourse_id(),cou.getTitle(),cou.getFee(),cou.getType(),cou.getDuration()});
		}	
	}
}
