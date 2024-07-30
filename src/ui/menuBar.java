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
	private JMenu mnAccountSetting;
	private JMenuItem mntmChangeUsername;
	private JMenuItem mntmChangePassword;
	private JMenuItem mntmHelp;
	
	menuBar(){
		/*** Menu ***/
		
		
		mnFile = new JMenu("File");
		add(mnFile);
		
		mnAccountSetting = new JMenu("Account Setting");
		mnAccountSetting.setIcon(new ImageIcon(menuBar.class.getResource("/images/accSetting.png")));
		mnFile.add(mnAccountSetting);
		
		mntmChangeUsername = new JMenuItem("Change Username");
		mntmChangeUsername.setIcon(new ImageIcon(menuBar.class.getResource("/images/changeUser.png")));
		mntmChangeUsername.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog_changeUsername dialog = new dialog_changeUsername();
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		});
		mnAccountSetting.add(mntmChangeUsername);
		
		mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.setIcon(new ImageIcon(menuBar.class.getResource("/images/changePass.png")));
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
		mnAccountSetting.add(mntmChangePassword);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(menuBar.class.getResource("/images/exit.png")));
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		mnHelp = new JMenu("Help");
		add(mnHelp);
		
		mntmHelp = new JMenuItem("Help");
		mntmHelp.setIcon(new ImageIcon(menuBar.class.getResource("/images/help.png")));
		mnHelp.add(mntmHelp);
		mntmHelp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				URL resource = getClass().getResource("User Manual.docx");
				String file = "User Manual.docx";
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
				// TODO Auto-generated method stub
				String blog = "http://mssquaremdy.blogspot.com";
				if(Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new java.net.URI(blog));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		/*** End Menu ***/
	}
}
