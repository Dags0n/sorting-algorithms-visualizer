package br.ufrn.imd.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("VisualSort");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Painel principal com layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            // Cria um fundo personalizado em gradiente
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();

                // Gradiente suave de cor
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(70, 130, 180), // Azul steel
                        0, h, new Color(230, 230, 250) // Lavanda clara
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };

        // Painel superior com título
        JLabel titleLabel = new JLabel("Bem vindo ao VisualSort!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel central: texto explicativo e botões
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        // Descrição inicial
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center; padding: 0 20px;'>" +
                "<h2 style='margin-bottom:10px;'>Visualize e Aprenda</h2>" +
                "<p>Explore algoritmos de ordenação e veja como eles funcionam passo a passo!</p>" +
                "<p>Escolha entre diversos métodos, observe suas etapas e compare a eficiência.</p>" +
                "</div></html>", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.DARK_GRAY);

        centerPanel.add(descriptionLabel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        // Criação dos botões com ícones (opcionais)
        JButton startButton = createButton("Visualizar Ordenações", "../assets/start_icon.png");
        JButton infoButton = createButton("Informações sobre os algoritmos", "../assets/info_icon.png");
        JButton exitButton = createButton("Sair", "../assets/exit_icon.png");

        // Ações dos botões
        startButton.addActionListener(e -> {
            new SortingApp().setVisible(true);
            dispose();
        });

        infoButton.addActionListener(e -> {
            new InfoWindow().setVisible(true);
            dispose();
        });

        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(exitButton);

        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Rodapé (opcional)
        JLabel footerLabel = new JLabel("© 2024 VisualSort - Desenvolvido por Dagson Gabriel", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setForeground(Color.DARK_GRAY);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            // Se não encontrar o ícone, apenas continue
        }
        button.setFocusPainted(false);
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
