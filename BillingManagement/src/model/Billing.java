package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/egsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertBilling(String AccNo, String Date, String Unit, String UnitPrice, String Total) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Ooops!!!, Error in Database connection....";
			}
			
			// Data insert
			
			String query = " insert into billing(`b_ID`,`b_accNo`,`b_date`,`b_unit`,`b_unitPrice`,`b_total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pre_Stmt = con.prepareStatement(query);
			
			pre_Stmt.setInt(1, 0);
			pre_Stmt.setString(2, AccNo);
			pre_Stmt.setString(3, Date);
			pre_Stmt.setString(4, Unit);
			pre_Stmt.setString(5, UnitPrice);
			pre_Stmt.setString(6, Total);
			
			
			// executing 
			
			pre_Stmt.execute();
			con.close();
			output = "Successfully Insreted";
		} catch (Exception e) {
			
			output = "An error occurred when inserting the billing information.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBilling() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "An error occurred when reading from the database.";
			}
			
			
			// Prepare the html table.
			
			
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Account No</th><th>Date</th><th>Unit Price</th><th>PreUnit Price</th><th>Total Amount</th></tr>";
			String query = "select * from billing1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("biil_ID"));
				String AccNo = rs.getString("bill_AccNo");
				String Date = rs.getString("bill_Date");
				String Unit = rs.getString("bill_UnitA");
				String UnitPrice = rs.getString("bill_Unitprice");
				String Total = rs.getString("bill_Total");

				// Add to the table
				output += "<tr><td>" + ID + "</td>";
				output += "<td>" + AccNo + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Unit + "</td>";
				output += "<td>" + UnitPrice + "</td>";
				output += "<td>" + Total + "</td>";
				
			}
			con.close();
			output += "</table>";
		} catch (Exception e) {
			output = "Error in reading data.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBilling(String ID, String AccNo, String Date, String Unit, String UnitPrice, String Total) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error in updating data.";
			}

			// create a prepared statement
			String query = "UPDATE billing1 SET b_accNo=?,b_accNo=?,b_unit=?,b_unitPrice=?,b_total=?" + "WHERE b_ID=?";

			PreparedStatement UpdatePre_Stmt = con.prepareStatement(query);

			// binding values
			UpdatePre_Stmt.setString(1, AccNo);
			UpdatePre_Stmt.setString(2, Date);
			UpdatePre_Stmt.setString(3, Unit);
			UpdatePre_Stmt.setString(4, UnitPrice);
			UpdatePre_Stmt.setString(5, Total);
			UpdatePre_Stmt.setInt(6, Integer.parseInt(ID));

			// execute the statement
			UpdatePre_Stmt.execute();
			con.close();

			output = "Successfully Updated";
		} catch (Exception e) {
			output = "Error in updating....";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBilling(String ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Ooops!!!, Error in Database connection....";
			}

			// create a prepared statement
			String query = "delete from billing where b_ID=?";

			PreparedStatement DeletePre_Stmt = con.prepareStatement(query);

			// binding values
			DeletePre_Stmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			DeletePre_Stmt.execute();
			con.close();

			output = "Successfully Deleted.";
		} catch (Exception e) {
			output = "Error in deleting.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
