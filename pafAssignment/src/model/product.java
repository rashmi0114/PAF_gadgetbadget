package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class product {
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pafassignment", "root", "");
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
			String query = " insert into product(`Pid`,`Pname`,`Pdesc`,`Pcategory`,`Pprice`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4,desc);
			preparedStmt.setFloat(5, (float) Double.parseDouble(price));
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
			output = "<table border='1'><tr><th>Pid</th><th>Pname</th><th>Pdesc</th>" + "<th>Pprice</th>"
					+ "<th>Pcategory</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from product";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Pid = Integer.toString(rs.getInt("Pid"));
				String Pname = rs.getString("Pname");
				String Pdesc = rs.getString("Pdesc");
				String Pprice = Float.toString(rs.getFloat("Pprice"));
				String Pcategory = rs.getString("Pcategory");
				// Add into the html table
				output += "<tr><td>" + Pid + "</td>";
				output += "<td>" + Pname + "</td>";
				output += "<td>" + Pdesc + "</td>";
				output += "<td>" + Pprice + "</td>";
				output += "<td>" + Pcategory + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + Pid + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the product.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String Pid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from product where Pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the product.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String ID, String code, String name, String desc, String price)
	// 4
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE product SET Pname=?,Pdesc=?,Pcategory=?,Pprice=? WHERE Pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, desc);
			preparedStmt.setFloat(4, (float) Double.parseDouble(price));
			
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the product.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
