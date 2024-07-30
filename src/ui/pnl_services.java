package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class pnl_services extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JButton btnServiceHistory,btnNewService;
	/**
	 * Create the panel.
	 */
	public pnl_services() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel, BorderLayout.NORTH);
		
		btnNewService = new JButton("New Service");
		btnNewService.setIcon(new ImageIcon(pnl_services.class.getResource("/images/new.png")));
		btnNewService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_newService());
				btnNewService.setForeground(Color.BLACK);
				btnNewService.setBackground(Color.WHITE);
			}
		});
		btnNewService.setForeground(Color.BLACK);
		btnNewService.setBackground(Color.WHITE);
		btnNewService.setFocusable(false);
		panel.add(btnNewService);
		
		btnServiceHistory = new JButton("Service History");
		btnServiceHistory.setIcon(new ImageIcon(pnl_services.class.getResource("/images/list.png")));
		btnServiceHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_serviceHistory());
				btnServiceHistory.setForeground(Color.BLACK);
				btnServiceHistory.setBackground(Color.WHITE);
			}
		});
		btnServiceHistory.setForeground(Color.WHITE);
		btnServiceHistory.setBackground(Color.GRAY);
		btnServiceHistory.setFocusable(false);
		panel.add(btnServiceHistory);
		
		panel_1 = new pnl_newService();
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
		btnNewService.setForeground(Color.WHITE);
		btnNewService.setBackground(Color.GRAY);
		btnServiceHistory.setForeground(Color.WHITE);
		btnServiceHistory.setBackground(Color.GRAY);
	}
}