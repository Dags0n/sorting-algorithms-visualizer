package br.ufrn.imd.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import br.ufrn.imd.model.sorting.*;
import br.ufrn.imd.utils.DataGenerator;

public class SortingApp extends JFrame {
    private int[] array;
    private int delay;
    private boolean isInserting = false;

    public SortingApp() {
        setTitle("Visualizador de Ordenações");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Painel principal com gradiente
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(70, 130, 180),
                        0, h, new Color(230, 230, 250)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(mainPanel);

        // Cabeçalho
        JLabel titleLabel = new JLabel("Visualize uma ordenação!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel central - BoxLayout para organizar verticalmente
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Instruções
        JLabel instructions = new JLabel("<html><center>Insira o tamanho do array e o delay entre as etapas da visualização.<br>" +
                "Em seguida, gere o array e prossiga para escolher o algoritmo de ordenação.</center></html>");
        instructions.setFont(new Font("Arial", Font.PLAIN, 14));
        instructions.setForeground(Color.DARK_GRAY);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(new EmptyBorder(10, 10, 30, 10));
        centerPanel.add(instructions);

        // Painel para Tamanho do Array
        JPanel arraySizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        arraySizePanel.setOpaque(false);
        JLabel arraySizeLabel = new JLabel("Tamanho do Array:    ");
        arraySizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        arraySizeLabel.setForeground(Color.BLACK);
        JTextField arraySizeField = new JTextField(10);
        arraySizeField.setToolTipText("Ex: 50");
        arraySizePanel.add(arraySizeLabel);
        arraySizePanel.add(arraySizeField);
        centerPanel.add(arraySizePanel);

        // Painel para Delay
        JPanel delayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        delayPanel.setOpaque(false);
        JLabel delayLabel = new JLabel("Tempo de Delay (ms):");
        delayLabel.setFont(new Font("Arial", Font.BOLD, 14));
        delayLabel.setForeground(Color.BLACK);
        JTextField delayField = new JTextField(10);
        delayField.setToolTipText("Ex: 100 (100 ms de atraso)");
        delayPanel.add(delayLabel);
        delayPanel.add(delayField);
        centerPanel.add(delayPanel);
        centerPanel.add(Box.createVerticalStrut(10)); // espaço

        JPanel arrayPanel = new JPanel(new FlowLayout());
        arrayPanel.setOpaque(false);

        // Botão Gerar Array
        JButton generateButton = new JButton("Gerar Array");
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setBackground(new Color(245, 245, 245));
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        arrayPanel.add(generateButton);

        // Switch para Inserir Array
        JToggleButton arraySwitch = new JToggleButton("Inserir: OFF");
        arraySwitch.setFont(new Font("Arial", Font.BOLD, 14));
        arraySwitch.setBackground(new Color(245, 245, 245));
        arraySwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
        arrayPanel.add(arraySwitch);

        centerPanel.add(arrayPanel);
        centerPanel.add(Box.createVerticalStrut(10)); // espaço

        // Label que mostra o array gerado
        JLabel arrayLabel = new JLabel("Array: []");
        arrayLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        arrayLabel.setForeground(Color.DARK_GRAY);
        arrayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(arrayLabel);
        centerPanel.add(Box.createVerticalStrut(10)); // espaço

        // Botão Selecionar Algoritmo
        JButton goToSelectSortingButton = new JButton("Selecionar Algoritmo de Ordenação");
        goToSelectSortingButton.setFont(new Font("Arial", Font.BOLD, 14));
        goToSelectSortingButton.setBackground(new Color(245, 245, 245));
        goToSelectSortingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToSelectSortingButton.setEnabled(false);
        centerPanel.add(goToSelectSortingButton);
        centerPanel.add(Box.createVerticalStrut(10)); // espaço

        // Botão para voltar para Home
        JButton backToHomeButton = new JButton("Voltar");
        backToHomeButton.setFont(new Font("Arial", Font.BOLD, 14));
        backToHomeButton.setBackground(new Color(245, 245, 245));
        backToHomeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(backToHomeButton);

        // Ações dos botões
        generateButton.addActionListener(e -> {
            try {
                String inputText = arraySizeField.getText();
                int[] arr;

                if (isInserting) {
                    String[] stringArray = inputText.split(",");
                    int size = stringArray.length;
                    int[] newArray = new int[size];
                    for (int i = 0; i < size; i++) {
                        newArray[i] = Integer.parseInt(stringArray[i].trim()); // Remover espaços em branco
                    }
                    arr = newArray;
                } else {
                    int arrSize = Integer.parseInt(inputText);
                    arr = DataGenerator.generateRandomArray(arrSize, 0, 100);
                }
                this.array = arr;

                int d = Integer.parseInt(delayField.getText());
                this.delay = d;

                int elementsToShow = Math.min(arr.length, 20);
                StringBuilder sb = new StringBuilder("Array: [");
                for (int i = 0; i < elementsToShow; i++) {
                    sb.append(arr[i]);
                    if (i < elementsToShow - 1) sb.append(", ");
                }
                if (arr.length > 20) {
                    sb.append(" ...");
                }
                sb.append("]");
                arrayLabel.setText(sb.toString());
                goToSelectSortingButton.setEnabled(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor insira um valor válido para o " + (isInserting ? "array" : "tamanho") + " e o delay!");
            }
        });

        arraySwitch.addActionListener(e -> {
            if (arraySwitch.isSelected()) {
                arraySwitch.setText("Inserir Array: ON");
                arraySizeLabel.setText("Insira o Array:             ");
                arraySizeField.setText("");
                arraySizeField.setToolTipText("EX: 1, 42, 7");
                isInserting = true;
            } else {
                arraySwitch.setText("Inserir Array: OFF");
                arraySizeLabel.setText("Tamanho do Array:    ");
                arraySizeField.setText("");
                arraySizeField.setToolTipText("Ex: 50");
                isInserting = false;
            }
        });

        goToSelectSortingButton.addActionListener(event -> {
            if (this.array == null) {
                JOptionPane.showMessageDialog(this, "Você precisa gerar um array antes de prosseguir!");
                return;
            }
            new AlgorithmSelectionDialog(this, array, delay);
        });

        backToHomeButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingApp app = new SortingApp();
            app.setVisible(true);
        });
    }
}


