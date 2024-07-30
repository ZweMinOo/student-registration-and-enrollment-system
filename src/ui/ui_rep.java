package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
/**
 * <h1> ui_rep </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class ui_rep extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private pnl_rep_enrollment panel5;
	private pnl_rep_registration panel4;
	private pnl_rep_student panel3;
	private pnl_rep_class panel2;
	private pnl_rep_course panel1;
	private menuBar menuBar = new menuBar("rep");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui_rep frame = new ui_rep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ui_rep() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ui_rep.class.getResource("/images/logo.png")));
		setTitle("Receptionist");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 590, 448);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInetCollege = new JLabel("iNet College");
		lblInetCollege.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInetCollege.setBounds(244, 11, 104, 19);
		contentPane.add(lblInetCollege);
		
		JLabel lblNewLabel = new JLabel("Student Registration and Enrollment System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(152, 31, 297, 19);
		contentPane.add(lblNewLabel);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 61, 564, 332);
		contentPane.add(tabbedPane);
		
		panel1 = new pnl_rep_course();
		panel1.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Course", null, panel1, null);
		
		panel2 = new pnl_rep_class();
		panel2.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Class", null, panel2, null);
		
		panel3 = new pnl_rep_student();
		panel3.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Student", null, panel3, null);
		
		panel4 = new pnl_rep_registration();
		panel4.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Registration", null, panel4, null);
		
		panel5 = new pnl_rep_enrollment();
		panel5.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Enrollment", null, panel5, null);
		
		//Reload Data on Click
		tabbedPane.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				panel3.loadData();
			}			
		});
		
		this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
            	int res = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

            	if (res == JOptionPane.YES_OPTION) { 
            		 System.exit(0);
            	} 
            }
        } );
	}
}
