package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
/**
 * <h1> ui_admin </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class ui_admin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private menuBar menuBar = new menuBar("admin");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui_admin frame = new ui_admin();
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
	public ui_admin() {
		setResizable(false);
		setTitle("Admin");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ui_admin.class.getResource("/images/logo.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 452);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setJMenuBar(menuBar);
		JLabel lblInetCollege = new JLabel("iNet College");
		lblInetCollege.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInetCollege.setBounds(244, 11, 104, 19);
		contentPane.add(lblInetCollege);
		
		JLabel lblNewLabel = new JLabel("Student Registration and Enrollment System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(152, 31, 297, 19);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setBounds(10, 61, 564, 328);
		contentPane.add(tabbedPane);
		
		pnl_admin_course panel1 = new pnl_admin_course();
		panel1.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Course", null, panel1, null);
		
		pnl_admin_class panel2 = new pnl_admin_class();
		panel2.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Class", null, panel2, null);
		
		pnl_admin_student panel3 = new pnl_admin_student();
		panel3.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Student", null, panel3, null);
		
		pnl_admin_payment panel4 = new pnl_admin_payment();
		panel4.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Payment", null, panel4, null);
		
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
