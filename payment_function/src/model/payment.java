package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class payment {
	
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/payment", "root", "");
			System.out.println("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	

	public String insertpayment(String userID, String cardname, String cardnumber, String expire_date,String cvv) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into pay(`paymentID`,`userID`,`cardname`,`cardnumber`,`expire_date`,`cvv`)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, (int) Double.parseDouble(userID));
			preparedStmt.setString(3, cardname);
			preparedStmt.setInt(4, (int) Double.parseDouble(cardnumber));
			//preparedStmt.setDate(5, Date.valueOf(expire_date) );
			preparedStmt.setString(5, expire_date);
			preparedStmt.setInt(6, (int) Double.parseDouble(cvv));
			//preparedStmt.setd(5, expire_date);
		
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
	



	public String readpayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>userID</th><th>userID</th><th>cardname</th>" + "<th>cardnumber</th>"
					+ "<th>expire_date</th>" + "<th>cvv</th>"+"<th>Update</th><th>Remove</th></tr>" ;

			String query = "select * from pay";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String userID = rs.getString("userID");
				String cardname = rs.getString("cardname");
				String cardnumber = Double.toString(rs.getDouble("cardnumber"));
				String expire_date = rs.getString("expire_date");
				String cvv = rs.getString("cvv");
				// Add into the html table
				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + cardname + "</td>";
				output += "<td>" + cardnumber + "</td>";
				output += "<td>" + expire_date + "</td>";
				output += "<td>" + cvv + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	public String deletepayment(String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from pay where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatepayment(String paymentID,String userID, String cardname, String cardnumber, String expire_date,String cvv)
	// 4
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE pay SET userID=?,cardname=?,cardnumber=?,expire_date=?,cvv=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,(int) Double.parseDouble (userID));
			preparedStmt.setString(2, cardname);
			preparedStmt.setInt(3, (int) Double.parseDouble(cardnumber));
			preparedStmt.setString(4, expire_date);
			preparedStmt.setInt(5, (int) Double.parseDouble(cvv));
			preparedStmt.setInt(6, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
