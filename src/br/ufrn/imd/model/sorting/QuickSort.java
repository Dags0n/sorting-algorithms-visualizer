package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Quick Sort.
 *
 * <p>O Quick Sort é um algoritmo eficiente de ordenação que usa a estratégia
 * "Dividir e Conquistar". Ele seleciona um elemento como pivô, particiona o
 * array em dois subarrays com base no pivô e ordena os subarrays recursivamente.</p>
 */
public class QuickSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe QuickSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public QuickSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Quick Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Método recursivo que realiza o processo de divisão e ordenação do array.
     *
     * @param array o array a ser ordenado
     * @param low o índice inicial da seção do array
     * @param high o índice final da seção do array
     */
    private void quickSort(int[] array, int low, int high) {
        checkPause();
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Particiona o array em relação ao pivô, colocando elementos menores à esquerda
     * e elementos maiores à direita.
     *
     * @param array o array a ser particionado
     * @param low o índice inicial da seção do array
     * @param high o índice final da seção do array
     * @return o índice do pivô após a partição
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            checkPause();
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
                visualizer.setArray(array);
                Sleep.sleep(delay);
            }
        }

        swap(array, i + 1, high);
        visualizer.setArray(array);
        Sleep.sleep(delay);

        return i + 1;
    }
}
