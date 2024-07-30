package ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.classes;

import dao.DAO;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
/**
 * <h1> pnl_rep_class </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_rep_class extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfCourse;
	private JComboBox<String> cbBStatus;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_rep_class() {
		setBackground(new Color(245, 255, 250));
		setSize(560,300);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
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
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(10, 19, 46, 14);
		add(lblStatus);
		
		cbBStatus = new JComboBox<String>();
		cbBStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbBStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Active", "Closed", "Delete", "On Schedule"}));
		cbBStatus.setBounds(66, 13, 97, 20);
		cbBStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		add(cbBStatus);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCourse.setBounds(191, 19, 46, 14);
		add(lblCourse);
		
		tfCourse = new JTextField();
		tfCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCourse.setBounds(247, 16, 111, 20);
		add(tfCourse);
		tfCourse.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(pnl_rep_class.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(368, 15, 103, 23);
		add(btnSearch);
		loadData();
	}
	/**
	 * Method to load data from database add to table by given filter and search results
	 * 
	 */
	private void loadData(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		String status = cbBStatus.getSelectedItem()+"";
		List<classes> classList;
		if(!status.equals("All")){
			classList = classesDAO.getAllClasses("FROM classes WHERE status='"+status+"'");
		}
		else{
			classList = classesDAO.getAllClasses("FROM classes");
		}
		List<classes> searchedList = new ArrayList<classes>();
		
		for(classes cla : classList){
			String key = tfCourse.getText().toLowerCase();
			String course = cla.getCourses().getTitle().toLowerCase();
			if(course.contains(key)){
				searchedList.add(cla);
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		
		for(classes cla : searchedList){
			model.addRow(new Object[]{cla.getClass_id(),cla.getCourses().getTitle(),cla.getStatus(),sdf.format(cla.getStart_date()),sdf.format(cla.getEnd_date())});
		}
	}
}
