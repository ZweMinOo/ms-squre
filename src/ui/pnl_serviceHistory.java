package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import pojo.services;
import dao.dao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;

public class pnl_serviceHistory extends JPanel implements dao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnAddAccessories,btnServiceRelease;
	private dialog_addAccessories dig_addAcc;
	private dialog_serviceRelease dig_serRel;
	
	/**
	 * Create the panel.
	 */
	public pnl_serviceHistory() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		btnAddAccessories = new JButton("Add accessories");
		btnAddAccessories.setIcon(new ImageIcon(pnl_serviceHistory.class.getResource("/images/addAccessories.png")));
		btnAddAccessories.setEnabled(false);
		btnAddAccessories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableBtn();
				dig_addAcc = new dialog_addAccessories(getSelectedServiceNo());
				dig_addAcc.setLocationRelativeTo(null);
				dig_addAcc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dig_addAcc.setVisible(true);
				dig_addAcc.btnSave.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dig_addAcc.saveToDB();
						loadToTable();
						JOptionPane.showMessageDialog(null, "Accessories Added");
						dig_serRel.dispose();
					}
				});
			}
		});
		btnAddAccessories.setForeground(Color.BLACK);
		btnAddAccessories.setBackground(Color.LIGHT_GRAY);
		btnAddAccessories.setFocusable(false);
		panel.add(btnAddAccessories);
		
		btnServiceRelease = new JButton("Service release");
		btnServiceRelease.setIcon(new ImageIcon(pnl_serviceHistory.class.getResource("/images/released.png")));
		btnServiceRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableBtn();
				dig_serRel = new dialog_serviceRelease(getSelectedServiceNo());
				dig_serRel.setVisible(true);
				dig_serRel.setLocationRelativeTo(null);
				dig_serRel.btnRelease.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//dcReleaseDate.getD
						if(dig_serRel.tfServiceFee.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Service fee cannot be empty");
						}
						else if(dig_serRel.dcReleaseDate.getDate() == null){
							JOptionPane.showMessageDialog(null, "Please choose release date");
						}
						//else if(dcReleaseDate.getDate()>Calendar.getInstance()){ }
						else{
							dig_serRel.saveToDB();
							loadToTable();
							JOptionPane.showMessageDialog(null, "Service Released");
							dig_serRel.dispose();
						}
					}
				});
			}
		});
		btnServiceRelease.setEnabled(false);
		btnServiceRelease.setForeground(Color.BLACK);
		btnServiceRelease.setBackground(Color.LIGHT_GRAY);
		btnServiceRelease.setFocusable(false);
		panel.add(btnServiceRelease);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Service no", "Customer name", "Model", "Error", "Service note", "Received date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				btnAddAccessories.setEnabled(true);
				btnServiceRelease.setEnabled(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 loadToTable();
	}
	
	private int getSelectedServiceNo(){
		int row = table.getSelectedRow();
		int sNo = 0;
		if(row>=0){
			sNo = Integer.parseInt(table.getValueAt(row, 0)+"");
		}
		return sNo;
	}
	
	private void disableBtn(){
		btnAddAccessories.setEnabled(true);
		btnServiceRelease.setEnabled(true);
	}
	private void loadToTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		//"Service no", "Customer name", "Model", "Error", "Service note", "Received date"
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		
		List<services> serList = servicesDAO.getAllServices("FROM services WHERE released_date = null");
		for(int i=rowCount;i<serList.size();i++){
			services ser = serList.get(i);
			model.addRow(new Object[]{
				ser.getService_id(),
				ser.getCustomer().getCustomer_name(),
				ser.getModel_name(),
				ser.getError(),
				ser.getService_note(),
				ser.getReceived_date()
			});
		}
	}
}