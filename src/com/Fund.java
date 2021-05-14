package com;

import java.sql.*;


public class Fund {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funds", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	public String insertFunds(String code, String name, String amount, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into fund (`fundID`,`fundCode`,`ProjectName`,`fundAmount`,`fundDesc`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setDouble(4, Double.parseDouble(amount)); 
	 preparedStmt.setString(5, desc);
	 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newFunds = readFunds();
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newFunds + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the funds.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String readFunds() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1' class='table table-striped'><thead><tr><th>Fund Code</th><th>Project Name</th>" +
	 "<th>Fund Amount</th>" + 
	 "<th>Fund Description</th>" +
	 "<th>Update</th><th>Remove</th></tr></thead><tbody>"; 
	 
	 String query = "select * from fund"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String fundID = Integer.toString(rs.getInt("fundID")); 
	 String fundCode = rs.getString("fundCode"); 
	 String projectName = rs.getString("projectName"); 
	 String fundAmount = Double.toString(rs.getDouble("fundAmount")); 
	 String fundDesc = rs.getString("fundDesc"); 
	 
	 
	 // Add into the html table
	 output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + fundID
			 + "'>" + fundCode + "</td>"; 
			 output += "<td>" + projectName + "</td>"; 
			 output += "<td>" + fundAmount + "</td>"; 
			 output += "<td>" + fundDesc + "</td>"; 
	 
	 
	 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundid='"
						+ fundID + "'>" + "</td></tr>";  
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</tbody></table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Funds."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateFunds(String ID, String code, String name, String amount, String desc)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE fund SET fundCode=?,projectName=?,fundAmount=?,fundDesc=? WHERE fundID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setDouble(3, Double.parseDouble(amount)); 
		 preparedStmt.setString(4, desc); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newFunds = readFunds();
		 output = "{\"status\":\"success\", \"data\": \"" + 
				 newFunds + "\"}";  
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the funds.\"}";
		 e.printStackTrace();
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	
	public String deleteFunds(String fundID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from fund where fundID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fundID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 String newFunds = readFunds();
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newFunds + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the funds.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
