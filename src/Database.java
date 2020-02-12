import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.AllInfoRow;
import models.Respondent;

/*
 * Class for managing the database.
 */
public class Database {

	// If you have the mysql server on your own computer use "localhost" as server
	// address.
	
	private static String databaseServerAddress = "vm23.cs.lth.se";
	private static String databaseUser = ""; // database login user
	private static String databasePassword = ""; // database login password
	private static String database = ""; // the database to use, i.e.
													// default schema
	Connection conn = null;

	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Necessary on Windows computers
			conn = DriverManager.getConnection("jdbc:mysql://" + databaseServerAddress + "/" + database, databaseUser,
					databasePassword);

			// Display the contents of the database in the console.
			// This should be removed in the final version
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Respondents");
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}

			stmt.close();

		} catch (SQLException e) {
			printSqlError(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addName(String name) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try {
			String sql = "insert into Respondents (name) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
			resultOK = true;
			ps.close();
		} catch (SQLException e) {
			resultOK = false; // one reason may be that the name is already in the database
			if (e.getErrorCode() == 1062 && e.getSQLState().equals("23000")) {
				// duplicate key error
				System.out.println(name + " already exists in the database");
			} else {
				printSqlError(e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}

	public boolean addProjectChar(String name, int s0, int s1) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try {
			String sql = "insert into ProjectChar (name, charzero, charone) values(?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, s0);
			ps.setInt(3, s1);

			ps.executeUpdate();
			resultOK = true;
			ps.close();
		} catch (SQLException e) {
			resultOK = false; // one reason may be that the name is already in the database
			if (e.getErrorCode() == 1062 && e.getSQLState().equals("23000")) {
				// duplicate key error
				System.out.println(name + " already exists in the database");
			} else {
				printSqlError(e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}

	public boolean addAnswers(String name, int s11, int s12, int s13, int s14) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try {
			String sql = "insert into Answers (name, sone, stwo, sthree, sfour) values(?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, s11);
			ps.setInt(3, s12);
			ps.setInt(4, s13);
			ps.setInt(5, s14);

			ps.executeUpdate();
			resultOK = true;
			ps.close();
		} catch (SQLException e) {
			resultOK = false; // one reason may be that the name is already in the database
			if (e.getErrorCode() == 1062 && e.getSQLState().equals("23000")) {
				// duplicate key error
				System.out.println(name + " already exists in the database");
			} else {
				printSqlError(e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}

	private void printSqlError(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
		e.printStackTrace();
	}

	public List<AllInfoRow> getAllData() {
		ArrayList<AllInfoRow> allInfo = new ArrayList<>();
		try {
			// This query returns only the rows that have values in every column
			String query = "select Respondents.name,\n" + "		Answers.sone,\n" + "        Answers.stwo,\n"
					+ "		Answers.sthree,\n" + "		Answers.sfour,\n" + "		ProjectChar.charzero,\n"
					+ "        ProjectChar.charone\n"
					+ "from Respondents natural join Answers natural join ProjectChar;";
			
			// This query returns all the rows
			String query1 = "select Respondents.name,\n" + 
					"		Answers.sone,\n" + 
					"		Answers.stwo, \n" + 
					"		Answers.sthree,\n" + 
					"		Answers.sfour,\n" + 
					"		ProjectChar.charzero,\n" + 
					"		ProjectChar.charone\n" + 
					"from Respondents\n" + 
					"	left join Answers on Respondents.name = Answers.name\n" + 
					"	left join ProjectChar on ProjectChar.name = Answers.name;";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query1);
			while (resultSet.next()) {
				allInfo.add(new AllInfoRow(resultSet.getString("Respondents.name"), resultSet.getInt("Answers.sone"),
						resultSet.getInt("Answers.stwo"), resultSet.getInt("Answers.sthree"),
						resultSet.getInt("Answers.sfour"), resultSet.getInt("ProjectChar.charzero"),
						+resultSet.getInt("ProjectChar.charone")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allInfo;
	}

	public ArrayList<Respondent> getRespondents() {
		ArrayList<Respondent> respondents = new ArrayList<>();

		try {
			String query = "select * from Respondents";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				respondents.add(new Respondent(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respondents;
	}

}