package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import pojo.customers;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class dialog_customerInfo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCustomerName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfCity;
	private JLabel lblCustomerName;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JLabel lblCity;
	private JPanel buttonPane;
	JButton btnOK;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_customerInfo dialog = new dialog_customerInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_customerInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_customerInfo.class.getResource("/images/about.png")));
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Customer Info");
		setBounds(100, 100, 366, 212);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblCustomerName = new JLabel("Customer Name");
			lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
			gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
			gbc_lblCustomerName.anchor = GridBagConstraints.EAST;
			gbc_lblCustomerName.gridx = 0;
			gbc_lblCustomerName.gridy = 0;
			contentPanel.add(lblCustomerName, gbc_lblCustomerName);
		}
		{
			tfCustomerName = new JTextField();
			tfCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_tfCustomerName = new GridBagConstraints();
			gbc_tfCustomerName.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfCustomerName.weighty = 1.0;
			gbc_tfCustomerName.insets = new Insets(0, 0, 5, 0);
			gbc_tfCustomerName.gridx = 1;
			gbc_tfCustomerName.gridy = 0;
			contentPanel.add(tfCustomerName, gbc_tfCustomerName);
			tfCustomerName.setColumns(10);
		}
		{
			lblPhone = new JLabel("Phone");
			lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 1;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			tfPhone = new JTextField();
			tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_tfPhone = new GridBagConstraints();
			gbc_tfPhone.weighty = 1.0;
			gbc_tfPhone.insets = new Insets(0, 0, 5, 0);
			gbc_tfPhone.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfPhone.gridx = 1;
			gbc_tfPhone.gridy = 1;
			contentPanel.add(tfPhone, gbc_tfPhone);
			tfPhone.setColumns(10);
		}
		{
			lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 2;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_tfEmail = new GridBagConstraints();
			gbc_tfEmail.weighty = 1.0;
			gbc_tfEmail.insets = new Insets(0, 0, 5, 0);
			gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfEmail.gridx = 1;
			gbc_tfEmail.gridy = 2;
			contentPanel.add(tfEmail, gbc_tfEmail);
			tfEmail.setColumns(10);
		}
		{
			lblAddress = new JLabel("Address");
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.WEST;
			gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 3;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			tfAddress = new JTextField();
			tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_tfAddress = new GridBagConstraints();
			gbc_tfAddress.weighty = 1.0;
			gbc_tfAddress.anchor = GridBagConstraints.NORTH;
			gbc_tfAddress.insets = new Insets(0, 0, 5, 0);
			gbc_tfAddress.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfAddress.gridx = 1;
			gbc_tfAddress.gridy = 3;
			contentPanel.add(tfAddress, gbc_tfAddress);
			tfAddress.setColumns(10);
		}
		{
			lblCity = new JLabel("City");
			lblCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblCity = new GridBagConstraints();
			gbc_lblCity.anchor = GridBagConstraints.WEST;
			gbc_lblCity.insets = new Insets(0, 0, 0, 5);
			gbc_lblCity.gridx = 0;
			gbc_lblCity.gridy = 4;
			contentPanel.add(lblCity, gbc_lblCity);
		}
		{
			tfCity = new JTextField();
			tfCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfCity.setText("");
			GridBagConstraints gbc_tfCity = new GridBagConstraints();
			gbc_tfCity.weighty = 1.0;
			gbc_tfCity.anchor = GridBagConstraints.NORTH;
			gbc_tfCity.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfCity.gridx = 1;
			gbc_tfCity.gridy = 4;
			contentPanel.add(tfCity, gbc_tfCity);
			tfCity.setColumns(10);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnOK.setBackground(new Color(0, 255, 51));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCancel.setBackground(new Color(255, 51, 51));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tfCustomerName.setText("");
						tfPhone.setText("");
						tfEmail.setText("");
						tfAddress.setText("");
						tfCity.setText("");
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfCustomerName, tfPhone, tfEmail, tfAddress, tfCity, btnOK}));
		//setVisible(true);
	}
	
	public customers getCustomer(){
		
		customers cust = new customers();
		try {
			
			
			validate.isName(tfCustomerName.getText());
			validate.isPhone(tfPhone.getText());
			validate.isEmail(tfEmail.getText());
			validate.isCity(tfCity.getText());
			
			cust.setCustomer_name(tfCustomerName.getText());
			cust.setPhone(tfPhone.getText());
			cust.setEmail(tfEmail.getText());
			cust.setAddress(tfAddress.getText());
			cust.setCity(tfCity.getText());
			return cust;
		}
		catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(this, iae.getMessage() , "Invalid",JOptionPane.ERROR_MESSAGE);
			System.out.println(iae.getMessage());
			return null;
		}
		
	}
}