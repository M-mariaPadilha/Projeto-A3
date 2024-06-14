import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoasVindasGUI extends JFrame {

    private JTextField numberField; // Campo de texto para inserção do número de linhas
    private JLabel maxLabel; // Rótulo para exibir o número máximo de linhas no arquivo
    private int maxLines; // Variável para armazenar o número máximo de linhas
    private String nomeArquivo = "ProjetoA3//src//files//NumerosOrdenarArquivo.txt"; // Nome do arquivo a ser lido

    public BoasVindasGUI() {
        setTitle("Bem-vindo"); // Define o título da janela
        setSize(400, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela

        setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

        JPanel welcomePanel = new JPanel(); // Cria um painel para o conteúdo inicial
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS)); // Define o layout do painel como BoxLayout vertical

        JLabel welcomeLabel = new JLabel("Bem-vindo ao Organizador de Números!"); // Cria um rótulo de boas-vindas
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha o rótulo ao centro horizontalmente
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18)); // Define a fonte do rótulo
        welcomePanel.add(welcomeLabel); // Adiciona o rótulo ao painel

        JLabel instructionLabel = new JLabel("Selecione quantas linhas deseja ler do arquivo:"); // Rótulo de instrução
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha ao centro horizontalmente
        welcomePanel.add(instructionLabel); // Adiciona ao painel

        numberField = new JTextField(5); // Cria um campo de texto para inserir o número de linhas desejado
        numberField.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha ao centro horizontalmente
        welcomePanel.add(numberField); // Adiciona ao painel

        maxLabel = new JLabel(); // Rótulo para exibir o número máximo de linhas
        maxLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha ao centro horizontalmente
        welcomePanel.add(maxLabel); // Adiciona ao painel

        JButton startButton = new JButton("Iniciar"); // Botão "Iniciar"
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha ao centro horizontalmente
        startButton.addActionListener(new StartButtonListener()); // Adiciona um ouvinte de ação ao botão
        welcomePanel.add(startButton); // Adiciona ao painel

        add(welcomePanel, BorderLayout.CENTER); // Adiciona o painel ao centro da janela
        maxLines = calcularMaxLinhas(nomeArquivo); // Calcula o número máximo de linhas no arquivo
        maxLabel.setText("Máximo de linhas disponíveis: " + maxLines); // Define o texto do rótulo de número máximo de linhas
    }

    // Método para calcular o número máximo de linhas no arquivo
    private int calcularMaxLinhas(String nomeArquivo) {
        int count = 0; // Inicializa o contador de linhas
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) { // Abre o arquivo para leitura
            while (br.readLine() != null) { // Lê cada linha do arquivo
                count++; // Incrementa o contador de linhas
            }
        } catch (IOException e) { // Trata exceções de leitura de arquivo
            e.printStackTrace(); // Imprime o rastreamento da pilha de erros
        }
        return count; // Retorna o número total de linhas no arquivo
    }

    // Classe interna para lidar com eventos do botão "Iniciar"
    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int numLinhas = Integer.parseInt(numberField.getText()); // Obtém o número de linhas digitado
                if (numLinhas > 0 && numLinhas <= maxLines) { // Verifica se o número de linhas é válido
                    SwingUtilities.invokeLater(() -> {
                        AppGUI app = new AppGUI(numLinhas); // Cria uma nova instância de AppGUI com o número de linhas
                        app.setVisible(true); // Torna a janela visível
                    });
                    dispose(); // Fecha a janela atual (BoasVindasGUI)
                } else {
                    // Exibe uma mensagem de erro se o número de linhas não for válido
                    JOptionPane.showMessageDialog(BoasVindasGUI.this, "Por favor, insira um número válido entre 1 e " + maxLines, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Exibe uma mensagem de erro se o valor inserido não for um número válido
                JOptionPane.showMessageDialog(BoasVindasGUI.this, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
