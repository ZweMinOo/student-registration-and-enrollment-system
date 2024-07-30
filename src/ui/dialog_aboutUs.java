package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * <h1> dialog_aboutUs </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@SuppressWarnings("serial")
public class dialog_aboutUs extends JDialog {

	private final JPanel contentPanel = new JPanel();
	// data
	private final String version = "1.0.0";
	private final String releasedDate = "7/22/2018";
	private final String developer = "Zwe Min Oo";
	private final String github = "github.com/ZweMinOo";
	private final String facebook = "facebook.com/zmo.james";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_aboutUs dialog = new dialog_aboutUs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_aboutUs() {
		setTitle("About us");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_aboutUs.class.getResource("/images/about.png")));
		setBounds(100, 100, 374, 283);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblVersion = new JLabel("Version : "+ version);
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVersion.setForeground(Color.WHITE);
		lblVersion.setBounds(10, 11, 293, 29);
		contentPanel.add(lblVersion);
		
		JLabel lblReleasedDate = new JLabel("Released date : " + releasedDate);
		lblReleasedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReleasedDate.setForeground(Color.WHITE);
		lblReleasedDate.setBounds(10, 51, 293, 26);
		contentPanel.add(lblReleasedDate);
		
		JLabel lblDeveloper = new JLabel("Developer : " + developer);
		lblDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeveloper.setForeground(Color.WHITE);
		lblDeveloper.setBounds(10, 88, 293, 32);
		contentPanel.add(lblDeveloper);
		
		JLabel lblGithub = new JLabel("GitHub : " + github);
		lblGithub.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGithub.setForeground(Color.WHITE);
		lblGithub.setBounds(10, 131, 293, 29);
		contentPanel.add(lblGithub);
		
		JLabel lblFacebook = new JLabel("Facebook : " + facebook);
		lblFacebook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacebook.setForeground(Color.WHITE);
		lblFacebook.setBounds(10, 171, 293, 25);
		contentPanel.add(lblFacebook);
		
		JButton btnCopyGitHub = new JButton("Copy");
		btnCopyGitHub.setFocusable(false);
		btnCopyGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copy(github);
				JOptionPane.showMessageDialog(null, "Copied github profile");
			}
		});
		btnCopyGitHub.setToolTipText("Copy to clipboard");
		btnCopyGitHub.setBounds(282, 136, 76, 23);
		contentPanel.add(btnCopyGitHub);
		
		JButton btnCopyFacebook = new JButton("Copy");
		btnCopyFacebook.setFocusable(false);
		btnCopyFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copy(facebook);
				JOptionPane.showMessageDialog(null, "Copied facebook profile");
			}
		});
		btnCopyFacebook.setToolTipText("Copy to clipboard");
		btnCopyFacebook.setBounds(282, 174, 76, 23);
		contentPanel.add(btnCopyFacebook);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	/**
	 * Method to copy text to clipboard
	 * 
	 * @param text parameter to receive text to copy
	 */
	public void copy(String text)
    {
        Clipboard clipboard = getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
    }
	
	/**
	 * Method to get system clipboard 
	 * 
	 * @return systemClipboard system call clipboard
	 */
	private Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
}