// Diálogo de seleção de algoritmo
class AlgorithmSelectionDialog extends JDialog {
    private int[] array;

    public AlgorithmSelectionDialog(JFrame parent, int[] array, int delay) {
        super(parent, "Selecionar algoritmo", true);
        this.array = array;

        setSize(350, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Escolha o algoritmo de ordenação:");
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{"BubbleSort", "MergeSort", "BogoSort", "QuickSort", "SelectionSort", "InsertionSort", "HeapSort", "ShellSort", "RadixSort"});
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

// Janela de visualização
class SortingVisualizerWindow extends JFrame {
    private SortingVisualizer visualizer;
    private Sorting sortingAlgorithm;
    private int[] array;
    private int[] originalArray;
    private String currentAlgorithm;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private int delay;
    Thread startThread = new Thread(() -> {
        sortingAlgorithm.sort(array);
    });

    public SortingVisualizerWindow(int[] array, int delay, String algorithm) {
        setTitle("Visualização de ordenação");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.array = array.clone();
        this.delay = delay;
        this.originalArray = array.clone();
        this.currentAlgorithm = algorithm;

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        visualizer = new SortingVisualizer(array);
        initializeAlgorithm(currentAlgorithm);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(new Color(230, 230, 250));
        JLabel infoLabel = new JLabel("Algoritmo usado: " + currentAlgorithm);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(infoLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        JButton startButton = new JButton("Começar");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton stopButton = new JButton("Pausar");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton resetButton = new JButton("Resetar");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        startButton.setBackground(new Color(200, 255, 200));
        stopButton.setBackground(new Color(255, 255, 200));
        resetButton.setBackground(new Color(255, 200, 200));
        backButton.setBackground(new Color(200, 255, 200));

        startButton.addActionListener(e -> {
            if (isRunning) {
                return;
            }
            isRunning = true;
            startThread = new Thread(() -> sortingAlgorithm.sort(array));
            startThread.start();
        });

        stopButton.addActionListener(e -> {
            if (isRunning && !isPaused) {
                isPaused = true;
                sortingAlgorithm.pause(); // Método para pausar a execução.
                stopButton.setText("Retomar");
            } else {
                isPaused = false;
                sortingAlgorithm.resume(); // Método para retomar a execução.
                stopButton.setText("Pausar");
            }
        });


        resetButton.addActionListener(e -> {
            stopButton.setText("Pausar");
            resetToInitialState();
        });

        backButton.addActionListener(e -> dispose());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
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
            case "QuickSort":
                sortingAlgorithm = new QuickSort(visualizer, delay);
                break;
            case "SelectionSort":
                sortingAlgorithm = new SelectionSort(visualizer, delay);
                break;
            case "InsertionSort":
                sortingAlgorithm = new InsertionSort(visualizer, delay);
                break;
            case "HeapSort":
                sortingAlgorithm = new HeapSort(visualizer, delay);
                break;
            case "ShellSort":
                sortingAlgorithm = new ShellSort(visualizer, delay);
                break;
            case "RadixSort":
                sortingAlgorithm = new RadixSort(visualizer, delay);
                break;
        }
    }

    private void resetToInitialState() {
        this.array = originalArray.clone();
        getContentPane().remove(visualizer);
        visualizer = new SortingVisualizer(array);
        getContentPane().add(visualizer, BorderLayout.CENTER);
        initializeAlgorithm(this.currentAlgorithm);
        isRunning = false;
        isPaused = false;
        revalidate();
        repaint();
    }
}
