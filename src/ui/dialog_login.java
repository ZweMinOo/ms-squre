package ui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.dao;

import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class dialog_login extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
	private JButton btnLogin;
	private JButton btnClear;
	private JRadioButton rdbtnManager;
	private JPasswordField pfPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_login dialog = new dialog_login();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_login.class.getResource("/images/MS square.png")));
		setTitle("Login to MS quare ");
		setResizable(false);
		setBounds(100, 100, 408, 308);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "Are you sure to exit?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(dialog_login.class.getResource("/images/MS square 0.jpg")));
		lblLogo.setBounds(123, 3, 165, 133);
		contentPanel.add(lblLogo);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(72, 147, 92, 14);
		contentPanel.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(171, 144, 137, 20);
		contentPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(74, 186, 87, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblUserRole = new JLabel("User role");
		lblUserRole.setBounds(74, 223, 87, 14);
		contentPanel.add(lblUserRole);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBackground(Color.WHITE);
		rdbtnManager.setSelected(true);
		rdbtnManager.setBounds(171, 219, 87, 23);
		contentPanel.add(rdbtnManager);
		bg.add(rdbtnManager);
		
		JRadioButton rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setBackground(Color.WHITE);
		rdbtnStaff.setBounds(260, 219, 87, 23);
		bg.add(rdbtnStaff);
		contentPanel.add(rdbtnStaff);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(171, 183, 137, 20);
		contentPanel.add(pfPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						checkLogin();
					}
				});
				
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			{
				btnClear = new JButton("Clear");
				btnClear.setBackground(Color.WHITE);
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tfUsername.setText("");
						pfPassword.setText("");
					}
				});
				btnClear.setActionCommand("Cancel");
				buttonPane.add(btnClear);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfUsername, pfPassword, btnLogin}));
	}
	
	private void disp(){
		this.setVisible(false);
	}
	
	@SuppressWarnings("deprecation")
	private void checkLogin(){
		String user=tfUsername.getText(),pass=pfPassword.getText(),ur;
		System.out.println("\n"+pass+"\n");
		if(rdbtnManager.isSelected()){
			ur = "Manager";
		}else{
			ur = "Staff";
		}
		
		if(!user.equals("")&&!pass.equals("")){
			if(login(user,pass,ur)){
				if(ur.equals("Manager")){
					final ui_manager m = new ui_manager();
					m.setVisible(true);
					m.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (JOptionPane.showConfirmDialog(m, 
					            "Are you sure to close this window?", "Really Closing?", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					            System.exit(0);
					        }
					    }
					});
				}else{
					final ui_staff s =new ui_staff();
					s.setVisible(true);
					s.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (JOptionPane.showConfirmDialog(s, 
					            "Are you sure to close this window?", "Really Closing?", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					            System.exit(0);
					        }
					    }
					});
				}	
				disp();
			}
			else{
				JOptionPane.showMessageDialog(null, "Login failed. Try again!");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Please fill all boxes");
		}
	}
	
	private boolean login(String username,String password,String userRole){
		boolean isAuth=true;
		int authUser = usersDAO.getAllUsers("FROM users WHERE username = '" + username + "' and  password = '" + md5encryption("#"+password+"#") +"' and user_role = '"+ userRole +"'").size();
		if(authUser==0)
		{
			isAuth = false;
		}
		return isAuth;
	}
	
	private String md5encryption(String text)
    {   String hashtext = null;
        try 
        {
            String plaintext = text;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
              hashtext = "0"+hashtext;   
            }
        } catch (Exception e1) 
        {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null,e1.getClass().getName() + ": " + e1.getMessage());   
        }
        return hashtext;     
    }
}