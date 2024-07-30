package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_salesAndServiceReport extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JButton btnSalesReport,btnServicesReport;
	/**
	 * Create the panel.
	 */
	public pnl_salesAndServiceReport() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		btnSalesReport = new JButton("Sales Report");
		btnSalesReport.setIcon(new ImageIcon(pnl_salesAndServiceReport.class.getResource("/images/list.png")));
		btnSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_salesReport());
				btnSalesReport.setForeground(Color.BLACK);
				btnSalesReport.setBackground(Color.WHITE);
			}
		});
		btnSalesReport.setForeground(Color.BLACK);
		btnSalesReport.setBackground(Color.WHITE);
		btnSalesReport.setFocusable(false);
		panel.add(btnSalesReport);
		
		btnServicesReport = new JButton("ServicesReport");
		btnServicesReport.setIcon(new ImageIcon(pnl_salesAndServiceReport.class.getResource("/images/list.png")));
		btnServicesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_servicesReport());
				btnServicesReport.setForeground(Color.BLACK);
				btnServicesReport.setBackground(Color.WHITE);
			}
		});
		btnServicesReport.setForeground(Color.WHITE);
		btnServicesReport.setBackground(Color.GRAY);
		btnServicesReport.setFocusable(false);
		panel.add(btnServicesReport);
		
		panel_1 = new pnl_salesReport();
		add(panel_1, BorderLayout.CENTER);

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
		btnSalesReport.setForeground(Color.WHITE);
		btnSalesReport.setBackground(Color.GRAY);
		btnServicesReport.setForeground(Color.WHITE);
		btnServicesReport.setBackground(Color.GRAY);
	}
}