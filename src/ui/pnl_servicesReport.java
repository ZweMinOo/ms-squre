package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pnl_servicesReport extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private String tDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	private dialog_serviceReportFilter dialog;
	/**
	 * Create the panel.
	 */
	public pnl_servicesReport() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JButton btnFliters = new JButton("Fliters");
		btnFliters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog = new dialog_serviceReportFilter();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.setVisible(true);
				try {
					dialog.dcFromDate.setDate(sdf.parse(tDate));
					dialog.dcToDate.setDate(sdf.parse(tDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.btnOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						java.util.Date fromDate = dialog.dcFromDate.getDate(),toDate = dialog.dcToDate.getDate();
						
						if(dialog.rdbtnDaily.isSelected()){
							loadToTable("Daily",fromDate,toDate);
						}else if(dialog.rdbtnMonthly.isSelected()){
							loadToTable("Monthly",fromDate,toDate);
						}else if(dialog.rdbtnYearly.isSelected()){
							loadToTable("Yearly",fromDate,toDate);
						}
						dialog.dispose();
					}
				});
			}
		});
		btnFliters.setIcon(new ImageIcon(pnl_servicesReport.class.getResource("/images/filter.png")));
		btnFliters.setBackground(Color.WHITE);
		panel.add(btnFliters);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setRowHeight(20);
		try {
			loadToTable("Daily",sdf.parse(tDate),sdf.parse(tDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
	}
	
	//load to table by type
	private void loadToTable(String type,java.util.Date fromDate,java.util.Date toDate){		
		setTableModel(type);
		loadData(type,fromDate,toDate);
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DefaultTableModel model;
	
	public void loadData(String type,java.util.Date fromDate,java.util.Date toDate){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1;i>=0;i--){
			model.removeRow(i);
			table.revalidate();
		}
		
		//Retrieve Date 
		   String jdbc_driver= "com.mysql.jdbc.Driver";  
		   String db_url = "jdbc:mysql://localhost/ms_square";

		   final String user = "root";
		   final String pass = "1234";
		   
		   java.sql.Connection conn = null;
		   java.sql.Statement stmt = null;
		   
		   try{
		      Class.forName(jdbc_driver);

		      conn = DriverManager.getConnection(db_url, user, pass);
		      
		      stmt = conn.createStatement();
		      
		      model = (DefaultTableModel) table.getModel();
		      String query = "";
				
		      // choose query by type
			  if(type.equals("")){
					type = "Daily";
			  }
				int allSales = 0,allAmount = 0;
				if(type.equals("Daily")){ // get daily report
					query = "SELECT s.released_date as Day, COUNT(s.service_id) as TotalServices, SUM(s.service_fee) as Amount " +
							"FROM services as s " +
							"WHERE s.released_date BETWEEN '" + sdf.format(fromDate) + "' AND '" + sdf.format(toDate)+
							"' GROUP BY s.released_date " +
							"ORDER BY s.released_date";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String day  = rs.getString("Day");
				         int totalServices = rs.getInt("TotalServices");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{day,totalServices,amount});
				        
				         allSales += totalServices;
				         allAmount += amount;
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();				 
				}else if(type.equals("Monthly")){ // get monthly report
					query = "SELECT MONTH(s.released_date) as Month, COUNT(s.service_id) as TotalServices, SUM(s.service_fee) as Amount " +
							"FROM services as s " +
							"INNER JOIN services_details as sd " +
							"ON s.service_id = sd.service_id " +
							"WHERE s.released_date BETWEEN "+ sdf.format(fromDate) + " AND '" + sdf.format(toDate) +
							"' GROUP BY MONTH(s.released_date) " +
							"ORDER BY MONTH(s.released_date)";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String month  = rs.getString("Month");
				         int totalServices = rs.getInt("TotalServices");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{month,totalServices,amount});
				        
				         allSales += totalServices;
				         allAmount += amount;
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();
				}else if(type.equals("Yearly")){ // get yearly report
					query = "SELECT YEAR(s.released_date) as Year, COUNT(s.service_id) as TotalServices, SUM(s.service_fee) as Amount " +
							"FROM services as s " +
							"INNER JOIN services_details as sd " +
							"ON s.service_id = sd.service_id " +
							"WHERE s.released_date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY YEAR(s.released_date) " +
							"ORDER BY YEAR(s.released_date)";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String year  = rs.getString("Year");
				         int totalServices = rs.getInt("TotalServices");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{year,totalServices,amount});
				        
				         allSales += totalServices;
				         allAmount += amount;
				         System.out.println("yearly");
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();
					 
				}	      
		   } // end try
		   catch(SQLException se){
		      se.printStackTrace();
		   }
		   catch(Exception e){
		      e.printStackTrace();
		   }
		   finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		//int i=1;
//		for(purchase_details purDet : purchase_detailsDAO.getAllPurchaseDetails("FROM purchase_details")){
//			
//			model.addRow(
//				new Object[]{
//						i++,
//						purDet.getPurchase().getSupplier().getSupplier_name(),
//						purDet.getPurchase().getSupplier().getContact_name(),
//						purDet.getItem().getModel().getModel_name(),
//						purDet.getItem().getColor(),
//						purDet.getQuantity(),
//						purDet.getPurchase().getPurchased_date(),
//						purDet.getTotal_amount()
//			});
//		}
	}
	
	//change table column by type
	@SuppressWarnings("serial")
	private void setTableModel(String type){
		if(type.equals("")){
			type = "Daily";
		}
		if(type.equals("Daily")){
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Date", "Total services", "Total amount"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		}else if(type.equals("Monthly")){
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Monthly", "Total services", "Total amount"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			
		}else if(type.equals("Yearly")){
			table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Yearly", "Total services", "Total amount"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		}
	}
}