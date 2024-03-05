import java.sql.*;


public class Conn {

    Connection conn;
    Statement s;

    public Conn() {
        try{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver() );

            String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
            conn = DriverManager.getConnection(url, "root", "abc123");
            s = conn.createStatement();

        }catch (Exception e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
    public void closeConnection() {
        try{
            if(s !=null){
                s.close();
            }
            if (conn !=null) {
                conn.close();
                System.out.println("connection closed.");
            }
        }catch(SQLException e){
            System.err.println("error closing the connection" + e.getMessage());
        }
    }
    public static void main(String args[]){
        Conn databaseConnection = new Conn();

    }
}