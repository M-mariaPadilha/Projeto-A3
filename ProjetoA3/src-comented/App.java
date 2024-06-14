import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Executa a interface gráfica Swing em uma thread separada
        SwingUtilities.invokeLater(() -> {
            // Cria e exibe a interface de Boas Vindas
            BoasVindasGUI boasVindasGUI = new BoasVindasGUI();
            boasVindasGUI.setVisible(true);
        });
    }
}
