import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    static Connection conn = null;

    public static void main(String[] args) {
        InsertData();
        GetData();
    }

    public static void dbConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DB_URL = "jdbc:mysql://localhost/exam";
            final String USER = "root";
            final String PASS = "1234";

            // Establish the connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection established successfully!");

        } catch (Exception e) {
            System.out.println("Error: SQL exception - " + e.getMessage());
        }
    }

    public static void InsertData() {
        dbConnection();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                // Insert data into the exam table
                String sql1 = "INSERT INTO exam (name, examtype) VALUES ('Alice', 'Midterm'),('Bob', 'Final'),('Charlie', 'Quiz')";

                stmt.executeUpdate(sql1);

                System.out.println("Data inserted successfully!");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error: SQL exception - " + e.getMessage());
        }
    }

    public static void GetData() {
        dbConnection();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                String sql = "SELECT * FROM exam";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("name") + ", Exam Type: " + rs.getString("examtype"));
                }
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error: SQL exception - " + e.getMessage());
        }
    }
}

/*
 * Compile:
 * javac -cp .;mysql-connector-j-8.4.0.jar Test.java
 * 
 * Run:
 * java -cp .;mysql-connector-j-8.4.0.jar Test
 */
