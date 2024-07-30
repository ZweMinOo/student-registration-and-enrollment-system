package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import dao.DAO;
import pojo.classes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
/**
 * <h1> dialog_chooseClass </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class dialog_chooseClass extends JDialog implements DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	JTextField tfSelectedClass;
	JButton btnChoose;
	classes classes = new classes();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_chooseClass dialog = new dialog_chooseClass();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_chooseClass() {
		setResizable(false);
		setTitle("Choose Course");
		setBounds(100, 100, 439, 250);
		getContentPane().setBackground(new Color(245, 255, 250));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 168);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Class id", "Course", "Status", "Start date", "End date"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0) + "");
				classes = classesDAO.getAllClasses("FROM classes WHERE class_id = '" + selectedId + "'").get(0);
				tfSelectedClass.setText(classes.getClass_id()+"");
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
		scrollPane.setViewportView(table);
		
		JLabel lblSelectedCourse = new JLabel("Selected Course");
		lblSelectedCourse.setBounds(20, 193, 116, 14);
		contentPanel.add(lblSelectedCourse);
		
		tfSelectedClass= new JTextField();
		tfSelectedClass.setEditable(false);
		tfSelectedClass.setBounds(146, 190, 100, 20);
		contentPanel.add(tfSelectedClass);
		tfSelectedClass.setColumns(10);
		
		btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnChoose.setBounds(312, 190, 89, 23);
		contentPanel.add(btnChoose);
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
		List<classes> classList = classesDAO.getAllClasses("FROM classes WHERE status='On Schedule'");
		
		for(classes cla : classList){
			model.addRow(new Object[]{cla.getClass_id(),cla.getCourses().getTitle(),cla.getStatus(),cla.getStart_date(),cla.getEnd_date()});
		}
	}
}
