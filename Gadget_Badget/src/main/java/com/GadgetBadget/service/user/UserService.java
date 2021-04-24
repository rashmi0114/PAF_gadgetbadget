package com.GadgetBadget.service.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.GadgetBadget.model.DBconnection;
import com.GadgetBadget.util.User;



public class UserService {
	Connection con = null;

	public UserService() {

		con = DBconnection.connecter();
	}
	

	public String insertCart(User user) {


		String output;
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			
			
		String query = "INSERT INTO `order`(`orderId`, `buyerName`, `address`, `NIC`, `softwareName`, `size`, `version`, `cost`, `date`) VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, user.getOrderId());
			preparedStmt.setString(2, user.getBuyerName());
			preparedStmt.setString(3, user.getAddress());
			preparedStmt.setString(4, user.getNIC());
			preparedStmt.setString(5, user.getSoftwareName());
			preparedStmt.setInt(6, user.getSize());
			preparedStmt.setInt(7, user.getVersion());
			preparedStmt.setFloat(8, user.getCost());
			preparedStmt.setString(9, user.getDate());

			
			preparedStmt.execute();

			output = "Inserted details to the cart successfully";

		} catch (SQLException e) {
			output = "Error while inserting the details to the cart.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readCart() {
		String output1 = " ";
		// Prepare the html table to be displayed
		output1 = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Buyer Name</th><th>Address</th>"
				+ "<th>NIC</th>" + "<th>Software Name</th>" + "<th>Size</th>" + "<th>Version</th>" +"<th>Cost</th> "+ "<th>Date</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for review.";
			}
			String query = "select * from `order`";
			
			Statement st = con.createStatement();
			ResultSet results = st.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String orderId = results.getString("orderId");
				String buyerName = results.getString("buyerName");
				String address = results.getString("address");
				String NIC = results.getString("NIC");
				String softwareName = results.getString("softwareName");
				int size = results.getInt("Size");
				int version = results.getInt("version");
				float cost = results.getFloat("cost");
				String date = results.getString("date");
				
				// Add into the html table
				output1 += "<tr><td>" + orderId + "</td>";
				output1 += "<td>" + buyerName + "</td>";
				output1 += "<td>" + address + "</td>";
				output1 += "<td>" + NIC + "</td>";
				output1 += "<td>" + softwareName + "</td>";
				output1 += "<td>" + size + "</td>";
				output1 += "<td>" + version + "</td>";
				output1 += "<td>" + cost +"</td>";
				output1 += "<td>" + date +"</td>";   
			}

			// Complete the html table
			output1 += "</table>";
		} catch (Exception e) {
			output1 = "Error while Updating the cart details.";
			System.err.println(e.getMessage());
		}
		return output1;
	}
	
		public String updateCart(User user) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			//id check
			String query = "UPDATE `order` SET `buyerName`=?,`address`=?,`NIC`=?,`softwareName`=?,`size`=?,`version`=?,`cost`=?,`date`=? WHERE `orderId`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user.getBuyerName());
			preparedStmt.setString(2, user.getAddress());
			preparedStmt.setString(3, user.getNIC());
			preparedStmt.setString(4, user.getSoftwareName());
			preparedStmt.setFloat(5, user.getCost());
			preparedStmt.setInt(6, user.getSize());
			preparedStmt.setInt(7, user.getVersion());
			preparedStmt.setString(8, user.getDate());
			preparedStmt.setString(9, user.getOrderId());


			// execute the statement
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Updated details to the cart successfully";
			}else{
				output = "Error while updating the cart details.";			}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error while updating the cart details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCart(User user) {
		
		
		String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query ="DELETE FROM `order` WHERE orderId=?";

			
			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user.getOrderId());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully from the cart";
			
			
		} catch (Exception e) {
			output = "Error while deleting the cart details.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	
	public String viewCart() {
		String output = " ";
		// Prepare the html table to be displayed
		output = 	"<table border=\"1\"><tr> <th>Buyer Name</th><th>Address</th>"
				+ "<th>NIC</th>" + "<th>Software Name</th>" + "<th>Size</th>" + "<th>Version</th>" + "<th>Cost</th> "+ "<th>Date</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating the cart details";
			}
			String query = "select * from `order`";
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				
				String buyerName = results.getString("buyerName");
				String address = results.getString("address");
				String NIC = results.getString("NIC");
				String sofwareName = results.getString("sofwareName");
				int size = results.getInt("Size");
				int version = results.getInt("version");
				float cost = results.getFloat("cost");
				String date = results.getString("date");
				
				// Add into the html table
				
				output += "<tr><td>" + buyerName + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + sofwareName + "</td>";
				output += "<td>" + size + "</td>";
				output += "<td>" + version + "</td>";
				output += "<td>" + cost +"</td>";
				output += "<td>" + date +"</td>";   
				
				   
			}

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the cart details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
	