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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class ui_staff extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panel_1;
	private JButton btnSales,btnServices;
	private menuBar menuBar = new menuBar();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui_staff frame = new ui_staff();
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
	public ui_staff() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ui_staff.class.getResource("/images/MS square.png")));
		setResizable(false);
		setTitle("MS Square 1.0");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		
		setJMenuBar(menuBar);
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnSales = new JButton("Sales");
		btnSales.setIcon(new ImageIcon(ui_staff.class.getResource("/images/sales.png")));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_sales());
				btnSales.setForeground(Color.WHITE);
				btnSales.setBackground(Color.GRAY);
			}
		});
		btnSales.setForeground(Color.WHITE);
		btnSales.setBackground(Color.GRAY);
		btnSales.setFocusable(false);
		panel.add(btnSales);
		
		btnServices = new JButton("Services");
		btnServices.setIcon(new ImageIcon(ui_staff.class.getResource("/images/service.png")));
		btnServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new pnl_services());
				btnServices.setForeground(Color.WHITE);
				btnServices.setBackground(Color.GRAY);
			}
		});
		btnServices.setBackground(Color.WHITE);
		btnServices.setFocusable(false);
		panel.add(btnServices);
		
		panel_1 = new pnl_sales();
		contentPane.add(panel_1, BorderLayout.CENTER);
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
		btnSales.setForeground(Color.BLACK);
		btnSales.setBackground(new Color(255, 255, 255));
		btnServices.setForeground(Color.BLACK);
		btnServices.setBackground(new Color(255, 255, 255));
	}
}