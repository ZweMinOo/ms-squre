package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import pojo.*;
import dao.dao;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.toedter.calendar.JDateChooser;

public class dialog_serviceRelease extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField tfTotalAcsCost;
	JTextField tfServiceFee;
	private JTextField tfTotalCost;
	private List<services_details> sdList = new ArrayList<services_details>();
	private double totalAcsCost=0;
	private services service;
	JDateChooser dcReleaseDate;
	JButton btnRelease;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_serviceRelease dialog = new dialog_serviceRelease(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_serviceRelease(int sNo) {
		setResizable(false);
		setTitle("Service Release");
		setBounds(100, 100, 530, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblServiceNo = new JLabel("Service no - ");
		lblServiceNo.setBounds(10, 11, 180, 14);
		contentPanel.add(lblServiceNo);
		
		JLabel lblCustomerName = new JLabel("Customer name - ");
		lblCustomerName.setBounds(10, 47, 211, 14);
		contentPanel.add(lblCustomerName);
		
		JLabel lblBrandModel = new JLabel("Brand & Model - ");
		lblBrandModel.setBounds(10, 80, 223, 14);
		contentPanel.add(lblBrandModel);
		
		JLabel lblError = new JLabel("Error");
		lblError.setBounds(187, 11, 46, 14);
		contentPanel.add(lblError);
		
		JTextArea taError = new JTextArea();
		taError.setBackground(SystemColor.controlHighlight);
		taError.setWrapStyleWord(true);
		taError.setLineWrap(true);
		taError.setEditable(false);
		taError.setBounds(243, 11, 261, 47);
		contentPanel.add(taError);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 105, 459, 125);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line No", "Item", "Qty", "Price", "Line Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(96, 235, 305, 73);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(3, 2, 3, 3));
		
		JLabel lblTotalAccessoriesCost = new JLabel("Total accessories cost");
		panel.add(lblTotalAccessoriesCost);
		
		tfTotalAcsCost = new JTextField();
		panel.add(tfTotalAcsCost);
		tfTotalAcsCost.setEditable(false);
		tfTotalAcsCost.setColumns(10);
		
		JLabel lblServiceFee = new JLabel("Service fee");
		panel.add(lblServiceFee);
		
		tfServiceFee = new JTextField();
		tfServiceFee.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					tfTotalCost.setText(calcuateTotalCost()+"");
				}catch(NumberFormatException nfe){
					char [] ch = tfServiceFee.getText().toCharArray();
					String txt="";
					for(int i=0;i<ch.length-1;i++){
						try{
							Double.parseDouble(ch[i]+"");
							txt += ch[i];
						}
						catch(NumberFormatException e){
							
						}
					}
					tfServiceFee.setText(txt);
					if(txt.equals("")){
						tfTotalCost.setText(totalAcsCost+"");
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}	
		});
		panel.add(tfServiceFee);
		tfServiceFee.setColumns(10);
		
		JLabel lblTotalCost = new JLabel("Total cost");
		panel.add(lblTotalCost);
		
		tfTotalCost = new JTextField();
		tfTotalCost.setEditable(false);
		panel.add(tfTotalCost);
		tfTotalCost.setColumns(10);
		
		JLabel lblReleaseDate = new JLabel("Release date");
		lblReleaseDate.setBounds(252, 80, 80, 14);
		contentPanel.add(lblReleaseDate);
		
		dcReleaseDate = new JDateChooser();
		dcReleaseDate.setBounds(366, 74, 91, 20);
		contentPanel.add(dcReleaseDate);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRelease = new JButton("Release");
				btnRelease.setBackground(Color.GREEN);
				btnRelease.setActionCommand("OK");
				buttonPane.add(btnRelease);
				getRootPane().setDefaultButton(btnRelease);
			}
		}
		String query = "FROM services WHERE service_id = '" + sNo + "'";
		service = servicesDAO.getAllServices(query).get(0);

		query = "FROM services_details WHERE service_id = '" + sNo + "'";
		
		sdList = services_detailsDAO.getAllServicesDetails(query);
		
		if(service!=null){
			int serNo = service.getService_id();
			String cName = service.getCustomer().getCustomer_name();
			String bAm = service.getModel_name();
			String error = service.getError();
			
			lblServiceNo.setText(lblServiceNo.getText() + " " + serNo);
			lblCustomerName.setText(lblCustomerName.getText() + " " + cName);
			lblBrandModel.setText(lblBrandModel.getText() + " " + bAm);
			taError.setText(error);
		}
		if(sdList.size() != 0) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=0;i<sdList.size();i++){
				services_details temp = sdList.get(i);
				totalAcsCost += temp.getQuantity()*temp.getPrice();
				model.addRow(new String[]{(i+1)+"", temp.getAccessory_name(), temp.getQuantity()+"", temp.getPrice()+"", (temp.getQuantity()*temp.getPrice())+""});
			}
		}
		tfTotalAcsCost.setText(totalAcsCost+"");
		tfTotalCost.setText(totalAcsCost+"");
	}
	
	private double calcuateTotalCost() throws NumberFormatException{ // calculating total service cost
		double tc = totalAcsCost + Double.parseDouble(tfServiceFee.getText());;
		return tc;
	}
	
	public void saveToDB(){ // save to database
		services service = this.service;
		service.setReleased_date(dcReleaseDate.getDate());
		service.setService_fee(calcuateTotalCost());
		servicesDAO.updateServices(service);
	}
}