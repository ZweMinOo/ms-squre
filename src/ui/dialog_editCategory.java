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

import pojo.categories;

import dao.dao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Font;
import java.awt.Toolkit;

public class dialog_editCategory extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCategoryName;
	JButton btnSave;
	private List<categories> categoriesList = categoriesDAO.getAllCategories("FROM categories");
	private JComboBox<String> cbBCategory;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_editCategory dialog = new dialog_editCategory();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_editCategory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_editCategory.class.getResource("/images/edit.png")));
		setResizable(false);
		setTitle("Edit Category");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 117);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 5, 5));
		{
			JLabel lblChooseToEdit = new JLabel("Choose to edit");
			lblChooseToEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblChooseToEdit);
		}
		{
			
			cbBCategory = new JComboBox<String>();
			cbBCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbBCategory.setToolTipText("choose to edit");
			for(int i=0;i<categoriesList.size();i++){
				cbBCategory.addItem(categoriesList.get(i).getCategory_name());
			}
			contentPanel.add(cbBCategory);
			cbBCategory.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					tfCategoryName.setEditable(true);
					tfCategoryName.setText(cbBCategory.getSelectedItem()+"");
				}
			});
		}
		{
			JLabel lblCategory = new JLabel("Category name");
			lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCategory.setEnabled(true);
			contentPanel.add(lblCategory);
		}
		{
			tfCategoryName = new JTextField();
			tfCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfCategoryName.setToolTipText("Edit category");
			tfCategoryName.setEditable(false);
			contentPanel.add(tfCategoryName);
			tfCategoryName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSave.setToolTipText("Save edited");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
					btnCancel.setForeground(Color.WHITE);
					btnCancel.setToolTipText("Cancel editing");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					buttonPane.setLayout(new BorderLayout(0, 0));
					btnCancel.setBackground(Color.RED);
					buttonPane.add(btnCancel, BorderLayout.WEST);
				}
				btnSave.setBackground(Color.GREEN);
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSave);
			}
		}
	}
	
	private categories category = new categories();
	public categories getCategory(){
		if(tfCategoryName.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Category name cannot be blank");
		}
		else{
			for(categories cat : categoriesList){
				if(cat.getCategory_name().equals(cbBCategory.getSelectedItem()+"")){
					category = cat;
					category.setCategory_name(tfCategoryName.getText());
					break;
				}
			}
		}
		return category;
	}
}