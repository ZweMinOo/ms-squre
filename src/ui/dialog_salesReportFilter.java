package ui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class dialog_salesReportFilter extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextField tfTop;
	JDateChooser dcFromDate,dcToDate;
	JRadioButton rdbtnByTime, rdbtnByItem, rdbtnDaily, rdbtnMonthly, rdbtnYearly, rdbtnBSI, rdbtnWSI;
	JButton btnOK;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_salesReportFilter dialog = new dialog_salesReportFilter();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_salesReportFilter() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dialog_salesReportFilter.class.getResource("/images/filter.png")));
		setTitle("Filter");
		setBounds(100, 100, 450, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFromDate = new JLabel("From date");
		lblFromDate.setBounds(90, 11, 62, 14);
		contentPanel.add(lblFromDate);
		
		JLabel lblToDate = new JLabel("To date");
		lblToDate.setBounds(252, 11, 46, 14);
		contentPanel.add(lblToDate);
		
		dcFromDate = new JDateChooser();
		dcFromDate.setBounds(151, 11, 91, 20);
		contentPanel.add(dcFromDate);
		
		dcToDate = new JDateChooser();
		dcToDate.setBounds(310, 11, 91, 20);
		contentPanel.add(dcToDate);
		
		ButtonGroup bg = new ButtonGroup();
		rdbtnByTime = new JRadioButton("By Time");
		rdbtnByTime.setBackground(Color.WHITE);
		rdbtnByTime.setSelected(true);
		rdbtnByTime.setBounds(6, 53, 72, 23);
		rdbtnByTime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rdbtnDaily.setEnabled(true);
				rdbtnMonthly.setEnabled(true);
				rdbtnYearly.setEnabled(true);
				
				tfTop.setEnabled(false);
				rdbtnBSI.setEnabled(false);
				rdbtnWSI.setEnabled(false);
			}
		});
		contentPanel.add(rdbtnByTime);
		
		
		rdbtnByItem = new JRadioButton("By Item");
		rdbtnByItem.setBackground(Color.WHITE);
		rdbtnByItem.setBounds(6, 119, 72, 23);
		rdbtnByItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rdbtnDaily.setEnabled(false);
				rdbtnMonthly.setEnabled(false);
				rdbtnYearly.setEnabled(false);
				
				tfTop.setEnabled(true);
				rdbtnBSI.setEnabled(true);
				rdbtnWSI.setEnabled(true);
			}
		});
		contentPanel.add(rdbtnByItem);
		
		bg.add(rdbtnByTime);
		bg.add(rdbtnByItem);
		
		/* panel */
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(90, 42, 334, 41);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 3, 5, 5));
		
		ButtonGroup bg1 = new ButtonGroup();
		rdbtnDaily = new JRadioButton("Daily");
		rdbtnDaily.setBackground(Color.WHITE);
		rdbtnDaily.setSelected(true);
		panel.add(rdbtnDaily);
		
		rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBackground(Color.WHITE);
		panel.add(rdbtnMonthly);
		
		rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.setBackground(Color.WHITE);
		panel.add(rdbtnYearly);
		bg1.add(rdbtnDaily);
		bg1.add(rdbtnMonthly);
		bg1.add(rdbtnYearly);
		
		/* end panel */
		
		/* panel_1 */
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(90, 94, 334, 80);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		ButtonGroup bg2 = new ButtonGroup();
		tfTop = new JTextField();
		tfTop.setEnabled(false);
		tfTop.setToolTipText("Number of top");
		tfTop.setBounds(66, 8, 86, 20);
		panel_1.add(tfTop);
		tfTop.setColumns(10);
		
		rdbtnBSI = new JRadioButton("Best selling items");
		rdbtnBSI.setEnabled(false);
		rdbtnBSI.setSelected(true);
		rdbtnBSI.setBackground(Color.WHITE);
		rdbtnBSI.setBounds(165, 7, 135, 23);
		panel_1.add(rdbtnBSI);
		
		rdbtnWSI = new JRadioButton("Worst selling items");
		rdbtnWSI.setEnabled(false);
		rdbtnWSI.setBackground(Color.WHITE);
		rdbtnWSI.setBounds(165, 50, 135, 23);
		panel_1.add(rdbtnWSI);
		bg2.add(rdbtnWSI);
		bg2.add(rdbtnBSI);
		JLabel lblTop = new JLabel("Top");
		lblTop.setBounds(10, 11, 46, 14);
		panel_1.add(lblTop);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
		}
		/* end panel_1 */
	}
}