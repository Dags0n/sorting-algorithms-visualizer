package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Merge Sort.
 *
 * <p>O Merge Sort é um algoritmo de ordenação baseado na técnica "Dividir e Conquistar".
 * Ele divide o array em subarrays menores, ordena-os recursivamente e os combina para formar o array ordenado.</p>
 */
public class MergeSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe MergeSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public MergeSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Merge Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Método recursivo que divide o array em subarrays menores e os ordena.
     *
     * @param array o array a ser dividido e ordenado
     * @param left o índice inicial do subarray
     * @param right o índice final do subarray
     */
    private void mergeSort(int[] array, int left, int right) {
        checkPause();
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    /**
     * Combina dois subarrays ordenados em um único array ordenado.
     *
     * @param array o array contendo os subarrays
     * @param left o índice inicial do primeiro subarray
     * @param mid o índice que separa os dois subarrays
     * @param right o índice final do segundo subarray
     */
    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
            visualizer.setArray(array);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
            visualizer.setArray(array);
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            checkPause();
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            visualizer.setArray(array);
            Sleep.sleep(delay);
        }

        while (i < n1) {
            checkPause();
            array[k] = leftArray[i];
            i++;
            k++;
            visualizer.setArray(array);
            Sleep.sleep(delay);
        }

        while (j < n2) {
            checkPause();
            array[k] = rightArray[j];
            j++;
            k++;
            visualizer.setArray(array);
            Sleep.sleep(delay);
        }
    }
}
