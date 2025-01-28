package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela visualização do array durante o processo de ordenação.
 *
 * <p>A `SortingVisualizer` exibe o array como um conjunto de barras, onde o comprimento
 * de cada barra é proporcional ao valor do elemento correspondente no array.</p>
 */
public class SortingVisualizer extends JPanel {
    private int[] array;

    /**
     * Construtor da classe `SortingVisualizer`.
     *
     * @param array o array a ser visualizado
     */
    public SortingVisualizer(int[] array) {
        this.array = array;
    }

    /**
     * Retorna o array atual sendo visualizado.
     *
     * @return o array atual
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Atualiza o array e solicita a repintura do painel.
     *
     * @param array o novo array a ser visualizado
     */
    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }

    /**
     * Método responsável por desenhar os elementos do array como barras no painel.
     *
     * @param g o objeto Graphics usado para renderizar os elementos
     */
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
                g.drawString(valueText, i * barWidth + (barWidth - textWidth) / 2, height - barHeight - 5);
            }
        }
    }

    /**
     * Obtém o maior valor no array para calcular a altura proporcional das barras.
     *
     * @return o maior valor presente no array
     */
    private int getMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            max = Math.max(max, value);
        }
        return max;
    }
}
