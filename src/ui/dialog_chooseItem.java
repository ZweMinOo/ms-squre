package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import dao.dao;

import pojo.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Toolkit;
import java.awt.Font;

public class dialog_chooseItem extends JDialog implements dao{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<categories> categoriesList;
	//private List<categories_brands>  categories_brandsList;
	private List<brands> brandsList;
	private List<models> modelsList;
	private List<items> itemsList;
	private models model = new models();
	private items item = new items();
	JButton btnOK;
	private JComboBox<String> cbBCategory = new JComboBox<String>(),
			cbBBrand = new JComboBox<String>(),
			cbBModel= new JComboBox<String>(),
			cbBColor = new JComboBox<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_chooseItem dialog = new dialog_chooseItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_chooseItem() {
		setTitle("Choose Item");
		categoriesList = categoriesDAO.getAllCategories("FROM categories");
		//categories_brandsList = categories_brandsDAO.getAllCategoriesBrands("FROM categories_brands");
		modelsList = modelsDAO.getAllModels("FROM models");
		itemsList = itemsDAO.getAllItems("FROM items");
		brandsList = brandsDAO.getAllBrands("FROM brands");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_chooseItem.class.getResource("/images/addAccessories.png")));
		setResizable(false);
		setBounds(100, 100, 329, 217);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 5, 5));
		{
			JLabel lblCategory = new JLabel("Category");
			lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblCategory);
		}
		{
			cbBCategory = new JComboBox<String>();
			cbBCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addToCbBCategory();
			contentPanel.add(cbBCategory);			
			cbBCategory.addActionListener(new ActionListener(){
				//@Override
				public void actionPerformed(ActionEvent arg0) {	
					System.out.println("WORKED");
					addToCbBModel();
					setSelected();
				}	
			});
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblBrand);
		}
		{
			cbBBrand = new JComboBox<String>();
			cbBBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addToCbBBrand();			
			contentPanel.add(cbBBrand);			
			cbBBrand.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {		
					addToCbBModel();
					setSelected();
				}	
			});
		}
		{
			JLabel lblModel = new JLabel("Model");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblModel);
		}
		{
			cbBModel = new JComboBox<String>();
			cbBModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addToCbBModel();
			contentPanel.add(cbBModel);
			cbBModel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					addToCbBColor();
					setSelected();
				}	
			});
		}
		{
			JLabel lblItem = new JLabel("Item");
			lblItem.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblItem);
		}
		{
			cbBColor = new JComboBox<String>();
			cbBColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addToCbBColor();
			contentPanel.add(cbBColor);
			cbBColor.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setSelected();
				}	
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnOK.setEnabled(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				btnOK.setBackground(Color.GREEN);
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	private void setSelected(){	
		if(cbBModel.getSelectedItem()!=null) {
			for(models m:modelsList){
				if(cbBModel.getSelectedItem().equals(m.getModel_name())){
					model = m;
					break;
				}
			}
		}
		boolean isContain=true;
		if(cbBColor.getSelectedItem()!=null){
			for(items i:itemsList){
				if(cbBColor.getSelectedItem().equals(i.getColor())){
					item = i;
					isContain=false;
					break;
				}
			}
			btnOK.setEnabled(true);
		}
		else {
			btnOK.setEnabled(false);
		}
		if(isContain){
			for(items i:itemsList){
				if(model==i.getModel()){
					item = i;
					break;
				}
			}
		}
	}
	private void addToCbBCategory(){
		cbBCategory.removeAllItems();
		for(categories cat:categoriesList){
			cbBCategory.addItem(cat.getCategory_name());
		}
	}
	
	private void addToCbBBrand(){
		cbBBrand.removeAllItems();
		for(brands b:brandsList){
				cbBBrand.addItem(b.getBrand_name());
		}
	}
	
	private void addToCbBModel(){
		
		cbBModel.removeAllItems();
		for(models m:modelsList){
			if(m.getCategories_brands().getBrand().getBrand_name().equals(cbBBrand.getSelectedItem())&&
			   m.getCategories_brands().getCategory().getCategory_name().equals(cbBCategory.getSelectedItem())
			){
				cbBModel.addItem(m.getModel_name());
			}
		}
	}
	
	private void addToCbBColor(){
		cbBColor.removeAllItems();
		for(items i:itemsList){
			if(i.getModel().getModel_name().equals(cbBModel.getSelectedItem())){
				cbBColor.addItem(i.getColor());
			}
		}
	}
	
	public items getItem(){
		return item;
	}
}