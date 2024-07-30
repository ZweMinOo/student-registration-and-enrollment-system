package ui;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.payments;

import dao.DAO;
/**
 * <h1> pnl_admin_payment </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class pnl_admin_payment extends JPanel implements DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public pnl_admin_payment() {
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
				"Payment id", "Student id", "Class id", "Course Title", "Paid Amount", "Remain Amount", "Payment Date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
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
		List<payments> payList = paymentsDAO.getAllPayments("FROM payments");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		
		for(payments pay : payList){
			model.addRow(new Object[]{pay.getPayment_id(),pay.getStudents().getStudent_id(),pay.getClasses().getClass_id(),pay.getClasses().getCourses().getTitle(),pay.getPaid_amount(),pay.getRemaining_amount(),sdf.format(pay.getPayment_date())});
		}
	}
}
