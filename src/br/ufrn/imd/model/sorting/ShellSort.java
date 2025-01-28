package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;

/**
 * Implementação do algoritmo Shell Sort.
 *
 * <p>O Shell Sort é uma melhoria do algoritmo Insertion Sort. Ele usa um valor de "gap" (intervalo)
 * para dividir o array em subarrays menores, aplicando o Insertion Sort em cada um.
 * Os gaps são gradualmente reduzidos até que o array esteja completamente ordenado.</p>
 */
public class ShellSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe ShellSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public ShellSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo Shell Sort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                checkPause();

                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    checkPause();
                    array[j] = array[j - gap];
                    visualizer.setArray(array);
                    Sleep.sleep(delay);
                }

                array[j] = temp;
                visualizer.setArray(array);
                Sleep.sleep(delay);
            }
        }
    }
}
