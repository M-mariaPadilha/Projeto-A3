import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoasVindasGUI extends JFrame {

    private JTextField numberField;
    private JLabel maxLabel;
    private int maxLines;
    private String nomeArquivo = "ProjetoA3//src//files//NumerosOrdenarArquivo.txt";

    public BoasVindasGUI() {
        setTitle("Bem-vindo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Bem-vindo ao Organizador de Números!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        welcomePanel.add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Selecione quantas linhas deseja ler do arquivo:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(instructionLabel);

        numberField = new JTextField(5);
        numberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(numberField);

        maxLabel = new JLabel();
        maxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(maxLabel);

        JButton startButton = new JButton("Iniciar");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new StartButtonListener());
        welcomePanel.add(startButton);

        add(welcomePanel, BorderLayout.CENTER);
        maxLines = calcularMaxLinhas(nomeArquivo);
        maxLabel.setText("Máximo de linhas disponíveis: " + maxLines);
    }

    private int calcularMaxLinhas(String nomeArquivo) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int numLinhas = Integer.parseInt(numberField.getText());
                if (numLinhas > 0 && numLinhas <= maxLines) {
                    SwingUtilities.invokeLater(() -> {
                        AppGUI app = new AppGUI(numLinhas);
                        app.setVisible(true);
                    });
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BoasVindasGUI.this, "Por favor, insira um número válido entre 1 e " + maxLines, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BoasVindasGUI.this, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
