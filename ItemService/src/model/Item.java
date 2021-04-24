package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

public class Item {
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/paf", "root", "");
			System.out.println("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	

	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into seller(`sellerId`,`sellerName`,`sellerCategory`,`sellerPhone`,`sellerEmail`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setInt(4, (int) Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			// 6
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for readingcyhgcyty.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>sellerId</th><th>sellerName</th><th>sellerCategory</th>" + "<th>sellerPhone</th>"
					+ "<th>SellerEmail</th>"+"<th>Update</th><th>Remove</th></tr>" ;

			String query = "select * from seller";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String sellerId = Integer.toString(rs.getInt("sellerId"));
				String sellerName = rs.getString("sellerName");
				String sellerCategory = rs.getString("sellerCategory");
				String sellerPhone = Double.toString(rs.getDouble("sellerPhone"));
				String sellerEmail = rs.getString("sellerEmail");
				// Add into the html table
				
				output += "<tr><td>" + sellerId + "</td>";
				output += "<td>" + sellerName + "</td>";
				output += "<td>" + sellerCategory + "</td>";
				output += "<td>" + sellerPhone + "</td>";
				output += "<td>" + sellerEmail + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + sellerId + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	public String deleteItem(String sellerId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from seller where sellerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(sellerId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String ID, String code, String name, String price, String desc)
	// 4
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE seller SET sellerName=?,sellerCategory=?,sellerPhone=?,sellerEmail=? WHERE sellerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setInt(3, (int) Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
