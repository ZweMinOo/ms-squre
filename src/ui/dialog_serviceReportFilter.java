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

public class dialog_serviceReportFilter extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JButton btnOK;
	JRadioButton rdbtnDaily,rdbtnMonthly,rdbtnYearly;
	JDateChooser dcToDate, dcFromDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_serviceReportFilter dialog = new dialog_serviceReportFilter();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_serviceReportFilter() {
		setTitle("Service Report Filter");
		setResizable(false);
		setBounds(100, 100, 371, 161);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFromDate = new JLabel("From date");
			lblFromDate.setBounds(10, 11, 65, 14);
			contentPanel.add(lblFromDate);
		}
		
		dcFromDate = new JDateChooser();
		dcFromDate.setBounds(80, 11, 91, 20);
		contentPanel.add(dcFromDate);
		
		JLabel lblToDate = new JLabel("To date");
		lblToDate.setBounds(192, 11, 46, 14);
		contentPanel.add(lblToDate);
		
		dcToDate = new JDateChooser();
		dcToDate.setBounds(252, 11, 91, 20);
		contentPanel.add(dcToDate);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnDaily = new JRadioButton("Daily");
		rdbtnDaily.setSelected(true);
		rdbtnDaily.setBackground(Color.WHITE);
		rdbtnDaily.setBounds(18, 54, 109, 23);
		bg.add(rdbtnDaily);
		contentPanel.add(rdbtnDaily);
		
		rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBackground(Color.WHITE);
		rdbtnMonthly.setBounds(129, 54, 109, 23);
		bg.add(rdbtnMonthly);
		contentPanel.add(rdbtnMonthly);
		
		rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.setBackground(Color.WHITE);
		rdbtnYearly.setBounds(250, 54, 109, 23);
		bg.add(rdbtnYearly);
		contentPanel.add(rdbtnYearly);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setForeground(Color.WHITE);
				btnOK.setBackground(Color.BLUE);
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
		}
	}
}
