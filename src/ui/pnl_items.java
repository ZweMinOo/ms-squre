package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.items;

import dao.dao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_items extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private dialog_newItem dialog_newItem;
	private dialog_itemsFilter dialog_itemsFilter = new dialog_itemsFilter();
	/**
	 * Create the panel.
	 */
	public pnl_items() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewItem = new JButton("New item");
		btnNewItem.setIcon(new ImageIcon(pnl_items.class.getResource("/images/new.png")));
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog_newItem = new dialog_newItem();
				dialog_newItem.setLocationRelativeTo(null);
				dialog_newItem.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog_newItem.setVisible(true);
				dialog_newItem.btnSave.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							items item = new items();
							item = dialog_newItem.getNewItem();
							itemsDAO.insertItems(item);
							loadToTable("None");
							JOptionPane.showMessageDialog(null, "Saved");
						}catch(NullPointerException npe){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}
						dialog_newItem.dispose();
					}
				});
			}
		});
		btnNewItem.setBackground(Color.WHITE);
		btnNewItem.setFocusable(false);
		panel.add(btnNewItem);
		
		JButton btnEditItem = new JButton("Edit item");
		btnEditItem.setIcon(new ImageIcon(pnl_items.class.getResource("/images/edit.png")));
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_editItem dialog = new dialog_editItem();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.btnSave.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							items item = new items();
							item = dialog.getEditedItem();
							itemsDAO.updateItems(item);
							loadToTable("None");
							JOptionPane.showMessageDialog(null, "Saved");
						}catch(NullPointerException npe){
							//JOptionPane.showMessageDialog(null, "Please fill all blank", "Incomplete saving",JOptionPane.ERROR_MESSAGE);
						}catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(null, "Please quantity & price must be number!", "Incomplete saving",JOptionPane.ERROR_MESSAGE);
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(null,"Try again!" , "Incomplete saving",JOptionPane.ERROR_MESSAGE);
						}
						dialog.dispose();
					}
				});
			}
		});
		btnEditItem.setBackground(Color.WHITE);
		btnEditItem.setFocusable(false);
		panel.add(btnEditItem);
		
		JButton btnFliters = new JButton("Fliters");
		btnFliters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog_itemsFilter.setLocationRelativeTo(null);
				dialog_itemsFilter.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				dialog_itemsFilter.setVisible(true);
				dialog_itemsFilter.btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(dialog_itemsFilter.rdbtnReachedReorder.isSelected()) {
							loadToTable("Reorder");
						}
						else {
							loadToTable("None");
						}
						dialog_itemsFilter.setVisible(false);
					}
				});
			}
		});
		btnFliters.setIcon(new ImageIcon(pnl_items.class.getResource("/images/filter.png")));
		btnFliters.setBackground(Color.WHITE);
		btnFliters.setFocusable(false);
		panel.add(btnFliters);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item No", "Model", "Color", "Price", "Qty", "Brand", "Category", "Reorder lvl"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(65);
		table.setBackground(Color.WHITE);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		loadToTable("None");
	}
	
	private void loadToTable(String filter){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		
		if(filter.equals("None")) {
			for(items item: itemsDAO.getAllItems("FROM items")){
				model.addRow(new Object[]{
						item.getItem_id(),
						item.getModel().getModel_name(),
						item.getColor(),
						item.getUnit_price(),
						item.getStock_quantity(),
						item.getModel().getCategories_brands().getBrand().getBrand_name(),
						item.getModel().getCategories_brands().getCategory().getCategory_name(),
						item.getReorder_level()
					});
			}
		}else if(filter.equals("Reorder")){
			for(items item: itemsDAO.getAllItems("FROM items")){
				if(item.getStock_quantity() <= item.getReorder_level()) {
					model.addRow(new Object[]{
							item.getItem_id(),
							item.getModel().getModel_name(),
							item.getColor(),
							item.getUnit_price(),
							item.getStock_quantity(),
							item.getModel().getCategories_brands().getBrand().getBrand_name(),
							item.getModel().getCategories_brands().getCategory().getCategory_name(),
							item.getReorder_level()
						});
				}
			}
		}
	}
}