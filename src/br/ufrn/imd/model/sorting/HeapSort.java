package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class HeapSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public HeapSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // Constrói o heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            checkPause(); // Verifica se o algoritmo deve pausar
            heapify(array, n, i);
        }

        // Extrai os elementos do heap
        for (int i = n - 1; i > 0; i--) {
            checkPause(); // Verifica se o algoritmo deve pausar

            swap(array, 0, i);
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização

            heapify(array, i, 0);
        }
    }

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
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização

            heapify(array, n, largest);
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
