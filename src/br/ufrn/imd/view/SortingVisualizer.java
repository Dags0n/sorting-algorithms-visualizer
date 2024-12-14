package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;
import br.ufrn.imd.model.sorting.*;

public class SortingVisualizer extends JPanel {
    private int[] array;

    public SortingVisualizer(int[] array) {
        this.array = array;
    }

    // Atualiza o array e repinta o painel
    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array != null) {
            int width = getWidth();
            int height = getHeight();
            int barWidth = width / array.length;
            int maxVal = getMaxValue();

            g.setFont(new Font("Arial", Font.BOLD, 12));

            for (int i = 0; i < array.length; i++) {
                int barHeight = (int) ((double) array[i] / maxVal * (height - 20));
                g.setColor(Color.BLUE);
                g.fillRect(i * barWidth, height - barHeight, barWidth - 2, barHeight);

                g.setColor(Color.BLACK);
                String valueText = String.valueOf(array[i]);
                int textWidth = g.getFontMetrics().stringWidth(valueText);
                g.drawString(valueText, i * barWidth + (barWidth - textWidth)/2, height - barHeight - 5);
            }
        }
    }

    private int getMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            max = Math.max(max, value);
        }
        return max;
    }
}

