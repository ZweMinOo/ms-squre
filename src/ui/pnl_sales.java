package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_sales extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	/**
	 * Create the panel.
	 */
	public pnl_sales() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel, BorderLayout.NORTH);
		
		JButton btnNewSale = new JButton("New Sale");
		btnNewSale.setIcon(new ImageIcon(pnl_sales.class.getResource("/images/new.png")));
		btnNewSale.setForeground(Color.BLACK);
		btnNewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_newSale());
			}
		});
		btnNewSale.setBackground(Color.WHITE);
		btnNewSale.setFocusable(false);
		panel.add(btnNewSale);
		
		panel_1 = new pnl_newSale();
		add(panel_1, BorderLayout.CENTER);

	}

	//changing panel on click
	private void changePanel(JPanel obj){
		this.remove(panel_1);
		panel_1 =  obj;
		this.add(panel_1, BorderLayout.CENTER);
		panel_1.setVisible(false);
		panel_1.setVisible(true);
		//changeBtnsColor();
	}
}