package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import pojo.users;

import dao.dao;

import java.awt.Component;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.awt.Toolkit;

public class dialog_changeUsername extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnChange;
	private JTextField tfUser;
	private JRadioButton rdbtnManager,rdbtnStaff;
	private JPasswordField pfPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_changeUsername dialog = new dialog_changeUsername();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_changeUsername() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_changeUsername.class.getResource("/images/changeUser.png")));
		setTitle("Change Username");
		setBounds(100, 100, 289, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				JLabel lblAccount = new JLabel("Account");
				lblAccount.setFont(new Font("Dialog", Font.PLAIN, 12));
				panel.add(lblAccount);
			}
			ButtonGroup bg = new ButtonGroup();
			{
				rdbtnManager = new JRadioButton("Manager");
				rdbtnManager.setFont(new Font("Dialog", Font.PLAIN, 12));
				rdbtnManager.setSelected(true);
				rdbtnManager.setBackground(Color.WHITE);
				bg.add(rdbtnManager);
				panel.add(rdbtnManager);
			}
			{
				rdbtnStaff = new JRadioButton("Staff");
				rdbtnStaff.setFont(new Font("Dialog", Font.PLAIN, 12));
				rdbtnStaff.setBackground(Color.WHITE);
				bg.add(rdbtnStaff);
				panel.add(rdbtnStaff);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(2, 2, 5, 5));
			{
				JLabel lblNewUsername = new JLabel("New username");
				lblNewUsername.setFont(new Font("Dialog", Font.PLAIN, 12));
				lblNewUsername.setBackground(Color.WHITE);
				panel.add(lblNewUsername);
			}
			{
				tfUser = new JTextField();
				tfUser.setFont(new Font("Dialog", Font.PLAIN, 12));
				panel.add(tfUser);
				tfUser.setColumns(10);
			}
			{
				JLabel lblConfirmPassword = new JLabel("Confirm Password");
				lblConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
				lblConfirmPassword.setBackground(Color.WHITE);
				panel.add(lblConfirmPassword);
			}
			{
				pfPass = new JPasswordField();
				pfPass.setFont(new Font("Dialog", Font.PLAIN, 12));
				panel.add(pfPass);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnChange = new JButton("Change");
				btnChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						changeUsername();
					}
				});
				btnChange.setBackground(Color.GREEN);
				btnChange.setFont(new Font("Dialog", Font.PLAIN, 12));
				btnChange.setActionCommand("Change");
				buttonPane.add(btnChange);
				getRootPane().setDefaultButton(btnChange);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnChange}));
	}

	private void changeUsername(){
		//get username
		//check is username blank 
		//if true
			//show error message
		//if false
			//check is password true
			//if valid
				//save username
			//if invalid
			// show error -- wrong password
		
		String username = tfUser.getText();
		if(username.equals("")){
			JOptionPane.showMessageDialog(null,"Username cannot be blank");
		}else{
			@SuppressWarnings("deprecation")
			String pass = pfPass.getText();
			String role = "Manager";
			if(rdbtnStaff.isSelected()){
				role = "Staff";
			}
			String q = "FROM users WHERE  password = '" + md5encryption("#" + pass + "#") +"' AND user_role = '" +role + "'";
			List<users> userList = usersDAO.getAllUsers(q);
			if(userList.size()!=0){
				userList.get(0).setUsername(username);
				usersDAO.updateUsers(userList.get(0));
				JOptionPane.showMessageDialog(null, "Username change successfully!","Completed",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Password Incorrect!","Invalid",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	//password encryption
		private static String md5encryption(String text)
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
	            JOptionPane.showMessageDialog(null,e1.getClass().getName() + ": " + e1.getMessage());   
	        }
	        return hashtext;     
	    }
}