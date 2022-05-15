package com; 
import model.customer; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
@Path("/Customer") 
public class customerService 
{ 
 customer customerObj = new customer(); 
 
 
 // reading customer data
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readCustomers() 
 { 
	return customerObj.readCustomers();
 } 


// inserting customer data
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertNewConnection(
 @FormParam("name") String name, 
 @FormParam("discription") String discription, 
 @FormParam("password") String password
 ) 
{ 
 String output = customerObj.insertCustomers(name, discription, password); 
return output; 
}

//updating customer data
@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCustomers(String customerData) 
{ 
//Convert the input string to a JSON object 
 JsonObject connectionObject = new JsonParser().parse(customerData).getAsJsonObject(); 
//Read the values from the JSON object
 String id = connectionObject.get("id").getAsString(); 
 String name = connectionObject.get("name").getAsString(); 
 String discription = connectionObject.get("discription").getAsString(); 
 String password = connectionObject.get("password").getAsString(); 

 String output = customerObj.updateCustomers(id, name,discription,password); 
return output; 
}


//deleting customer data
@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCustomers(String customerData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
 
//Read the value from the element <customerID>
 String id = doc.select("id").text(); 
 String output = customerObj.deleteCustomers(id); 
return output; 
}


}
