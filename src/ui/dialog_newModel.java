package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import pojo.*;
import dao.dao;
import java.awt.Toolkit;

public class dialog_newModel extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfModelName;
	private JComboBox<String> cbBCategory,cbBBrand;
	private categories category = new categories();
	private brands brand = new brands();
	private models model = new models();
	JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_newModel dialog = new dialog_newModel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_newModel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_newModel.class.getResource("/images/new.png")));
		setTitle("New model");
		setResizable(false);
		setBounds(100, 100, 280, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 2, 5, 5));
		{
			JLabel lblCategory = new JLabel("Category");
			lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblCategory);
		}
		{
			cbBCategory = new JComboBox<String>();
			cbBCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			for(categories cat : categoriesDAO.getAllCategories("FROM categories")){
				cbBCategory.addItem(cat.getCategory_name());
			}
			contentPanel.add(cbBCategory);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblBrand);
		}
		{
			cbBBrand = new JComboBox<String>();
			cbBBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			for(brands bnd : brandsDAO.getAllBrands("FROM brands")){
				cbBBrand.addItem(bnd.getBrand_name());
			}
			contentPanel.add(cbBBrand);
		}
		{
			JLabel lblModel = new JLabel("Model");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblModel);
		}
		{
			tfModelName = new JTextField();
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
				btnCancel.setForeground(Color.WHITE);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancel.setBackground(Color.RED);
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel, BorderLayout.WEST);
			}
			{
				btnSave = new JButton("Save");
				btnSave.setBackground(Color.GREEN);
				btnSave.setForeground(Color.BLACK);
				btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSave.setActionCommand("Save");
				buttonPane.add(btnSave, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSave);
			}
		}
	}

	public categories getCategory(){
		for(categories cat : categoriesDAO.getAllCategories("FROM categories")){
			if(cat.getCategory_name().equals(cbBCategory.getSelectedItem()+"")){
				category = cat;
				break;
			}
		}
		return category;
	}
	public brands getBrand(){
		for(brands bnd : brandsDAO.getAllBrands("FROM brands")){
			if(bnd.getBrand_name().equals(cbBBrand.getSelectedItem()+"")){
				brand = bnd;
				break;
			}
		}
		return brand;
	}
	public models getModel(){
		model = new models();
		if(tfModelName.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Model cannot be blank");
		}
		else{
			model.setModel_name(tfModelName.getText());
		}
		return model;
	}
}
