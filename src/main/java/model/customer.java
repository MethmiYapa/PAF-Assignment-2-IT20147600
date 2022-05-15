package model; 
import java.sql.*; 
public class customer 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/supliermanagement", "root", "");
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 

//method to insert new customers
public String insertCustomers(String name, String discription, String password) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into supplier (`id`,`name`,`discription`,`password`)"
 + " values (?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, discription); 
 preparedStmt.setString(4, password); 

 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
 System.err.println(e.getMessage()); 
 }
 return output; 
 } 


//method to read customers
public String readCustomers() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr>"+
 "<th>Supplier Name</th><th>"+
 "Discription</th>" +
 "<th>Password</th>" + 
 
 "</tr>"; 
 
 String query = "select * from supplier"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String id= Integer.toString(rs.getInt("id")); 
 String name = rs.getString("name"); 
 String discription = rs.getString("discription"); 
 String password = rs.getString("password");  
 
 // Add into the html table
 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' "
 		+ " type='hidden' value='" + id + "'>"
		 + name + "</td>"; 
 output += "<td>" + discription + "</td>"; 
 output += "<td>" + password + "</td>"; 

//buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";  
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading connections."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 



//method to update items
public String updateCustomers(String id, String name, String discription, String password) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE supplier SET name=?,discription=?,password=? WHERE id=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, discription); 
 preparedStmt.setString(3, password); 

 preparedStmt.setInt(4, Integer.parseInt(id)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 



//method to delete customers
public String deleteCustomers(String id) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from supplier where id=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(id)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 
