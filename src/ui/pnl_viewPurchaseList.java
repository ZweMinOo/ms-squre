package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import pojo.purchase_details;

import dao.dao;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pnl_viewPurchaseList extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public pnl_viewPurchaseList() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Supplier name", "Contact name", "Model", "Color", "Qty", "Purchased date", "Total amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(62);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		loadToTable();
	}
	
	public void loadToTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		int i=1;
		for(purchase_details purDet : purchase_detailsDAO.getAllPurchaseDetails("FROM purchase_details")){
			
			model.addRow(
				new Object[]{
						i++,
						purDet.getPurchase().getSupplier().getSupplier_name(),
						purDet.getPurchase().getSupplier().getContact_name(),
						purDet.getItem().getModel().getModel_name(),
						purDet.getItem().getColor(),
						purDet.getQuantity(),
						purDet.getPurchase().getPurchased_date(),
						purDet.getTotal_amount()
			});
		}
	}
}