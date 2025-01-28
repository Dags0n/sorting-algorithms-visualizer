package br.ufrn.imd.view;

import br.ufrn.imd.model.sorting.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a janela de visualização de ordenação.
 *
 * <p>A `SortingVisualizerWindow` permite ao usuário observar o funcionamento de
 * diferentes algoritmos de ordenação em tempo real, com suporte para iniciar,
 * pausar, retomar e resetar o processo de ordenação.</p>
 */
public class SortingVisualizerWindow extends JFrame {
    private SortingVisualizer visualizer;
    private Sorting sortingAlgorithm;
    private int[] array;
    private int[] originalArray;
    private String currentAlgorithm;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private int delay;

    Thread startThread = new Thread(() -> sortingAlgorithm.sort(array));

    /**
     * Construtor da classe `SortingVisualizerWindow`.
     *
     * @param array o array a ser ordenado
     * @param delay o tempo de atraso (em milissegundos) entre as etapas de visualização
     * @param algorithm o nome do algoritmo de ordenação selecionado
     */
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

    /**
     * Inicializa os componentes da interface gráfica, incluindo o painel
     * de visualização, botões e os eventos associados.
     */
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
                sortingAlgorithm.pause();
                stopButton.setText("Retomar");
            } else {
                isPaused = false;
                sortingAlgorithm.resume();
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

    /**
     * Inicializa o algoritmo de ordenação com base no nome selecionado.
     *
     * @param algorithm o nome do algoritmo de ordenação selecionado
     */
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

    /**
     * Reseta o array e o estado do visualizador para o estado inicial.
     */
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
