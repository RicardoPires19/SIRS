import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SQLProcedures {

	// JDBC driver name and database URL
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://192.168.56.104:3306/leiloes_sirs"
			+"?verifyServerCertificate=false"
			+"&useSSL=true"
			+"&requireSSL=true";


	//  Database credentials
//	final String USER = "ssluser";
//	final String PASS = "ssluser";
	
	 String USER="root";
	 String PASS="numero15395";
	

	public SQLProcedures() {
		 USER="root";
		  PASS="numero15395";
	}
	
	public SQLProcedures(String user,String pass){
		USER=user;
		PASS=pass;
	}

	private ResultSet getFromDB(String query, String param) throws ClassNotFoundException, SQLException{
		Connection myConn = null;
		Class.forName(JDBC_DRIVER);
		myConn = DriverManager.getConnection(DB_URL, USER, PASS);

		PreparedStatement myStmt = myConn.prepareStatement(query);

		myStmt.setString(1, param);

		return myStmt.executeQuery();
	}
	
	private ResultSet getFromDB(String query, int param) throws ClassNotFoundException, SQLException{
		Connection myConn = null;
		Class.forName(JDBC_DRIVER);
		myConn = DriverManager.getConnection(DB_URL, USER, PASS);

		PreparedStatement myStmt = myConn.prepareStatement(query);

		myStmt.setInt(1, param);

		return myStmt.executeQuery();
	}

	private ResultSet getFromDB(String query) throws ClassNotFoundException, SQLException{
		Connection myConn = null;
		Class.forName(JDBC_DRIVER);
		myConn = DriverManager.getConnection(DB_URL, USER, PASS);


		Statement myStmt = myConn.createStatement();

		return myStmt.executeQuery(query);
	}

	private Connection getConn() throws ClassNotFoundException, SQLException{
		Connection myConn = null;
		Class.forName(JDBC_DRIVER);
		myConn = DriverManager.getConnection(DB_URL, USER, PASS);

		return myConn;
	}

	public void updateBid(int idLeilao, int idUser, int bid){
		try {
			String sql = "update leiloes set Highest_bidder = ?, Highest_bid = ? where Id = ?;";

			Connection myConn = getConn();

			PreparedStatement myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, idUser);
			myStmt.setInt(2, bid);
			myStmt.setInt(3, idLeilao);

			myStmt.executeUpdate();

			System.out.println("leilao updated with new bid.");

		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
	}

	public void insertLeilao( int hBidder, int owner, int hBid, Date date, String temDescription){
		try {
			String sql = "INSERT INTO leiloes( Highest_bidder, Owner, Highest_bid, End_date, ItemDescription) "
					+ "VALUES( ?, ?, ?, ?, ?);";

			Connection myConn = getConn();

			PreparedStatement myStmt = myConn.prepareStatement(sql);

		
			myStmt.setInt(1, hBidder);
			myStmt.setInt(2, owner);
			myStmt.setInt(3, hBid);
			myStmt.setDate(4, date);
			myStmt.setString(5, temDescription);

			myStmt.executeUpdate();
			System.out.println("leilao inserted.");

		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
	}

	public List<leilao> listLeiloes(){


		try {
			String sql = "select * from leiloes";

			ResultSet myRs = getFromDB(sql);

			List<leilao> leiloes = new ArrayList<leilao>(50);
			
			while (myRs.next()) {
				System.out.println("Id: " + myRs.getString("Id") + ", "
						+"Highest Bidder: " + myRs.getString("Highest_bidder") + ", "
						+"Owner: " + myRs.getString("Owner") + ", "
						+"Highest Bid: " + myRs.getString("Highest_Bid") + ", "
						+"End date: " + myRs.getString("End_date") + ", "
						+"ItemDescription: " + myRs.getString("ItemDescription")
						);
					leiloes.add(new leilao(myRs.getInt("Id"), 
											myRs.getInt("Owner"), 
											myRs.getInt("Highest_bidder"), 
											myRs.getInt("Highest_Bid"), 
											myRs.getDate("End_date"), 
											myRs.getString("ItemDescription")));
			}
			
			return leiloes;
		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
		return null;
	}

	public User getUserByEmail(String email){

		try {

			String sql = "select * from users where Email = ?";

			ResultSet myRs = getFromDB(sql, email);
			if (myRs.next()) {
				System.out.println("Id: " + myRs.getString("Id") + ", "
						+"First Name: " + myRs.getString("First_Name") + ", "
						+"Surname: " + myRs.getString("Surname") + ", "
						+"Password: " + myRs.getString("Password") + ", "
						+"Email: " + myRs.getString("Email") + ", "
						+"Credit: " + myRs.getString("Credit") +", "
						+"Salt: " + myRs.getString("Salt") +", "
						);
			}
			
			return new User(myRs.getInt("Id"), 
							myRs.getString("First_Name"), 
							myRs.getString("Surname"), 
							myRs.getString("Password"),
							myRs.getString("Email"), 
							myRs.getInt("Credit"),
							myRs.getString("Salt")
							);
		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
		return null;

	}

	public leilao getAuctionById(int id) {
		try {

			String sql = "select * from leiloes where Id = ?";

			ResultSet myRs = getFromDB(sql, id);
			if (myRs.next()){
			System.out.println("Id: " + myRs.getString("Id") + ", "
					+"Highest Bidder: " + myRs.getString("Highest_bidder") + ", "
					+"Owner: " + myRs.getString("Owner") + ", "
					+"Highest Bid: " + myRs.getString("Highest_Bid") + ", "
					+"End date: " + myRs.getString("End_date") + ", "
					+"ItemDescription: " + myRs.getString("ItemDescription")
					);
			return new leilao(myRs.getInt("Id"), 
					myRs.getInt("Owner"), 
					myRs.getInt("Highest_bidder"), 
					myRs.getInt("Highest_Bid"), 
					myRs.getDate("End_date"), 
					myRs.getString("ItemDescription"));
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
		return null;
		
	}
	
	public void insertUser(String firstName, String surname, String password, String email,String salt){
		try {
			String sql = "INSERT INTO users (First_Name,Surname,Password,Email,Salt) "
					+ "VALUES(?, ?, ?,?,?);";

			Connection myConn = getConn();

			PreparedStatement myStmt = myConn.prepareStatement(sql);

			
			myStmt.setString(1, firstName);
			myStmt.setString(2, surname);
			myStmt.setString(3, password);
			myStmt.setString(4, email);
			myStmt.setString(5, salt);

			int num = myStmt.executeUpdate();

			System.out.println(num +" user(s) inserted.");

		} catch (ClassNotFoundException e) {
			System.err.println("Erro 0: " + e);
		} catch (SQLException e) {
			System.err.println("Erro 1: " + e);
		}
	}
}
