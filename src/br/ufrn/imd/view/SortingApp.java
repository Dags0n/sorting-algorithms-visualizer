package br.ufrn.imd.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import br.ufrn.imd.utils.ArrayGenerator;

/**
 * Classe principal para a interface de visualização de ordenação.
 *
 * <p>A `SortingApp` permite ao usuário configurar parâmetros, como tamanho do array e
 * atraso entre as etapas de visualização, e gerar arrays para serem ordenados.
 * Os usuários podem optar por inserir arrays manualmente ou gerá-los automaticamente.</p>
 */
public class SortingApp extends JFrame {
    private int[] array;
    private int delay;
    private boolean isInserting = false;

    /**
     * Construtor da classe `SortingApp`.
     *
     * <p>Inicializa a interface gráfica e configura as propriedades principais
     * da janela, como tamanho, título e comportamento ao fechar.</p>
     */
    public SortingApp() {
        setTitle("Visualizador de Ordenações");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    /**
     * Inicializa a interface gráfica da aplicação.
     *
     * <p>Define o layout da janela, painéis, botões e suas respectivas
     * funcionalidades, como geração e exibição do array.</p>
     */
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
                    arr = ArrayGenerator.generateRandomArray(arrSize, 0, 100);
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
}
