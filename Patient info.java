import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientRecords {

    public static void main(String[] args) {

        String dbURL = "jdbc:mysql://localhost:3306/patient";
        String username = "root";
        String password = "root";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(dbURL, username, password);

            statement = connection.createStatement();

            String query = "SELECT id, name, problem, bill FROM patients";

            resultSet = statement.executeQuery(query);

            System.out.println("Patient Records:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String problem = resultSet.getString("problem");
                double bill = resultSet.getDouble("bill");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Problem: " + problem);
                System.out.println("Bill: $" + bill);
                System.out.println("---------------------");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error: Connection or query execution failed!");
            e.printStackTrace();

        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error: Unable to close resources!");
                e.printStackTrace();
            }
        }
    }
}
