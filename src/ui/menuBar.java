package ui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * <h1> menuBar </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class menuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmExit;
	private JMenu mnAbout;
	private JMenuItem mntmVisitBlog;
	private JMenuItem mntmChangePassword;
	private JMenuItem mntmUserManual;
	private static dialog_aboutUs aboutUs = new dialog_aboutUs();
	
	menuBar(final String user){
		/*** Menu ***/
		
		mnFile = new JMenu("File");
		add(mnFile);
		
		mntmChangePassword = new JMenuItem("Change Password");
		mnFile.add(mntmChangePassword);
		mntmChangePassword.setIcon(new ImageIcon(menuBar.class.getResource("/images/changePass.png")));
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(menuBar.class.getResource("/images/exit.png")));
		mntmExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

            	if (res == JOptionPane.YES_OPTION) {
            		 System.exit(0);
            	} 
			}			
		});
		mnFile.add(mntmExit);
		mntmChangePassword.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog_changePassword dialog = new dialog_changePassword();
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		});
		
		mnHelp = new JMenu("Help");
		add(mnHelp);
		
		mntmUserManual = new JMenuItem("User Manual");
		mntmUserManual.setIcon(new ImageIcon(menuBar.class.getResource("/images/help.png")));
		mnHelp.add(mntmUserManual);
		mntmUserManual.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				URL resource = null;
				if(user.equals("admin"))
				{
					resource = getClass().getResource("User manual (admin).docx");
				}
				else{
					resource = getClass().getResource("User manual (receptionist).docx");
				}
				try{
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(new File(resource.toURI()));
					}
				}
				catch(IOException ioe){
					ioe.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		mnAbout = new JMenu("About");
		add(mnAbout);
		
		mntmVisitBlog = new JMenuItem("Visit Blog");
		mntmVisitBlog.setIcon(new ImageIcon(menuBar.class.getResource("/images/about.png")));
		mnAbout.add(mntmVisitBlog);
		mntmVisitBlog.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aboutUs.setVisible(true);
			}
			
		});
		
		/*** End Menu ***/
	}
}
