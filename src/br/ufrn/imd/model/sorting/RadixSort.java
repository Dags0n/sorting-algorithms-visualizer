package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class RadixSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public RadixSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int max = getMax(array);

        // Ordena com base em cada dígito
        for (int exp = 1; max / exp > 0; exp *= 10) {
            checkPause(); // Verifica se o algoritmo deve pausar
            countSort(array, exp);
        }
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private void countSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            checkPause(); // Verifica se o algoritmo deve pausar
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            checkPause(); // Verifica se o algoritmo deve pausar
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
