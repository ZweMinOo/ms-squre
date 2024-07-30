package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ui_manager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_1;
	private JButton btnPurchase,btnItem,btnSalesService;
	private menuBar menuBar = new menuBar();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui_manager frame = new ui_manager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ui_manager() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ui_manager.class.getResource("/images/MS square.png")));
		setResizable(false);
		setTitle("MS Squrae 1.0.0");
		setBackground(new Color(0, 153, 204));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel_1 = new pnl_purchase();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		btnPurchase= new JButton("Purchase");
		btnPurchase.setIcon(new ImageIcon(ui_manager.class.getResource("/images/purchase.png")));
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_purchase());
				//btnPurchase.setForeground(Color.WHITE);
				btnPurchase.setBackground(Color.LIGHT_GRAY);
			}
		});
		//btnPurchase.setForeground(Color.WHITE);
		btnPurchase.setFocusable(false);
		btnPurchase.setBackground(Color.LIGHT_GRAY);
		panel.add(btnPurchase);
		
		btnItem = new JButton("Item List");
		btnItem.setIcon(new ImageIcon(ui_manager.class.getResource("/images/list.png")));
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_item());
				//btnItem.setForeground(Color.WHITE);
				btnItem.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnItem.setFocusable(false);
		btnItem.setBackground(new Color(255, 255, 255));
		panel.add(btnItem);
		
		btnSalesService = new JButton("Sales & Service Report");
		btnSalesService.setIcon(new ImageIcon(ui_manager.class.getResource("/images/report.png")));
		btnSalesService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_salesAndServiceReport());
				//btnSalesService.setForeground(Color.WHITE);
				btnSalesService.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnSalesService.setFocusable(false);
		btnSalesService.setBackground(new Color(255, 255, 255));
		panel.add(btnSalesService);
	}
	
	//changing panel on click
	private void changePanel(JPanel obj){
		contentPane.remove(panel_1);
		panel_1 =  obj;
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setVisible(false);
		panel_1.setVisible(true);
		changeBtnsColor();
	}
	
	//changing btn colors
	private void changeBtnsColor(){
		btnPurchase.setForeground(Color.BLACK);
		btnPurchase.setBackground(new Color(255, 255, 255));
		btnItem.setForeground(Color.BLACK);
		btnItem.setBackground(new Color(255, 255, 255));
		btnSalesService.setForeground(Color.BLACK);
		btnSalesService.setBackground(new Color(255, 255, 255));
	}
}