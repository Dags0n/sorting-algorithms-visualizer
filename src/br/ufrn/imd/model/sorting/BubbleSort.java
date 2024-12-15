package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class BubbleSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public BubbleSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                checkPause(); // Verifica se o algoritmo deve pausar
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    visualizer.setArray(array); // Atualiza a visualização
                    sleep(delay); // Adiciona um pequeno atraso para visualização
                }
            }
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
