package ui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import dao.dao;
import pojo.*;

public class pnl_newSale extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfQuantity;
	private customers customer;
	private sales sale;
	private JDateChooser dcSalesDate;
	private List<sales_details> sdList = new ArrayList<sales_details>();
	private final dialog_chooseItem dialogCI = new dialog_chooseItem();
	private JLabel lblSelected,lblCustomerName;
	private items item = new items();
	
	/**
	 * Create the panel.
	 */
	public pnl_newSale() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {90, 90, 90, 90, 90, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCustomerInfo = new JLabel("Customer info");
		GridBagConstraints gbc_lblCustomerInfo = new GridBagConstraints();
		gbc_lblCustomerInfo.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerInfo.fill = GridBagConstraints.VERTICAL;
		gbc_lblCustomerInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerInfo.gridx = 0;
		gbc_lblCustomerInfo.gridy = 0;
		panel.add(lblCustomerInfo, gbc_lblCustomerInfo);
		
		JButton btnFill = new JButton("Fill");
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_customerInfo customerInfo = new dialog_customerInfo();
				customerInfo.setVisible(true);
				customer = new customers();
				customerInfo.btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						customer = customerInfo.getCustomer();
						if(customer!=null) {
							System.out.println("D");
							lblCustomerName.setText("Customer name - " + customer.getCustomer_name());
							//customerInfo.setVisible(false);
							customerInfo.dispose();
						}
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
		btnFill.setFocusable(false);
		panel.add(btnFill, gbc_btnFill);
		
		JLabel lblItem = new JLabel("Item");
		GridBagConstraints gbc_lblItem = new GridBagConstraints();
		gbc_lblItem.anchor = GridBagConstraints.WEST;
		gbc_lblItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblItem.gridx = 2;
		gbc_lblItem.gridy = 0;
		panel.add(lblItem, gbc_lblItem);
		
		JButton btnChoose = new JButton("Choose..");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogCI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogCI.setVisible(true);
				dialogCI.btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dialogCI.setVisible(false);				
						item = dialogCI.getItem();
						lblSelected.setText(item.getModel().getModel_name());	
					}
				});
			}
		});
		btnChoose.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChoose.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoose.gridx = 3;
		gbc_btnChoose.gridy = 0;
		btnChoose.setFocusable(false);
		panel.add(btnChoose, gbc_btnChoose);
		
		lblSelected = new JLabel("");
		GridBagConstraints gbc_lblSelected = new GridBagConstraints();
		gbc_lblSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelected.gridx = 4;
		gbc_lblSelected.gridy = 0;
		panel.add(lblSelected, gbc_lblSelected);
		
		JLabel lblSalesDate = new JLabel("Sales date");
		GridBagConstraints gbc_lblSalesDate = new GridBagConstraints();
		gbc_lblSalesDate.anchor = GridBagConstraints.WEST;
		gbc_lblSalesDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalesDate.gridx = 0;
		gbc_lblSalesDate.gridy = 1;
		panel.add(lblSalesDate, gbc_lblSalesDate);
		
		dcSalesDate = new JDateChooser();
		GridBagConstraints gbc_dcSalesDate = new GridBagConstraints();
		gbc_dcSalesDate.anchor = GridBagConstraints.BASELINE;
		gbc_dcSalesDate.insets = new Insets(0, 0, 5, 5);
		gbc_dcSalesDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_dcSalesDate.gridx = 1;
		gbc_dcSalesDate.gridy = 1;
		panel.add(dcSalesDate, gbc_dcSalesDate);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 2;
		gbc_lblQuantity.gridy = 1;
		panel.add(lblQuantity, gbc_lblQuantity);
		
		tfQuantity = new JTextField();
		GridBagConstraints gbc_tfQuantity = new GridBagConstraints();
		gbc_tfQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_tfQuantity.gridx = 3;
		gbc_tfQuantity.gridy = 1;
		panel.add(tfQuantity, gbc_tfQuantity);
		tfQuantity.setColumns(10);
		
		lblCustomerName = new JLabel("Customer name - ");
		GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
		gbc_lblCustomerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCustomerName.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustomerName.gridwidth = 2;
		gbc_lblCustomerName.gridx = 0;
		gbc_lblCustomerName.gridy = 2;
		panel.add(lblCustomerName, gbc_lblCustomerName);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int qty = Integer.parseInt(tfQuantity.getText());
					if(dialogCI.getItem()!=null){
						if(sdList.size()==0){
							sales_details sales_detail = new sales_details();
							items item = dialogCI.getItem();
							sales_detail.setItem(item);
							sales_detail.setQuantity(qty);
							sales_detail.setPrice(item.getUnit_price());
							sdList.add(sales_detail);	
							addToTable();
						}
						else{
							boolean isContinue = true;
							// check same item contain in sdList?
							for(int i=0;i<sdList.size();i++){
								if(sdList.get(i).getItem()==dialogCI.getItem()){
									sales_details tmp = sdList.get(i);
									tmp.setQuantity(qty + tmp.getQuantity());
									sdList.set(i, tmp);
									isContinue = false;
									i = sdList.size(); // stop looping
								}
							}
							
							if(isContinue==true){
								sales_details sales_detail = new sales_details();
								items item = dialogCI.getItem();
								sales_detail.setItem(item);
								sales_detail.setQuantity(qty);
								sales_detail.setPrice(item.getUnit_price());
								sdList.add(sales_detail);
								//addToTable();
							}
							addToTable();
						}	
					}
					else{
						JOptionPane.showMessageDialog(null, "Please choose an item");
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Quantity must be number");
				}
			}
		});
		btnAdd.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 2;
		btnAdd.setFocusable(false);
		panel.add(btnAdd, gbc_btnAdd);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveToDB(); //save data to database
			}
		});
		btnSell.setBackground(new Color(0, 255, 51));
		GridBagConstraints gbc_btnSell = new GridBagConstraints();
		gbc_btnSell.insets = new Insets(0, 0, 0, 5);
		gbc_btnSell.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSell.gridx = 5;
		gbc_btnSell.gridy = 2;
		btnSell.setFocusable(false);
		panel.add(btnSell, gbc_btnSell);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNotice = new JLabel("Notice - You need to fill customer info and sales date only one times");
		lblNotice.setForeground(Color.GREEN);
		panel_1.add(lblNotice, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Model", "Color", "Price", "Qty", "Line amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
	}
	
	private void addToTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			//rowCount--;
			table.revalidate();
		}
		
		for(int i=0;i<sdList.size();i++){
			sales_details ser = sdList.get(i);
			model.addRow(
				new Object[]{
					(i+1), // line no
					ser.getItem().getModel().getModel_name(), // model name
					ser.getItem().getColor(), // color
					ser.getPrice(), // price
					ser.getQuantity(), // quantity
					ser.getPrice() * ser.getQuantity() // line amount
			});
			System.out.println((i+1) + " " + ser.getItem().getModel().getModel_name() + " " + 
			ser.getItem().getColor() + " " +  ser.getPrice()+ " " + 
			ser.getQuantity() +  " " +  ser.getPrice() * ser.getQuantity());
		}
		
	}
	
	private void clearTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
	}
	
	private void saveToDB(){
		if(sdList.size()==0){
			JOptionPane.showMessageDialog(null, "No item added");
		}
		else{
			customersDAO.insertCustomers(customer);
			sale = new sales();
			sale.setCustomer(customer);
			sale.setDate(dcSalesDate.getDate());
			salesDAO.insertSales(sale);
			items item = new items();
			for(sales_details temp : sdList){
				temp.setSale(sale);
				sales_detailsDAO.insertSalesDetails(temp);
				item = temp.getItem();
				int qty = item.getStock_quantity() - temp.getQuantity();
				item.setStock_quantity(qty);
				itemsDAO.updateItems(item);
			}
			
			sdList = new ArrayList<sales_details>();
			customer = null;
			sale = null;
			clearTable();
			lblCustomerName.setText("Customer name - ");
			dialogCI.dispose();
			JOptionPane.showMessageDialog(null, "Completely saved");
		}
	}
}