import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    // URL de conexão com o banco de dados MySQL
    private static final String URL = "jdbc:mysql://localhost/dbnumeros";
    private static final String USER = "root";  // Usuário do banco de dados
    private static final String PASSWORD = "root";  // Senha do banco de dados
    private Connection connection;  // Objeto de conexão com o banco de dados

    // Construtor da classe DatabaseConnection
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Carrega o driver JDBC do MySQL
            connection = DriverManager.getConnection(URL, USER, PASSWORD);  // Estabelece a conexão com o banco de dados

            // Verifica se a conexão foi estabelecida com sucesso
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.err.println("Falha ao estabelecer conexão com o banco de dados.");
            }
        } catch (ClassNotFoundException e) {  // Exceção lançada se o driver JDBC não for encontrado
            e.printStackTrace();  // Imprime o rastreamento da pilha de exceção
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe uma mensagem de erro em uma caixa de diálogo
        } catch (SQLException e) {  // Exceção lançada em caso de erro de SQL
            e.printStackTrace();  // Imprime o rastreamento da pilha de exceção
            String errorMessage = "Erro ao conectar ao banco de dados.\n" + e.getMessage();  // Mensagem de erro detalhada
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe uma mensagem de erro em uma caixa de diálogo
        }
    }

    // Método para salvar uma lista de números no banco de dados
    public void salvarNumeros(List<String> numeros) {
        if (connection == null) {
            System.err.println("Conexão com o banco de dados não inicializada.");
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO numeros (numero) VALUES (?)";  // Query SQL para inserção de números

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // Prepara a declaração SQL para execução
            for (String numero : numeros) {
                stmt.setString(1, numero);  // Define o valor do parâmetro da query
                stmt.executeUpdate();  // Executa a atualização no banco de dados
            }
            JOptionPane.showMessageDialog(null, "Números salvos com sucesso no banco de dados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);  // Exibe uma mensagem de sucesso em uma caixa de diálogo

        } catch (SQLException e) {  // Exceção lançada em caso de erro de SQL
            e.printStackTrace();  // Imprime o rastreamento da pilha de exceção
            String errorMessage = "Erro ao salvar números no banco de dados.\n" + e.getMessage();  // Mensagem de erro detalhada
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe uma mensagem de erro em uma caixa de diálogo
        }
    }

    // Método para limpar todos os números do banco de dados
    public void limparNumeros() {
        if (connection == null) {
            System.err.println("Conexão com o banco de dados não inicializada.");
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String sql = "DELETE FROM numeros";  // Query SQL para deletar todos os números da tabela
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // Prepara a declaração SQL para execução
            stmt.executeUpdate();  // Executa a atualização no banco de dados
    
        } catch (SQLException e) {  // Exceção lançada em caso de erro de SQL
            e.printStackTrace();  // Imprime o rastreamento da pilha de exceção
            String errorMessage = "Erro ao limpar números do banco de dados.\n" + e.getMessage();  // Mensagem de erro detalhada
            JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe uma mensagem de erro em uma caixa de diálogo
        }
    }    
}
