package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Insertion Sort.
 *
 * <p>O Insertion Sort é um algoritmo de ordenação simples e eficiente para pequenos conjuntos de dados.
 * Ele constrói a lista ordenada gradualmente, inserindo cada elemento no lugar correto.</p>
 */
public class InsertionSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe InsertionSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public InsertionSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Insertion Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            checkPause();

            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                checkPause();
                array[j + 1] = array[j];
                j--;

                visualizer.setArray(array);
                Sleep.sleep(delay);
            }

            array[j + 1] = key;
            visualizer.setArray(array);
            Sleep.sleep(delay);
        }
    }
}
