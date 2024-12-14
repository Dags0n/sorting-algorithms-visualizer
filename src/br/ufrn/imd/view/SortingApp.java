package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;
import br.ufrn.imd.model.sorting.*;
import br.ufrn.imd.utils.DataGenerator;

public class SortingApp extends JFrame {
    private int[] array;
    private int delay;

    public SortingApp() {
        setTitle("Sorting Visualizer");
        setSize(1368, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Painel para gerar o array
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Array Size:");
        JTextField arraySizeField = new JTextField(10);
        JLabel delayLabel = new JLabel("Delay:");
        JTextField delayField = new JTextField(10);
        JButton generateButton = new JButton("Generate");
        JLabel arrayLabel = new JLabel("Array: ");
        JButton goToSelectSortingButton = new JButton("Go To Select Sorting");

        generateButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(arraySizeField.getText());
                int[] arr = DataGenerator.generateRandomArray(size, 0, 100);
                this.array = arr;

                int delay = Integer.parseInt(delayField.getText());
                this.delay = delay;

                arrayLabel.setText("Array: " + java.util.Arrays.toString(arr) + "\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        });

        // Após gerar o array, abrir a nova tela de seleção de tipo de ordenação
        goToSelectSortingButton.addActionListener(event -> {
            if (java.util.Arrays.equals(this.array, null)) {
                JOptionPane.showMessageDialog(this, "Please generate an array.");
                return;
            }
            new AlgorithmSelectionDialog(this, array, delay);
        });

        panel.add(label);
        panel.add(arraySizeField);
        panel.add(delayLabel);
        panel.add(delayField);
        panel.add(generateButton);
        panel.add(arrayLabel);
        panel.add(goToSelectSortingButton);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingApp app = new SortingApp();
            app.setVisible(true);
        });
    }
}

// Diálogo de seleção de algoritmo de ordenação
class AlgorithmSelectionDialog extends JDialog {
    private int[] array;
    private JComboBox<String> algorithmComboBox;

    public AlgorithmSelectionDialog(JFrame parent, int[] array, int delay) {
        super(parent, "Choose Sorting Algorithm", true);
        this.array = array;

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Define o layout como GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 15, 5); // Margens internas para melhor aparência

        JLabel label = new JLabel("Select Algorithm:");
        algorithmComboBox = new JComboBox<>(new String[]{"BubbleSort", "MergeSort", "BogoSort"});
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> {
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            dispose();
            new SortingVisualizerWindow(array, delay, selectedAlgorithm);
        });

        // Adicionando o label
        add(label, gbc);

        // Próxima linha
        gbc.gridy++;
        add(algorithmComboBox, gbc);

        // Próxima linha
        gbc.gridy++;
        add(okButton, gbc);

        setVisible(true);
    }

}

// Janela de visualização, com botões Start e Reset
class SortingVisualizerWindow extends JFrame {
    private SortingVisualizer visualizer;
    private Sorting sortingAlgorithm;
    private int[] array;
    private int[] originalArray; // guarda o estado original do array
    private String currentAlgorithm; // guarda o algoritmo selecionado
    private int delay;

    public SortingVisualizerWindow(int[] array, int delay, String algorithm) {
        setTitle("Sorting Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Salva o array e o originalArray
        this.array = array.clone();
        this.delay = delay;
        this.originalArray = array.clone();
        this.currentAlgorithm = algorithm;

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Inicializa o visualizador
        visualizer = new SortingVisualizer(array);

        // Inicializa o algoritmo de acordo com a escolha do usuário
        initializeAlgorithm(currentAlgorithm);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");

        startButton.addActionListener(e -> {
            new Thread(() -> {
                sortingAlgorithm.sort(array);
            }).start();
        });

        resetButton.addActionListener(e -> {
            resetToInitialState();
        });

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        add(visualizer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeAlgorithm(String algorithm) {
        switch (algorithm) {
            case "BubbleSort":
                sortingAlgorithm = new BubbleSort(visualizer, delay);
                break;
            case "MergeSort":
                sortingAlgorithm = new MergeSort(visualizer, delay);
                break;
            case "BogoSort":
                sortingAlgorithm = new BogoSort(visualizer, delay);
                break;
        }
    }

    private void resetToInitialState() {
        // Restaura o array original
        this.array = originalArray.clone();

        // Remove o visualizador atual
        getContentPane().remove(visualizer);

        // Cria um novo visualizador com o array original
        visualizer = new SortingVisualizer(array);
        getContentPane().add(visualizer, BorderLayout.CENTER);

        // Reinicializa o algoritmo de ordenação
        initializeAlgorithm(this.currentAlgorithm);

        // Atualiza o layout
        revalidate();
        repaint();
    }
}
