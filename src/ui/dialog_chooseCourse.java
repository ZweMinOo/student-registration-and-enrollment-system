package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.DAO;
import pojo.courses;
/**
 * <h1> dialog_chooseCourse </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class dialog_chooseCourse extends JDialog implements DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table_1;
	JTextField tfSelectedCourse;
	JButton btnChoose;
	courses course = new courses();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_chooseCourse dialog = new dialog_chooseCourse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public dialog_chooseCourse() {
		setResizable(false);
		setTitle("Choose Course");
		setBounds(100, 100, 439, 250);
		getContentPane().setBackground(new Color(245, 255, 250));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 168);
		contentPanel.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
		table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		table_1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int selectedId = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0) + "");
				course = coursesDAO.getAllCourses("FROM courses WHERE course_id = '" + selectedId + "'").get(0);
				tfSelectedCourse.setText(course.getTitle());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		scrollPane.setViewportView(table_1);
		
		JLabel lblSelectedCourse = new JLabel("Selected Course");
		lblSelectedCourse.setBounds(20, 193, 116, 14);
		contentPanel.add(lblSelectedCourse);
		
		tfSelectedCourse = new JTextField();
		tfSelectedCourse.setEditable(false);
		tfSelectedCourse.setBounds(146, 190, 100, 20);
		contentPanel.add(tfSelectedCourse);
		tfSelectedCourse.setColumns(10);
		
		btnChoose = new JButton("Choose");
		btnChoose.setBounds(312, 190, 89, 23);
		contentPanel.add(btnChoose);
		loadData();
	}
	/**
	 * Method to load data from database add to table
	 * 
	 */
	private void loadData(){
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table_1.revalidate();
		}
		List<courses> courseList = coursesDAO.getAllCourses("FROM courses");
		
		for(courses cou : courseList){
			model.addRow(new Object[]{cou.getCourse_id(),cou.getTitle(),cou.getFee(),cou.getType(),cou.getDuration()});
		}	
	}
}
