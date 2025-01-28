package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Bubble Sort.
 *
 * <p>O Bubble Sort é um algoritmo de ordenação simples que percorre o array repetidamente,
 * comparando elementos adjacentes e trocando-os de lugar se estiverem fora de ordem.
 * O processo se repete até que o array esteja ordenado.</p>
 */
public class BubbleSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe BubbleSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public BubbleSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Bubble Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                checkPause(); // Verifica se o algoritmo deve pausar
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    visualizer.setArray(array); // Atualiza a visualização
                    Sleep.sleep(delay); // Adiciona um pequeno atraso para visualização
                }
            }
        }
    }
}
