package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Heap Sort.
 *
 * <p>O Heap Sort é um algoritmo de ordenação baseado na estrutura de dados heap.
 * Ele constrói um heap máximo e, em seguida, extrai o maior elemento do heap repetidamente
 * para ordená-lo.</p>
 */
public class HeapSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe HeapSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public HeapSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Heap Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            checkPause();
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            checkPause();

            swap(array, 0, i);
            visualizer.setArray(array);
            Sleep.sleep(delay);

            heapify(array, i, 0);
        }
    }

    /**
     * Rearranja um subarray para manter a propriedade do heap máximo.
     *
     * @param array o array a ser transformado em heap
     * @param n o tamanho do heap
     * @param i o índice do nó atual
     */
    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(array, i, largest);
            visualizer.setArray(array);
            Sleep.sleep(delay);

            heapify(array, n, largest);
        }
    }
}
