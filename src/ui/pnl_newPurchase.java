package ui;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import com.toedter.calendar.JDateChooser;
import dao.dao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojo.*;
import java.awt.BorderLayout;

public class pnl_newPurchase extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfSupplierName;
	private JTextField tfContactName;
	private JTextField tfQuantity;
	private JTextField tfPhone;
	private JTextField tfTotalAmount;
	private JTextField tfEmail;
	private JTextArea taAddress;
	private JTextField tfCity;
	private items item = new items();
	List<purchase_details> pur_detList;
	private JLabel lblSelectedModel;
	private JDateChooser dcPurchasedDate;
	private dialog_chooseItem dialog;
	/**
	 * Create the panel.
	 */
	
	//constructor
	public pnl_newPurchase() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnl_form = new JPanel();
		pnl_form.setBackground(Color.WHITE);
		add(pnl_form);
		GridBagLayout gbl_pnl_form = new GridBagLayout();
		gbl_pnl_form.columnWidths = new int[]{62, 67, 86, 28, 69, 55, 0};
		gbl_pnl_form.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0};
		gbl_pnl_form.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_form.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		pnl_form.setLayout(gbl_pnl_form);
		
		JLabel lblSupplierName = new JLabel(" Supplier name");
		GridBagConstraints gbc_lblSupplierName = new GridBagConstraints();
		gbc_lblSupplierName.weighty = 1.0;
		gbc_lblSupplierName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSupplierName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplierName.gridx = 0;
		gbc_lblSupplierName.gridy = 0;
		pnl_form.add(lblSupplierName, gbc_lblSupplierName);
		
		tfSupplierName = new JTextField();
		GridBagConstraints gbc_tfSupplierName = new GridBagConstraints();
		gbc_tfSupplierName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSupplierName.insets = new Insets(0, 0, 5, 5);
		gbc_tfSupplierName.gridx = 1;
		gbc_tfSupplierName.gridy = 0;
		pnl_form.add(tfSupplierName, gbc_tfSupplierName);
		tfSupplierName.setColumns(10);
		
		JLabel lblItem = new JLabel("Item");
		GridBagConstraints gbc_lblItem = new GridBagConstraints();
		gbc_lblItem.anchor = GridBagConstraints.WEST;
		gbc_lblItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblItem.gridx = 2;
		gbc_lblItem.gridy = 0;
		pnl_form.add(lblItem, gbc_lblItem);
		
		JButton btnChoose = new JButton("Choose...");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog = new dialog_chooseItem();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dialog.setVisible(false);				
						item = dialog.getItem();
						//System.out.println(item.toString());
						lblSelectedModel.setText(item.getModel().getModel_name());	
					}
				});
			}
		});
		btnChoose.setFocusable(false);
		btnChoose.setForeground(Color.WHITE);
		btnChoose.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.anchor = GridBagConstraints.WEST;
		gbc_btnChoose.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoose.gridx = 3;
		gbc_btnChoose.gridy = 0;
		pnl_form.add(btnChoose, gbc_btnChoose);
		
		lblSelectedModel = new JLabel("");
		GridBagConstraints gbc_lblSelectedModel = new GridBagConstraints();
		gbc_lblSelectedModel.gridwidth = 2;
		gbc_lblSelectedModel.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectedModel.anchor = GridBagConstraints.WEST;
		gbc_lblSelectedModel.gridx = 4;
		gbc_lblSelectedModel.gridy = 0;
		pnl_form.add(lblSelectedModel, gbc_lblSelectedModel);
		
		JLabel lblContactName = new JLabel("Contact name");
		GridBagConstraints gbc_lblContactName = new GridBagConstraints();
		gbc_lblContactName.weighty = 1.0;
		gbc_lblContactName.insets = new Insets(0, 0, 5, 5);
		gbc_lblContactName.gridx = 0;
		gbc_lblContactName.gridy = 1;
		pnl_form.add(lblContactName, gbc_lblContactName);
		
		tfContactName = new JTextField();
		GridBagConstraints gbc_tfContactName = new GridBagConstraints();
		gbc_tfContactName.anchor = GridBagConstraints.EAST;
		gbc_tfContactName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfContactName.insets = new Insets(0, 0, 5, 5);
		gbc_tfContactName.gridx = 1;
		gbc_tfContactName.gridy = 1;
		pnl_form.add(tfContactName, gbc_tfContactName);
		tfContactName.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 2;
		gbc_lblQuantity.gridy = 1;
		pnl_form.add(lblQuantity, gbc_lblQuantity);
		
		tfQuantity = new JTextField();
		GridBagConstraints gbc_tfQuantity = new GridBagConstraints();
		gbc_tfQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_tfQuantity.gridx = 3;
		gbc_tfQuantity.gridy = 1;
		pnl_form.add(tfQuantity, gbc_tfQuantity);
		tfQuantity.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.insets = new Insets(0, 0, 0, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 5;
		pnl_form.add(lblCity, gbc_lblCity);
		
		JLabel lblPurchasedDate = new JLabel("Purchased date");
		GridBagConstraints gbc_lblPurchasedDate = new GridBagConstraints();
		gbc_lblPurchasedDate.anchor = GridBagConstraints.WEST;
		gbc_lblPurchasedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblPurchasedDate.gridx = 2;
		gbc_lblPurchasedDate.gridy = 3;
		pnl_form.add(lblPurchasedDate, gbc_lblPurchasedDate);
		
		dcPurchasedDate = new JDateChooser();
		GridBagConstraints gbc_dcPurchasedDate = new GridBagConstraints();
		gbc_dcPurchasedDate.insets = new Insets(0, 0, 5, 5);
		gbc_dcPurchasedDate.fill = GridBagConstraints.BOTH;
		gbc_dcPurchasedDate.gridx = 3;
		gbc_dcPurchasedDate.gridy = 3;
		pnl_form.add(dcPurchasedDate, gbc_dcPurchasedDate);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		pnl_form.add(lblAddress, gbc_lblAddress);
		
		JLabel lblPhone = new JLabel(" Phone");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.weighty = 1.0;
		gbc_lblPhone.anchor = GridBagConstraints.WEST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 2;
		pnl_form.add(lblPhone, gbc_lblPhone);
		
		tfPhone = new JTextField();
		GridBagConstraints gbc_tfPhone = new GridBagConstraints();
		gbc_tfPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPhone.anchor = GridBagConstraints.WEST;
		gbc_tfPhone.insets = new Insets(0, 0, 5, 5);
		gbc_tfPhone.gridx = 1;
		gbc_tfPhone.gridy = 2;
		pnl_form.add(tfPhone, gbc_tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lblTotalAmount = new JLabel("Total amount");
		GridBagConstraints gbc_lblTotalAmount = new GridBagConstraints();
		gbc_lblTotalAmount.anchor = GridBagConstraints.WEST;
		gbc_lblTotalAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalAmount.gridx = 2;
		gbc_lblTotalAmount.gridy = 2;
		pnl_form.add(lblTotalAmount, gbc_lblTotalAmount);
		
		tfTotalAmount = new JTextField();
		GridBagConstraints gbc_tfTotalAmount = new GridBagConstraints();
		gbc_tfTotalAmount.insets = new Insets(0, 0, 5, 5);
		gbc_tfTotalAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTotalAmount.gridx = 3;
		gbc_tfTotalAmount.gridy = 2;
		pnl_form.add(tfTotalAmount, gbc_tfTotalAmount);
		tfTotalAmount.setColumns(10);
		
		JLabel lblEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.weighty = 1.0;
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		pnl_form.add(lblEmail, gbc_lblEmail);
		
		tfEmail = new JTextField();
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.insets = new Insets(0, 0, 5, 5);
		gbc_tfEmail.gridx = 1;
		gbc_tfEmail.gridy = 3;
		pnl_form.add(tfEmail, gbc_tfEmail);
		tfEmail.setColumns(10);
		
		taAddress = new JTextArea(1,2);
		taAddress.setLineWrap(true);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		pnl_form.add(scrollPane_1, gbc_scrollPane_1);
		scrollPane_1.setViewportView(taAddress);
		
		tfCity = new JTextField();
		GridBagConstraints gbc_tfCity = new GridBagConstraints();
		gbc_tfCity.insets = new Insets(0, 0, 0, 5);
		gbc_tfCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCity.gridx = 1;
		gbc_tfCity.gridy = 5;
		pnl_form.add(tfCity, gbc_tfCity);
		tfCity.setColumns(10);
		
		JButton btnClear = new JButton("clear all");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfSupplierName.setText("");
				tfContactName.setText("");
				tfPhone.setText("");
				taAddress.setText("");
				tfCity.setText("");
				tfQuantity.setText(""); 
				tfTotalAmount.setText("");
				tfEmail.setText("");
				dcPurchasedDate.setDate(null);
				lblSelectedModel.setText("");
				setEnableOnceFill(true);
				clearTable();
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFocusable(false);
		btnClear.setBackground(new Color(255, 51, 51));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 5;
		pnl_form.add(btnClear, gbc_btnClear);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isComplete()){
					setEnableOnceFill(false);
					addToPurchaseList();
				}
			}
		});
		btnAdd.setFocusable(false);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(new Color(0, 102, 255));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 5;
		pnl_form.add(btnAdd, gbc_btnAdd);
		
		JButton btnSaveAll = new JButton("Save all");
		btnSaveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveToDB();
			}
		});
		btnSaveAll.setFocusable(false);
		btnSaveAll.setBackground(Color.GREEN);
		GridBagConstraints gbc_btnSaveAll = new GridBagConstraints();
		gbc_btnSaveAll.gridx = 5;
		gbc_btnSaveAll.gridy = 5;
		pnl_form.add(btnSaveAll, gbc_btnSaveAll);
		pnl_form.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfSupplierName, tfContactName, tfPhone, tfEmail, taAddress, tfCity, btnChoose, tfQuantity, tfTotalAmount, dcPurchasedDate, btnAdd}));
		
		JPanel pnl_bottom = new JPanel();
		add(pnl_bottom);
		pnl_bottom.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnl_bottom.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Model", "Color", "Quantity", "Line amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(1).setPreferredWidth(77);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(58);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		pnl_bottom.add(panel, BorderLayout.SOUTH);
		
		pur_detList = new ArrayList<purchase_details>();

	}

	// adding to purchase list
	private void addToPurchaseList(){
		try{
			purchase_details pur_detail = new purchase_details();
			int ind=0;
			if(pur_detList.size() == 0) {
				pur_detail.setItem(item);
				pur_detail.setQuantity(Integer.parseInt(this.tfQuantity.getText()));
				pur_detail.setTotal_amount(Double.parseDouble(this.tfTotalAmount.getText()));
				pur_detList.add(pur_detail);
				addToTable();
			}
			else if(item==null){
				
			}
			else {
				ind = isContain(item,pur_detList);
				if(ind == -1){
					pur_detail.setItem(item);
					pur_detail.setQuantity(Integer.parseInt(this.tfQuantity.getText()));
					pur_detail.setTotal_amount(Double.parseDouble(this.tfTotalAmount.getText()));
					pur_detList.add(pur_detail);
					addToTable();
				}else{
					purchase_details purd = pur_detList.get(ind);
					int qty = purd.getQuantity() + Integer.parseInt(this.tfQuantity.getText());
					double amt = purd.getTotal_amount() + Double.parseDouble(this.tfTotalAmount.getText());
					purd.setQuantity(qty);
					purd.setTotal_amount(amt);
					pur_detList.add(ind, purd);
					table.setValueAt(qty, ind, 3);
					table.setValueAt(amt, ind, 4);
				}
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Quantity & Total Amount must be number");
		}
	}
	
	// searching item in purchase_details & return index
	private int isContain(items obj,List<purchase_details> list){
		for(int i=0;i<list.size();i++){
			if(obj == list.get(i).getItem()){
				return i;
			}
		}
		return -1;
	}
	
	private boolean isComplete(){
		boolean valid = true;
		valid = tfSupplierName.getText()!="";
		valid = tfContactName.getText()!="";
		valid = tfPhone.getText()!="";
		valid = tfEmail.getText()!="";
		valid = taAddress.getText()!="";
		valid = tfCity.getText()!="";
		valid = tfQuantity.getText()!="";
		valid = tfTotalAmount.getText()!="";
		valid = item.getItem_id()!=0;
		valid = dcPurchasedDate.getDate()!=null;
		
		if(!valid){
			JOptionPane.showMessageDialog(null, "Cannot add! Please fill complete");
		} else{
			try {			
				validate.isName(tfContactName.getText());
				validate.isPhone(tfPhone.getText());
				validate.isEmail(tfEmail.getText());
				validate.isCity(tfCity.getText());				
				return true;
			}
			catch(IllegalArgumentException iae){
				JOptionPane.showMessageDialog(this, iae.getMessage() , "Invalid",JOptionPane.ERROR_MESSAGE);
				System.out.println(iae.getMessage());
				return false;
			}		
		}		
		
		return valid;
	}
	// save data to database
	private void saveToDB(){
		if(!pur_detList.isEmpty()) {
			suppliersDAO.insertSuppliers(this.getSupplier());
			purchaseDAO.insertPurchase(this.getPurchase());
			System.out.println(getPurchase().getPurchased_date());
			for(purchase_details pur_det:pur_detList){
				items itm = pur_det.getItem();
				itm.setStock_quantity(itm.getStock_quantity() + pur_det.getQuantity());
				itemsDAO.updateItems(item);
				pur_det.setPurchase(this.getPurchase());
				purchase_detailsDAO.insertPurchaseDetails(pur_det);
			}
			pur_detList = new ArrayList<purchase_details>();
			
			tfSupplierName.setText("");
			tfContactName.setText("");
			tfPhone.setText("");
			taAddress.setText("");
			tfEmail.setText("");
			tfCity.setText("");
			tfQuantity.setText(""); 
			tfTotalAmount.setText("");
			lblSelectedModel.setText("");
			dcPurchasedDate.setDate(null);
			clearTable();
			setEnableOnceFill(true);
			JOptionPane.showMessageDialog(null, "Completely saved");
		}
		else{
			JOptionPane.showMessageDialog(null, "Cannot save!");
		}
	}
	
	// setting once textfield enable or disable
	private void setEnableOnceFill(boolean isAllow){
		tfSupplierName.setEditable(isAllow);
		tfContactName.setEditable(isAllow);
		tfPhone.setEditable(isAllow);
		taAddress.setEditable(isAllow);
		tfEmail.setEditable(isAllow);
		tfCity.setEditable(isAllow);
		dcPurchasedDate.setEnabled(isAllow);
	}

	// get purchase
	purchase purchase = new purchase();
	private purchase getPurchase(){ 	
		purchase.setSupplier(this.getSupplier());
		Date date = dcPurchasedDate.getDate();
		purchase.setPurchased_date(date);
		return purchase;
	}
	
	// get supplier 
	suppliers sup = new suppliers();
	private suppliers getSupplier(){
		
		sup.setSupplier_name(tfSupplierName.getText());
		sup.setContact_name(tfContactName.getText());
		sup.setPhone(tfPhone.getText());
		sup.setAddress(taAddress.getText());
		sup.setCity(tfCity.getText());
		return sup;
	}
	
	private void clearTable(){
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		
		for (int i=rowCount-1; i>=0; i--) {
		    dm.removeRow(i);
		    table.revalidate();
		}
	}
	// add to table
	private void addToTable(){
		
		if(pur_detList.size()==0){
			//do nothing
		}
		else{	
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int rowCount = model.getRowCount();
			
			for(int i=rowCount-1;i>=0;i--){
				model.removeRow(i);
				table.revalidate();
			}
			for(int i=0;i<pur_detList.size();i++){
				purchase_details pur = pur_detList.get(i);
				model.addRow(new Object[]{(i+1),pur.getItem().getModel().getModel_name(),pur.getItem().getColor(),pur.getQuantity(),pur.getTotal_amount()});
			}
		}
	}
}