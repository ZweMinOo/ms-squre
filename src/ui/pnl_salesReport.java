package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import dao.dao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

public class pnl_salesReport extends JPanel implements dao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private String tDate = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
	private dialog_salesReportFilter dialog =  new dialog_salesReportFilter();
	/**
	 * Create the panel.
	 */
	public pnl_salesReport() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JButton btnFliters = new JButton("Fliters");
		btnFliters.setIcon(new ImageIcon(pnl_salesReport.class.getResource("/images/filter.png")));
		btnFliters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				try {
					dialog.dcFromDate.setDate(sdf.parse(tDate));
					dialog.dcToDate.setDate(sdf.parse(tDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dialog.btnOK.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						java.util.Date fromDate = dialog.dcFromDate.getDate(),toDate = dialog.dcToDate.getDate();
						
						if(dialog.rdbtnByTime.isSelected()){
							if(dialog.rdbtnDaily.isSelected()){
								loadToTable("Daily",fromDate,toDate,1);
							}else if(dialog.rdbtnMonthly.isSelected()){
								loadToTable("Monthly",fromDate,toDate,1);
							}else{
								loadToTable("Yearly",fromDate,toDate,1);
							}
						}else{
							try{
								int top = Integer.parseInt(dialog.tfTop.getText());
								if(top<1){
									JOptionPane.showMessageDialog(null, "Top must be greater than -1","Invalid",JOptionPane.ERROR_MESSAGE);
								}
								else {
									if(dialog.rdbtnBSI.isSelected()){
										loadToTable("BSI",fromDate,toDate,top);
									}else{
										loadToTable("WSI",fromDate,toDate,top);
									}
								}
							}catch(NumberFormatException nfe){
								JOptionPane.showMessageDialog(null, "Top must be only number","Invalid",JOptionPane.ERROR_MESSAGE);
							}
						}
						//dialog.dispose();
						dialog.setVisible(false);
					}
					
				});
			}
		});
		btnFliters.setBackground(Color.WHITE);
		btnFliters.setFocusable(false);
		panel.add(btnFliters);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();	
		
		try {
			loadToTable("Daily",sdf.parse(tDate),sdf.parse(tDate),1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
	}
	
	//load to table by type
	private void loadToTable(String type,java.util.Date fromDate,java.util.Date toDate,int top){
		setTableModel(type);
		loadData(type,fromDate,toDate,top);		
	}
	
	DefaultTableModel model;
	
	//load data form database by type
	private void loadData(String type,java.util.Date fromDate,java.util.Date toDate,int top){
		model = (DefaultTableModel) table.getModel();
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
					query = "SELECT s.date as Day, COUNT(s.sales_id) as TotalSales, SUM(sd.quantity*sd.price) as Amount " +
							"FROM sales as s " +
							"INNER JOIN sales_details as sd " +
							"ON s.sales_id = sd.sales_id " +
							"WHERE s.date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY s.date " +
							"ORDER BY s.date";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String day  = rs.getString("Day");
				         int totalSales = rs.getInt("TotalSales");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{day,totalSales,amount});
				        
				         allSales += totalSales;
				         allAmount += amount;
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();				 
				}else if(type.equals("Monthly")){ // get monthly report
					query = "SELECT MONTH(s.date) as Month, COUNT(s.sales_id) as TotalSales, SUM(sd.quantity*sd.price) as Amount " +
							"FROM sales as s " +
							"INNER JOIN sales_details as sd " +
							"ON s.sales_id = sd.sales_id " +
							"WHERE date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY MONTH(s.date) " +
							"ORDER BY MONTH(s.date)";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String month  = rs.getString("Month");
				         int totalSales = rs.getInt("TotalSales");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{month,totalSales,amount});
				        
				         allSales += totalSales;
				         allAmount += amount;
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();
				}else if(type.equals("Yearly")){ // get yearly report
					query = "SELECT YEAR(s.date) as Year, COUNT(s.sales_id) as TotalSales, SUM(sd.quantity*sd.price) as Amount " +
							"FROM sales as s " +
							"INNER JOIN sales_details as sd " +
							"ON s.sales_id = sd.sales_id " +
							"WHERE date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY YEAR(s.date) " +
							"ORDER BY YEAR(s.date)";
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
				         //Retrieve by column name
				         String month  = rs.getString("Year");
				         int totalSales = rs.getInt("TotalSales");
				         double amount = rs.getDouble("Amount");
				         
				         model.addRow(new Object[]{month,totalSales,amount});
				        
				         allSales += totalSales;
				         allAmount += amount;
				      }	   
					 model.addRow(new Object[]{"Total : ",allSales,allAmount});
					 rs.close();
				}else if(type.equals("BSI")){ // get best selling item report
					query = "SELECT m.model_name,i.color,b.brand_name,c.category_name,COUNT(sd.quantity) as qty,SUM(sd.quantity*sd.price) as Amount " +
							"FROM sales as s " +
							"INNER JOIN sales_details as sd "+
							"ON s.sales_id = sd.sales_id "+
							"INNER JOIN items as i "+
							"ON sd.item_id = i.item_id "+
							"INNER JOIN models as m "+
							"ON m.model_id = i.model_id "+
							"INNER JOIN categories_brands as cb "+
							"ON cb.categories_brands_id = m.categories_brands_id "+
							"INNER JOIN brands as b "+
							"ON b.brand_id = cb.brand_id "+
							"INNER JOIN categories as c "+
							"ON c.category_id = cb.category_id "+
							"WHERE s.date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY i.item_id "+
							"ORDER BY SUM(sd.quantity*sd.price) DESC LIMIT " + top;
					
					ResultSet rs = stmt.executeQuery(query);
					//"Line no","Model","Color","Brand","Category","Qty","Total Amount"
					int i=0;
					while(rs.next()){
				         //Retrieve by column name
						int lineNo = i + 1; i++;
						String model_name = rs.getString("model_name");
				        String color = rs.getString("color");
				        String brand = rs.getString("brand_name");
				        String category = rs.getString("category_name");
				        int qty = rs.getInt("qty");
				        double amt = rs.getDouble("Amount");
				        
				        model.addRow(new Object[]{lineNo,model_name,color,brand,category,qty,amt});
				        
				    }	   
					rs.close();
				}else if(type.equals("WSI")){// get worst selling report
					query = "SELECT m.model_name,i.color,b.brand_name,c.category_name,COUNT(sd.quantity) as qty,SUM(sd.quantity*sd.price) as Amount " +
							"FROM sales as s " +
							"INNER JOIN sales_details as sd "+
							"ON s.sales_id = sd.sales_id "+
							"INNER JOIN items as i "+
							"ON sd.item_id = i.item_id "+
							"INNER JOIN models as m "+
							"ON m.model_id = i.model_id "+
							"INNER JOIN categories_brands as cb "+
							"ON cb.categories_brands_id = m.categories_brands_id "+
							"INNER JOIN brands as b "+
							"ON b.brand_id = cb.brand_id "+
							"INNER JOIN categories as c "+
							"ON c.category_id = cb.category_id "+
							"WHERE s.date BETWEEN '"+ sdf.format(fromDate) + "' AND '" + sdf.format(toDate) +
							"' GROUP BY i.item_id "+
							"ORDER BY SUM(sd.quantity*sd.price) ASC LIMIT " + top;
					
					ResultSet rs = stmt.executeQuery(query);
					//"Line no","Model","Color","Brand","Category","Qty","Total Amount"
					int i=0;
					while(rs.next()){
				         //Retrieve by column name
						int lineNo = i + 1; i++;
						String model_name = rs.getString("model_name");
				        String color = rs.getString("color");
				        String brand = rs.getString("brand_name");
				        String category = rs.getString("category_name");
				        int qty = rs.getInt("qty");
				        double amt = rs.getDouble("Amount");
				        
				        model.addRow(new Object[]{lineNo,model_name,color,brand,category,qty,amt});
				      }	   
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
						"Date", "Total sales", "Total amount"
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
						"Monthly", "Total sales", "Total amount"
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
						"Yearly", "Total sales", "Total amount"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		}else if(type.equals("BSI")||type.equals("WSI")){
			 table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Line no","Model","Color","Brand","Category","Qty","Total Amount"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}
}