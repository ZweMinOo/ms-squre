package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Font;

public class dialog_itemsFilter extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JRadioButton rdbtnReachedReorder, rdbtnNone;
	JButton btnOK;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog_itemsFilter dialog = new dialog_itemsFilter();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_itemsFilter() {
		setResizable(false);
		setTitle("Items Fliter");
		setBounds(100, 100, 217, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		ButtonGroup bg = new ButtonGroup();
		{
			rdbtnReachedReorder = new JRadioButton("Item reached Reorder Lavel");
			rdbtnReachedReorder.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnReachedReorder.setBackground(Color.WHITE);
			bg.add(rdbtnReachedReorder);
			contentPanel.add(rdbtnReachedReorder);
		}
		{
			rdbtnNone = new JRadioButton("None");
			rdbtnNone.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnNone.setSelected(true);
			rdbtnNone.setBackground(Color.WHITE);
			bg.add(rdbtnNone);
			contentPanel.add(rdbtnNone);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnOK.setForeground(Color.WHITE);
				btnOK.setBackground(new Color(51, 51, 204));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
		}
	}

}
