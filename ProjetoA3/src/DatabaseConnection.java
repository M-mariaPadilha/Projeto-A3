import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost/dbnumeros";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.err.println("Falha ao estabelecer conexão com o banco de dados.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Erro ao conectar ao banco de dados.\n" + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void salvarNumeros(List<String> numeros) {
        if (connection == null) {
            System.err.println("Conexão com o banco de dados não inicializada.");
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO numeros (numero) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String numero : numeros) {
                stmt.setString(1, numero);
                stmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Números salvos com sucesso no banco de dados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace(); 
            String errorMessage = "Erro ao salvar números no banco de dados.\n" + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparNumeros() {
        if (connection == null) {
            System.err.println("Conexão com o banco de dados não inicializada.");
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String sql = "DELETE FROM numeros";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Erro ao limpar números do banco de dados.\n" + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }    
}
