package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import pojo.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import dao.dao;

public class pnl_newService extends JPanel implements dao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfBandM;
	private JTextArea taError,taServiceNote;
	private final dialog_customerInfo customerInfo = new dialog_customerInfo();
	private customers customer = null;
	private List<services> servicesList = new ArrayList<services>();
	private JDateChooser dcReceivedDate;
	private JButton btnFill;
	private JLabel lblCustomerName;
	/**
	 * Create the panel.
	 */
	public pnl_newService() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblCustomerInfo = new JLabel("Customer info");
		GridBagConstraints gbc_lblCustomerInfo = new GridBagConstraints();
		gbc_lblCustomerInfo.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerInfo.gridx = 0;
		gbc_lblCustomerInfo.gridy = 0;
		panel_1.add(lblCustomerInfo, gbc_lblCustomerInfo);
		
		btnFill = new JButton("Fill"); // to fill customer information
		btnFill.setForeground(Color.BLACK);
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerInfo.setVisible(true);
				customerInfo.btnOK.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
						customer = customerInfo.getCustomer();
						if(customer != null){
							String lb = lblCustomerName.getText();
							lblCustomerName.setText(lb + customer.getCustomer_name());
						}
						customerInfo.setVisible(false);
						customerInfo.dispose();
					}
				});
			}
		});
		btnFill.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnFill = new GridBagConstraints();
		gbc_btnFill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFill.insets = new Insets(0, 0, 5, 5);
		gbc_btnFill.gridx = 1;
		gbc_btnFill.gridy = 0;
		panel_1.add(btnFill, gbc_btnFill);
		
		JLabel lblServiceNote = new JLabel("Service note");
		GridBagConstraints gbc_lblServiceNote = new GridBagConstraints();
		gbc_lblServiceNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblServiceNote.anchor = GridBagConstraints.WEST;
		gbc_lblServiceNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblServiceNote.gridx = 3;
		gbc_lblServiceNote.gridy = 0;
		panel_1.add(lblServiceNote, gbc_lblServiceNote);
		
		taServiceNote = new JTextArea(2,2);
		taServiceNote.setLineWrap(true);
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 4;
		gbc_scrollPane_2.gridy = 0;
		panel_1.add(scrollPane_2, gbc_scrollPane_2);
		scrollPane_2.setViewportView(taServiceNote);
		
		JLabel lblModel = new JLabel("Brand&Model");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		panel_1.add(lblModel, gbc_lblModel);
		
		tfBandM = new JTextField();
		GridBagConstraints gbc_tfBandM = new GridBagConstraints();
		gbc_tfBandM.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBandM.insets = new Insets(0, 0, 5, 5);
		gbc_tfBandM.gridx = 1;
		gbc_tfBandM.gridy = 1;
		panel_1.add(tfBandM, gbc_tfBandM);
		tfBandM.setColumns(10);
		
		JLabel lblError = new JLabel("Error");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 2;
		panel_1.add(lblError, gbc_lblError);
		
		taError = new JTextArea(2,2);
		taError.setLineWrap(true);
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 2;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		scrollPane_1.setViewportView(taError);
		
		JLabel lblReceivedDate = new JLabel("Received date");
		GridBagConstraints gbc_lblReceivedDate = new GridBagConstraints();
		gbc_lblReceivedDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReceivedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceivedDate.anchor = GridBagConstraints.WEST;
		gbc_lblReceivedDate.gridx = 3;
		gbc_lblReceivedDate.gridy = 2;
		panel_1.add(lblReceivedDate, gbc_lblReceivedDate);
		
		dcReceivedDate = new JDateChooser();
		GridBagConstraints gbc_dcReceivedDate = new GridBagConstraints();
		gbc_dcReceivedDate.insets = new Insets(0, 0, 5, 5);
		gbc_dcReceivedDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_dcReceivedDate.gridx = 4;
		gbc_dcReceivedDate.gridy = 2;
		panel_1.add(dcReceivedDate, gbc_dcReceivedDate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add to list");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(customer == null){
					JOptionPane.showMessageDialog(null, "Please fill customer info first!");
				}
				else{
					addToList(); // add to Service list
					addToTable(); // display service details on table
				}
			}
		});
		btnAdd.setBackground(new Color(0, 102, 204));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 3;
		panel_1.add(btnAdd, gbc_btnAdd);
		
		JButton btnSaveService = new JButton("Save Service");
		btnSaveService.setToolTipText("Save added service list");
		btnSaveService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveToDB();
			}
		});
		btnSaveService.setBackground(new Color(51, 204, 0));
		GridBagConstraints gbc_btnSaveService = new GridBagConstraints();
		gbc_btnSaveService.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveService.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveService.gridx = 5;
		gbc_btnSaveService.gridy = 3;
		panel_1.add(btnSaveService, gbc_btnSaveService);
		
		lblCustomerName = new JLabel("Customer name - ");
		GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
		gbc_lblCustomerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCustomerName.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerName.gridwidth = 2;
		gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerName.gridx = 0;
		gbc_lblCustomerName.gridy = 4;
		panel_1.add(lblCustomerName, gbc_lblCustomerName);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Brand&Model", "Error", "Service note", "Received date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		scrollPane.setViewportView(table);
	}
	
	private void clearTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
	}
	
	private void addToList(){
		servicesList.add(this.getService());		
	}
	
	private services getService(){
		services service = new services();
		String bAm = this.tfBandM.getText();
		String error = taError.getText();
		String serviceNote = this.taServiceNote.getText();
		Date receivedDate = this.dcReceivedDate.getDate();
		
		service.setModel_name(bAm);
		service.setError(error);
		service.setService_note(serviceNote);
		service.setReceived_date(receivedDate);
		
		return service;
	}
	
	private void addToTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		
		for(int i=rowCount;i<servicesList.size();i++){
			services ser = servicesList.get(i);
			model.addRow(new Object[]{(i+1),ser.getModel_name(),ser.getError(),ser.getService_note(),ser.getReceived_date()});
		}
	}
	
	private void saveToDB(){
		if(servicesList.size()==0){
			JOptionPane.showMessageDialog(null, "No service added");
		}
		else {
			//save customer
			customersDAO.insertCustomers(customer);
			
			System.out.println(customer.getCustomer_id());
			//save service
			for(services temp:servicesList){
				temp.setCustomer(customer);
				servicesDAO.insertServices(temp);
			}
			//clear table
			clearTable();
			
			//reset all
			customer = null;
			servicesList = new ArrayList<services>();
			JOptionPane.showMessageDialog(null, "Completely saved");
		}
	}
}