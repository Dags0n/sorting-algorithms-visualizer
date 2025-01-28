package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa um diálogo para seleção de algoritmo de ordenação.
 *
 * <p>Essa classe exibe uma interface gráfica que permite ao usuário escolher
 * um algoritmo de ordenação para ser executado.</p>
 */
public class AlgorithmSelectionDialog extends JDialog {
    private int[] array;

    /**
     * Construtor da classe AlgorithmSelectionDialog.
     *
     * @param parent o JFrame pai que está exibindo este diálogo
     * @param array o array a ser ordenado
     * @param delay o tempo de atraso em milissegundos para visualização do algoritmo
     */
    public AlgorithmSelectionDialog(JFrame parent, int[] array, int delay) {
        super(parent, "Selecionar algoritmo", true);
        this.array = array;

        setSize(350, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Escolha o algoritmo de ordenação:");
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{
                "BubbleSort", "MergeSort", "BogoSort", "QuickSort",
                "SelectionSort", "InsertionSort", "HeapSort", "ShellSort", "RadixSort"
        });
        algorithmComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(new Color(245, 245, 245));

        okButton.addActionListener(e -> {
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            dispose();
            new SortingVisualizerWindow(array, delay, selectedAlgorithm);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(label, gbc);

        gbc.gridy = 1;
        mainPanel.add(algorithmComboBox, gbc);

        gbc.gridy = 2;
        mainPanel.add(okButton, gbc);

        add(mainPanel);
        setVisible(true);
    }
}
