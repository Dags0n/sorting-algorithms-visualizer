package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {

    public InfoWindow() {
        setTitle("Sorting Algorithms Information");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Títulos e descrições dos algoritmos
        mainPanel.add(createAlgorithmPanel(
                "Bubble Sort",
                "Bubble Sort é um algoritmo simples de ordenação que compara pares de elementos adjacentes e os troca se estiverem fora de ordem.",
                "Complexidade de Tempo: O(n²) no pior caso.\nComplexidade de Espaço: O(1).\nÉ simples, mas ineficiente para grandes conjuntos de dados."
        ));

        mainPanel.add(createAlgorithmPanel(
                "Merge Sort",
                "Merge Sort é um algoritmo de ordenação baseado na técnica 'divide e conquista'. Ele divide o array em subarrays menores, ordena-os e os combina.",
                "Complexidade de Tempo: O(n log n) no pior caso.\nComplexidade de Espaço: O(n).\nÉ eficiente, mas requer espaço extra."
        ));

        mainPanel.add(createAlgorithmPanel(
                "Bogo Sort",
                "Bogo Sort é um algoritmo ineficiente que gera permutações aleatórias do array até que ele esteja ordenado.",
                "Complexidade de Tempo: O((n-1)!) no pior caso.\nComplexidade de Espaço: O(1).\nÉ extremamente ineficiente e usado apenas para fins educacionais."
        ));

        JScrollPane scrollPane = new JScrollPane(mainPanel); // Permite rolar a janela caso as informações sejam muitas
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createAlgorithmPanel(String title, String description, String complexity) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JTextArea textArea = new JTextArea(description + "\n\n" + complexity);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        panel.add(textArea, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setVisible(true);
        });
    }
}
