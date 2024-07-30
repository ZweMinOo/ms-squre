package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.dao;

import pojo.services;
import pojo.services_details;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;

public class dialog_addAccessories extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea taError;
	private JTable table;
	private JTextField tfName;
	private JTextField tfQuantity;
	private JTextField tfPrice;
	private JButton btnAdd;
	private JLabel lblServiceNo,lblCustomerName, lblBAM;
	private List<services_details> sdList = new ArrayList<services_details>();
	private List<services_details> sdListTs = new ArrayList<services_details>();
	private services service;
	JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			/*dialog_addAccessories dialog = new dialog_addAccessories();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public dialog_addAccessories(int sNo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_addAccessories.class.getResource("/images/addAccessories.png")));
		setResizable(false);
		setTitle("Add Accessories");
		setBounds(100, 100, 458, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.NORTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				lblServiceNo = new JLabel("Service no :");
				GridBagConstraints gbc_lblServiceNo = new GridBagConstraints();
				gbc_lblServiceNo.anchor = GridBagConstraints.WEST;
				gbc_lblServiceNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblServiceNo.gridx = 0;
				gbc_lblServiceNo.gridy = 0;
				panel.add(lblServiceNo, gbc_lblServiceNo);
			}
			{
				lblCustomerName = new JLabel("Customer name : ");
				GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
				gbc_lblCustomerName.anchor = GridBagConstraints.WEST;
				gbc_lblCustomerName.insets = new Insets(0, 0, 5, 0);
				gbc_lblCustomerName.gridx = 1;
				gbc_lblCustomerName.gridy = 0;
				panel.add(lblCustomerName, gbc_lblCustomerName);
			}
			{
				lblBAM = new JLabel("Model : ");
				GridBagConstraints gbc_lblModel = new GridBagConstraints();
				gbc_lblModel.anchor = GridBagConstraints.WEST;
				gbc_lblModel.insets = new Insets(0, 0, 5, 5);
				gbc_lblModel.gridx = 0;
				gbc_lblModel.gridy = 1;
				panel.add(lblBAM, gbc_lblModel);
			}
			{
				JLabel lblError = new JLabel("Error");
				GridBagConstraints gbc_lblError = new GridBagConstraints();
				gbc_lblError.anchor = GridBagConstraints.WEST;
				gbc_lblError.insets = new Insets(0, 0, 5, 0);
				gbc_lblError.gridx = 0;
				gbc_lblError.gridy = 2;
				panel.add(lblError, gbc_lblError);
			}
			{
				taError = new JTextArea();
				taError.setEditable(false);
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 2;
				panel.add(scrollPane, gbc_scrollPane);
				scrollPane.setViewportView(taError);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(3, 3, 5, 5));
				{
					JLabel lblItemName = new JLabel("  Item name");
					lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
					panel_1.add(lblItemName);
				}
				{
					tfName = new JTextField();
					panel_1.add(tfName);
					tfName.setColumns(10);
					panel_1.add(new JLabel());
				}
				{
					JLabel lblQuantity = new JLabel("  Quantity");
					lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
					panel_1.add(lblQuantity);
				}
				{
					tfQuantity = new JTextField();
					panel_1.add(tfQuantity);
					tfQuantity.setColumns(10);
					panel_1.add(new JLabel());
				}
				{
					JLabel lblPrice = new JLabel("  Price");
					lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
					panel_1.add(lblPrice);
				}
				{
					tfPrice = new JTextField();
					panel_1.add(tfPrice);
					tfPrice.setColumns(10);
				}
				{
					btnAdd = new JButton("Add");
					btnAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							addToTable();
							addToList();
						}
					});
					btnAdd.setBackground(new Color(0, 153, 204));
					panel_1.add(btnAdd);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					panel_1.add(scrollPane);
					{
						table = new JTable();
						table.setRowHeight(20);
						table.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Line No", "Item name", "Quantity", "Price", "Line amount"
							}
						));
						scrollPane.setViewportView(table);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setBackground(Color.GREEN);
				btnSave.setActionCommand("Save");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfName, tfQuantity, tfPrice, btnAdd}));
		System.out.print("___________" + sNo);
		String query = "FROM services WHERE service_id = '" + sNo + "'";
		service = servicesDAO.getAllServices(query).get(0);
		
		if(service!=null){
			System.out.println("IT's ok");
		}
		query = "FROM services_details WHERE service_id = '" + sNo + "'";
		sdList = services_detailsDAO.getAllServicesDetails(query);
		
		if(service!=null){
			int serNo = service.getService_id();
			String cName = service.getCustomer().getCustomer_name();
			String bAm = service.getModel_name();
			String error = service.getError();
			
			lblServiceNo.setText(lblServiceNo.getText() + " " + serNo);
			lblCustomerName.setText(lblCustomerName.getText() + " " + cName);
			lblBAM.setText(lblBAM.getText() + " " + bAm);
			taError.setText(error);
		}
		if(sdList.size() != 0) {		
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=0;i<sdList.size();i++){
				services_details temp = sdList.get(i);
				model.addRow(new String[]{(i+1)+"", temp.getAccessory_name(), temp.getQuantity()+"", temp.getPrice()+"", (temp.getQuantity()*temp.getPrice())+""});
			}
		}
	}
	
	private String[] getAddedData(){ // get filled data
		String [] data = new String[3];
		if(tfName.getText() == ""){
			JOptionPane.showMessageDialog(null, "Item name cannot be empty");
		}
		else if(tfPrice.getText() == ""){
			JOptionPane.showMessageDialog(null, "Price cannot be empty");
		}
		else if(tfQuantity.getText() == ""){
			JOptionPane.showMessageDialog(null, "Quantity cannot be empty");
		}
		else {		
			try{
				System.out.println("PRICE : " + tfPrice.getText());
				if((Integer.parseInt(tfPrice.getText()) < 1) || (Integer.parseInt(tfQuantity.getText()) < 1)){
					JOptionPane.showMessageDialog(null, "Price and quantity cannot be least than 1");
				}
				else{
					data[0] = this.tfName.getText();
					data[1] = this.tfPrice.getText();
					data[2] = this.tfQuantity.getText();
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Price and quantity must be number");
			}
		}
		return data;
	}
	
	private void addToTable(){ // add a row to table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int i = model.getRowCount()+1;
		model.addRow(new String[]{i+"", getAddedData()[0], getAddedData()[1], getAddedData()[2], (Double.parseDouble(getAddedData()[1])*Integer.parseInt(getAddedData()[2]))+""});
	}
	
	private void addToList(){ // add to list
		services_details serDet = new services_details();
		serDet.setService(service);
		String [] dat = getAddedData();
		serDet.setAccessory_name(dat[0]);
		serDet.setPrice(Double.parseDouble(dat[1]));
		serDet.setQuantity(Integer.parseInt(dat[2]));
		
		sdListTs.add(serDet);
	}
	
	public void saveToDB() { // save to database
		for(int i=0;i<sdListTs.size();i++){
			services_details temp = sdListTs.get(i);
			services_detailsDAO.insertServicesDetails(temp);
		}
		JOptionPane.showMessageDialog(null, "Saved successfully");
	}
}