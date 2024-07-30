package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import dao.dao;
import pojo.categories_brands;
import pojo.models;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;

public class pnl_models extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	models model;
	/**
	 * Create the panel.
	 */
	public pnl_models() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewModel = new JButton("New model");
		btnNewModel.setIcon(new ImageIcon(pnl_models.class.getResource("/images/new.png")));
		btnNewModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_newModel dialog = new dialog_newModel();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.btnSave.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try{
							boolean isExist = false;
							categories_brands cat_bnd = new categories_brands();
							for(categories_brands cb : categories_brandsDAO.getAllCategoriesBrands("FROM categories_brands")) {
								if(cb.getBrand().getBrand_id()==dialog.getBrand().getBrand_id()&&
										cb.getCategory().getCategory_id()==dialog.getCategory().getCategory_id()){
									cat_bnd = cb;
									isExist = true;
									break;
								}
							}
							if(!isExist){
								cat_bnd.setCategory(dialog.getCategory());
								cat_bnd.setBrand(dialog.getBrand());
								categories_brandsDAO.insertCategoriesBrands(cat_bnd);
							}
							model = dialog.getModel();
							System.out.println(model.getModel_name());
							if(model.getModel_name()!=null) {
								model.setCategories_brands(cat_bnd);
								modelsDAO.insertModels(model);
								loadToTable();
								JOptionPane.showMessageDialog(null, "Saved");
								dialog.dispose();
							}
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}						
					}				
				});
			}
		});
		btnNewModel.setBackground(Color.WHITE);
		btnNewModel.setFocusable(false);
		panel.add(btnNewModel);
		
		JButton btnEditModel = new JButton("Edit model");
		btnEditModel.setIcon(new ImageIcon(pnl_models.class.getResource("/images/edit.png")));
		btnEditModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_editModel dialog = new dialog_editModel();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.btnSave.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try{
							models model = dialog.getEditedModel();
							modelsDAO.updateModels(model);
							loadToTable();
							JOptionPane.showMessageDialog(null, "Saved");
						}catch(NullPointerException npe){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}
						dialog.dispose();
					}
				});
			}
		});
		btnEditModel.setBackground(Color.WHITE);
		btnEditModel.setFocusable(false);
		panel.add(btnEditModel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Model", "Brand", "Category"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(115);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.setBackground(Color.WHITE);
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
		
		List<models> modList =modelsDAO.getAllModels("FROM models");
		for(int i=0;i<modList.size();i++){
			models mod = modList.get(i);
			categories_brands cnb = mod.getCategories_brands();
			model.addRow(new Object[]{mod.getModel_id(),mod.getModel_name(),cnb.getBrand().getBrand_name(),cnb.getCategory().getCategory_name()});
		}
	}
}