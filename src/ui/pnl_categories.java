package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pojo.categories;

import dao.dao;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;

public class pnl_categories extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public pnl_categories() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewCategory = new JButton("New Category");
		btnNewCategory.setIcon(new ImageIcon(pnl_categories.class.getResource("/images/new.png")));
		btnNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String cat = "";
					cat = JOptionPane.showInputDialog("Fill category name");
					if(!cat.equals("")){
						categories category = new categories();
						category.setCategory_name(cat);
						categoriesDAO.insertCategories(category);
						loadToTable();
						JOptionPane.showMessageDialog(null, "Saved");
					}
					else{
						JOptionPane.showMessageDialog(null, "Category name cannot be blank","Incomplete Saving!",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Incomplete saving");
				}
				
			}
		});
		btnNewCategory.setBackground(Color.WHITE);
		btnNewCategory.setFocusable(false);
		panel.add(btnNewCategory);
		
		JButton btnEditCategory = new JButton("Edit Category");
		btnEditCategory.setIcon(new ImageIcon(pnl_categories.class.getResource("/images/edit.png")));
		btnEditCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final dialog_editCategory dialog = new dialog_editCategory();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(dialog.getCategory()==null){
							JOptionPane.showMessageDialog(null, "Incomplete saving");
						}
						else {								
							categoriesDAO.updateCategories(dialog.getCategory());						
							
							JOptionPane.showMessageDialog(null,"Saved");
							loadToTable();
							dialog.dispose();
						}						
					}
				});
				
			}
		});
		btnEditCategory.setBackground(Color.WHITE);
		btnEditCategory.setFocusable(false);
		panel.add(btnEditCategory);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Line no", "Name"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
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
		
		List<categories> catList = categoriesDAO.getAllCategories("FROM categories");
		for(int i=0;i<catList.size();i++){
			categories cat = catList.get(i);
			model.addRow(new Object[]{cat.getCategory_id(),cat.getCategory_name()});
		}
	}
}