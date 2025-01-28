package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Radix Sort.
 *
 * <p>O Radix Sort é um algoritmo de ordenação que organiza os números com base
 * em seus dígitos, começando pelo dígito menos significativo até o mais significativo.
 * Ele é particularmente útil para ordenar números inteiros não negativos.</p>
 */
public class RadixSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe RadixSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public RadixSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Radix Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            checkPause();
            countSort(array, exp);
        }
    }

    /**
     * Obtém o maior valor no array.
     *
     * @param array o array a ser analisado
     * @return o maior valor no array
     */
    private int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Realiza a contagem e ordenação dos elementos com base no dígito especificado.
     *
     * @param array o array a ser ordenado
     * @param exp o expoente representando o dígito atual
     */
    private void countSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            checkPause();
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            checkPause();
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            visualizer.setArray(array);
            Sleep.sleep(delay);
        }
    }
}
