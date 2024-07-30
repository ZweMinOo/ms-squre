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
import dao.dao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class dialog_editBrand extends JDialog implements dao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfBrandName;
	private JComboBox<String> cbBBrand;
	JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_editBrand dialog = new dialog_editBrand();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_editBrand() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_editBrand.class.getResource("/images/edit.png")));
		setTitle("Edit Brand");
		setResizable(false);
		setBounds(100, 100, 260, 131);
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
			cbBBrand = new JComboBox<String>();
			for(brands brn : brandsDAO.getAllBrands("FROM brands")){
				cbBBrand.addItem(brn.getBrand_name()+"");
			}
			cbBBrand.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbBBrand.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					tfBrandName.setEditable(true);
					tfBrandName.setText(cbBBrand.getSelectedItem()+"");				
				}
				
			});
			contentPanel.add(cbBBrand);
		}
		{
			JLabel lblBrandName = new JLabel("Brand Name");
			lblBrandName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblBrandName);
		}
		{
			tfBrandName = new JTextField();
			tfBrandName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfBrandName.setEditable(false);
			contentPanel.add(tfBrandName);
			tfBrandName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCancel.setForeground(Color.WHITE);
				btnCancel.setBackground(Color.RED);
				btnCancel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dispose();
					}
					
				});
				buttonPane.setLayout(new BorderLayout(0, 0));
				btnCancel.setToolTipText("Cancel editing");
				buttonPane.add(btnCancel, BorderLayout.WEST);
			}
			{
				btnSave = new JButton("Save");
				btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSave.setBackground(Color.GREEN);
				btnSave.setActionCommand("");
				buttonPane.add(btnSave, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSave);
			}
		}
	}
	private brands brand = new brands();
	public brands getBrand(){
		brand = new brands();
		if(tfBrandName.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Brand name cannot be blank");
			brand = null;
		}
		else{
			for(brands bnd : brandsDAO.getAllBrands("FROM brands")){
				if(bnd.getBrand_name().equals(cbBBrand.getSelectedItem())){
					brand = bnd;
					brand.setBrand_name(tfBrandName.getText());
					break;
				}
			}
		}
		return brand;
	}
}