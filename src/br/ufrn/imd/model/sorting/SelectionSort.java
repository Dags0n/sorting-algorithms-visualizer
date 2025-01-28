package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Selection Sort.
 *
 * <p>O Selection Sort é um algoritmo de ordenação simples que percorre o array
 * para encontrar o menor elemento e o coloca na posição correta. Esse processo
 * é repetido para cada posição do array até que ele esteja ordenado.</p>
 */
public class SelectionSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe SelectionSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public SelectionSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Selection Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            checkPause();

            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                checkPause();
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
                visualizer.setArray(array);
                Sleep.sleep(delay);
            }
        }
    }
}
