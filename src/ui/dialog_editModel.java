package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import pojo.brands;
import pojo.categories;
import pojo.categories_brands;
import pojo.models;
import dao.dao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class dialog_editModel extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfModelName;
	private JComboBox<String> cbBModels,cbBCategories,cbBBrands;
	JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_editModel dialog = new dialog_editModel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_editModel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_editModel.class.getResource("/images/edit.png")));
		setTitle("Edit model");
		setBounds(100, 100, 314, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 5, 5));
		{
			JLabel lblCategoryNames = new JLabel("Category");
			lblCategoryNames.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblCategoryNames);
		}
		{
			cbBCategories = new JComboBox<String>();
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
			cbBCategories.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(cbBCategories);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblBrand);
		}
		{
			cbBBrands = new JComboBox<String>();
			//add brand names to cbBBrands
			for(brands brand : brandsDAO.getAllBrands("FROM brands")){
				cbBBrands.addItem(brand.getBrand_name());
			}
			cbBBrands.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					addModels();
				}
			});
			cbBBrands.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(cbBBrands);
		}
		{
			JLabel lblChooseToEdit = new JLabel("Choose to edit");
			lblChooseToEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblChooseToEdit);
		}
		{
			cbBModels = new JComboBox<String>();
			addModels();
			cbBModels.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					tfModelName.setEditable(true);
					tfModelName.setText(cbBModels.getSelectedItem()+"");
				}
			});
			cbBModels.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(cbBModels);
		}
		{
			JLabel lblModelName = new JLabel("Model name");
			lblModelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblModelName);
		}
		{
			tfModelName = new JTextField();
			tfModelName.setEditable(false);
			tfModelName.setToolTipText("Edit Model");
			tfModelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(tfModelName);
			tfModelName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setForeground(Color.WHITE);
				btnCancel.setBackground(Color.RED);
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCancel.setToolTipText("Cancel editing");
				buttonPane.add(btnCancel, BorderLayout.WEST);
			}
			{
				btnSave = new JButton("Save");
				btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnSave.setToolTipText("Save edited");
				btnSave.setBackground(Color.GREEN);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSave);
			}
		}
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
	models model;
	public models getEditedModel(){
		model = new models();
		String modelName = tfModelName.getText();
		if(modelName.equals("")){
			JOptionPane.showMessageDialog(null, "Model name cannot be blank");
		}
		else{
			for(models model : modelsDAO.getAllModels("FROM models")){
				categories_brands cb = model.getCategories_brands();
				if(cb.getBrand().getBrand_name().equals(cbBBrands.getSelectedItem()+"")&& //if selected model
					cb.getCategory().getCategory_name().equals(cbBCategories.getSelectedItem()+"")&& //if selected category
					model.getModel_name().equals(cbBModels.getSelectedItem()+"") //if selected model
				){
					
					this.model = model;
					model.setModel_name(modelName);
					break;
				}
			}
		}
		return model;
	}
}