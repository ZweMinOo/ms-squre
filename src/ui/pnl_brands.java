package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import pojo.brands;
import dao.dao;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_brands extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public pnl_brands() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewBrand = new JButton("New Brand");
		btnNewBrand.setIcon(new ImageIcon(pnl_brands.class.getResource("/images/new.png")));
		btnNewBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try{
					String brandName = JOptionPane.showInputDialog("Fill brand name");
					if(!brandName.equals("")){
						brands brand = new brands();
						brand.setBrand_name(brandName);
						brandsDAO.insertBrands(brand);
						JOptionPane.showMessageDialog(null, "Saved");
						loadToTable();
					}
					else{
						JOptionPane.showMessageDialog(null, "Brand name cannot be blank","Incomplete saving!",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e){
					//JOptionPane.showMessageDialog(null, "Brand name cannot be blank","Incomplete saving!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewBrand.setBackground(Color.WHITE);
		btnNewBrand.setFocusable(false);
		panel.add(btnNewBrand);
		
		JButton btnEditBrand = new JButton("Edit Brand");
		btnEditBrand.setIcon(new ImageIcon(pnl_brands.class.getResource("/images/edit.png")));
		btnEditBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_editBrand dialog = new dialog_editBrand();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);	
				dialog.btnSave.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						if(dialog.getBrand()==null){							
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}
						else{
							brandsDAO.updateBrands(dialog.getBrand());
							dialog.dispose();
							loadToTable();
							JOptionPane.showMessageDialog(null, "Saved");
						}
					}
					
				});
			}
		});
		btnEditBrand.setFocusable(false);
		btnEditBrand.setBackground(Color.WHITE);
		panel.add(btnEditBrand);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Brand"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		loadToTable();
	}
	
	private void loadToTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}	
		
		for(brands brn : brandsDAO.getAllBrands("FROM brands")){
			model.addRow(new Object[]{brn.getBrand_id(),brn.getBrand_name()});
		}
	}
}