import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SQL{

    String url = "jdbc:mysql://localhost:3306/myDB";
    String username = "root";
    String password = "password";

    public void insertPatientsData(Patient patient){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String sql = "INSERT INTO Patients (PatientID, Name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getName());

            statement.executeUpdate();
            statement.close();
            System.out.println("Patient data inserted successfully.");
        }catch(SQLException e){
            System.out.println("Failed to insert patient data.");
            e.printStackTrace();
        }
    }
}
