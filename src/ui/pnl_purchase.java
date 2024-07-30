package ui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_purchase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JButton btnViewPurchaseList,btnNewPurchase;
	/**
	 * Create the panel.
	 */
	public pnl_purchase() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		btnNewPurchase = new JButton("New Purchase");
		btnNewPurchase.setIcon(new ImageIcon(pnl_purchase.class.getResource("/images/new.png")));
		btnNewPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_newPurchase());
				btnNewPurchase.setForeground(Color.BLACK);
				btnNewPurchase.setBackground(Color.WHITE);
			}
		});
		btnNewPurchase.setFocusable(false);
		btnNewPurchase.setBackground(Color.WHITE);
		panel.add(btnNewPurchase);
		
		panel_1 = new pnl_newPurchase();
		panel_1.setBackground(Color.LIGHT_GRAY);
		add(panel_1, BorderLayout.CENTER);
		
		btnViewPurchaseList = new JButton("View Purchase List");
		btnViewPurchaseList.setIcon(new ImageIcon(pnl_purchase.class.getResource("/images/list.png")));
		btnViewPurchaseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_viewPurchaseList());
				btnViewPurchaseList.setForeground(Color.BLACK);
				btnViewPurchaseList.setBackground(Color.WHITE);
			}
		});
		btnViewPurchaseList.setFocusable(false);
		btnViewPurchaseList.setForeground(Color.WHITE);
		btnViewPurchaseList.setBackground(Color.GRAY);
		panel.add(btnViewPurchaseList);
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
		btnNewPurchase.setForeground(Color.WHITE);
		btnNewPurchase.setBackground(Color.GRAY);
		btnViewPurchaseList.setForeground(Color.WHITE);
		btnViewPurchaseList.setBackground(Color.GRAY);
	}
}