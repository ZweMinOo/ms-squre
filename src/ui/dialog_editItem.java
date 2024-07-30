package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import dao.dao;
import pojo.brands;
import pojo.categories;
import pojo.categories_brands;
import pojo.items;
import pojo.models;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class dialog_editItem extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cbBCategories,cbBBrands,cbBModels,cbBColors;
	JButton btnSave;
	private JTextField tfUnitPrice;
	private JTextField tfStockQty;
	private JTextField tfReorderLevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_editItem dialog = new dialog_editItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_editItem() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_editItem.class.getResource("/images/edit.png")));
		setTitle("Edit Item");
		setBounds(100, 100, 301, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(7, 2, 5, 5));
		{
			JLabel lblCategory = new JLabel("Category");
			lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblCategory);
		}
		{
			cbBCategories = new JComboBox<String>();
			cbBCategories.setFont(new Font("Tahoma", Font.PLAIN, 12));
			//add category names to cbBCategories
			for(categories cat : categoriesDAO.getAllCategories("FROM categories")){
				cbBCategories.addItem(cat.getCategory_name());
			}
			cbBCategories.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					addModels();
				}
			});
			cbBCategories.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					addModels();
				}
				
			});
			contentPanel.add(cbBCategories);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblBrand);
		}
		{
			cbBBrands = new JComboBox<String>();
			cbBBrands.setFont(new Font("Tahoma", Font.PLAIN, 12));
			for(brands bnd : brandsDAO.getAllBrands("FROM brands")){
				cbBBrands.addItem(bnd.getBrand_name());
			}
			cbBBrands.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					addModels();
				}
				
			});
			contentPanel.add(cbBBrands);
		}
		{
			JLabel lblModel = new JLabel("Choose Model");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblModel);
		}
		{
			cbBModels = new JComboBox<String>();
			cbBModels.setFont(new Font("Tahoma", Font.PLAIN, 12));
			for(models mod : modelsDAO.getAllModels("FROM models")){
				cbBModels.addItem(mod.getModel_name()+"");
			}
			cbBModels.addActionListener(new ActionListener(){		
				@Override
				public void actionPerformed(ActionEvent arg0) {
					addColors();
				}
				
			});
			contentPanel.add(cbBModels);
		}
		{
			JLabel lblColor = new JLabel("Color");
			lblColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblColor);
		}
		{
			cbBColors = new JComboBox<String>();
			cbBColors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbBColors.setEnabled(false);
			contentPanel.add(cbBColors);
		}
		{
			JLabel lblUnitPrice = new JLabel("Unit Price");
			lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblUnitPrice);
		}
		{
			tfUnitPrice = new JTextField();
			tfUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfUnitPrice.setEditable(false);
			contentPanel.add(tfUnitPrice);
			tfUnitPrice.setColumns(10);
		}
		{
			JLabel lblStockQty = new JLabel("Stock Quantity");
			lblStockQty.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblStockQty);
		}
		{
			tfStockQty = new JTextField();
			tfStockQty.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfStockQty.setEditable(false);
			contentPanel.add(tfStockQty);
			tfStockQty.setColumns(10);
		}
		{
			JLabel lblReorderLevel = new JLabel("Reorder level");
			lblReorderLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblReorderLevel);
		}
		{
			tfReorderLevel = new JTextField();
			tfReorderLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfReorderLevel.setEditable(false);
			contentPanel.add(tfReorderLevel);
			tfReorderLevel.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setForeground(Color.WHITE);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCancel.setBackground(Color.RED);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel, BorderLayout.WEST);
			}
			{
				btnSave = new JButton("Save");
				btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSave.setBackground(Color.GREEN);
				buttonPane.add(btnSave, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSave);
			}
		}
	}
	
	//add color names according to model
	private void addColors(){
		cbBColors.removeAllItems();
		cbBColors.revalidate();
		boolean isOk = false;
		for(items item : itemsDAO.getAllItems("FROM items")){
			if(item.getModel().getModel_name().equals(cbBModels.getSelectedItem()+"")){
				cbBColors.addItem(item.getColor());
				tfUnitPrice.setText(item.getUnit_price()+"");
				tfStockQty.setText(item.getStock_quantity()+"");
				tfReorderLevel.setText(item.getReorder_level()+"");
				isOk = true;
				break;
			}
		}
		if(!isOk) {
			cbBColors.removeAllItems();
			tfUnitPrice.setText("");
			tfStockQty.setText("");
			tfReorderLevel.setText("");
		}
		cbBColors.setEnabled(isOk);
		tfUnitPrice.setEditable(isOk);
		tfStockQty.setEditable(isOk);
		tfReorderLevel.setEditable(isOk);
	}
	//add model names according to category and brand
	private void addModels(){
		cbBModels.removeAllItems();
		cbBModels.revalidate();
		for(models model : modelsDAO.getAllModels("FROM models")){
			categories_brands cb = model.getCategories_brands();
			if(cb.getBrand().getBrand_name().equals(cbBBrands.getSelectedItem()+"")&&
				cb.getCategory().getCategory_name().equals(cbBCategories.getSelectedItem()+"")){
					cbBModels.addItem(model.getModel_name());
			}
		}
	}
		
		//get edited model
	items item;
	models model;
	public items getEditedItem() throws NumberFormatException{
		item = new items();
		for(models model : modelsDAO.getAllModels("FROM models")){
			categories_brands cb = model.getCategories_brands();
			if(cb.getBrand().getBrand_name().equals(cbBBrands.getSelectedItem()+"")&&
				cb.getCategory().getCategory_name().equals(cbBCategories.getSelectedItem()+"")&&
				model.getModel_name().equals(cbBModels.getSelectedItem()+"")){
				this.model = model;
				break;
			}
		}
		
		
		for(items item : itemsDAO.getAllItems("FROM items")){
			System.out.println(item.getModel().getCategories_brands().getCategories_brands_id() + " : " + model.getCategories_brands().getCategories_brands_id());
			//System.out.println(cbBColors.getSelectedItem().equals(item.getColor()));
			if(item.getModel().getModel_id() == model.getModel_id() && cbBColors.getSelectedItem().equals(item.getColor())){
				this.item = item;
				break;
			}
		}
		item.setStock_quantity(Integer.parseInt(this.tfStockQty.getText()));
		item.setUnit_price(Double.parseDouble(this.tfUnitPrice.getText()));
		int rLvl = Integer.parseInt(this.tfReorderLevel.getText());
		if(rLvl<0){
			rLvl = 0;
		}
		else {
			item.setReorder_level(rLvl);
		}
		return item;
	}
}