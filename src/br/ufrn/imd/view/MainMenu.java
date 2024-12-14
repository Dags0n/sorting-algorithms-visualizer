package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Welcome to Sorting Visualizer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Mensagem de boas-vindas
        JLabel welcomeLabel = new JLabel("Welcome to the Sorting Visualizer!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Menu com botões
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton startButton = new JButton("Start Sorting Visualization");
        JButton infoButton = new JButton("About Sorting Algorithms");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(e -> {
            // Abrir a tela principal
            new SortingApp().setVisible(true);
            dispose(); // Fecha o menu inicial
        });

        infoButton.addActionListener(e -> {
            // Abrir a janela informativa
            new InfoWindow().setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0)); // Fecha o programa

        buttonPanel.add(startButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
