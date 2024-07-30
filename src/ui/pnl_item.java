package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_item extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JButton btnCategories,btnBrands,btnModels,btnItems;
	/**
	 * Create the panel.
	 */
	public pnl_item() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		btnCategories = new JButton("Categories");
		btnCategories.setIcon(new ImageIcon(pnl_item.class.getResource("/images/list1.png")));
		btnCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_categories());
				btnCategories.setForeground(Color.BLACK);
				btnCategories.setBackground(Color.WHITE);
			}
		});
		btnCategories.setForeground(Color.BLACK);
		btnCategories.setBackground(Color.WHITE);
		btnCategories.setFocusable(false);
		panel.add(btnCategories);
		
		btnBrands = new JButton("Brands");
		btnBrands.setIcon(new ImageIcon(pnl_item.class.getResource("/images/list1.png")));
		btnBrands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_brands());
				btnBrands.setForeground(Color.BLACK);
				btnBrands.setBackground(Color.WHITE);
			}
		});
		btnBrands.setForeground(Color.WHITE);
		btnBrands.setBackground(Color.GRAY);
		btnBrands.setFocusable(false);
		panel.add(btnBrands);
		
		btnModels = new JButton("Models");
		btnModels.setIcon(new ImageIcon(pnl_item.class.getResource("/images/list1.png")));
		btnModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_models());
				btnModels.setForeground(Color.BLACK);
				btnModels.setBackground(Color.WHITE);
			}
		});
		btnModels.setForeground(Color.WHITE);
		btnModels.setBackground(Color.GRAY);
		btnModels.setFocusable(false);
		panel.add(btnModels);
		
		btnItems = new JButton("Items");
		btnItems.setIcon(new ImageIcon(pnl_item.class.getResource("/images/list1.png")));
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_items());
				btnItems.setForeground(Color.BLACK);
				btnItems.setBackground(Color.WHITE);
			}
		});
		btnItems.setForeground(Color.WHITE);
		btnItems.setBackground(Color.GRAY);
		btnItems.setFocusable(false);
		panel.add(btnItems);
		
		panel_1 = new pnl_categories();
		add(panel_1);
	}

	//changing panel
	private void changePanel(JPanel obj){
		this.remove(panel_1);
		panel_1 =  obj;
		this.add(panel_1, BorderLayout.CENTER);
		panel_1.setVisible(false);
		panel_1.setVisible(true);
		changeBtnsColor();
	}
		
	//changing btn colors
	private void changeBtnsColor(){
		btnCategories.setForeground(Color.WHITE);
		btnCategories.setBackground(Color.GRAY);
		btnBrands.setForeground(Color.WHITE);
		btnBrands.setBackground(Color.GRAY);
		btnModels.setForeground(Color.WHITE);
		btnModels.setBackground(Color.GRAY);
		btnItems.setForeground(Color.WHITE);
		btnItems.setBackground(Color.GRAY);
	}
}