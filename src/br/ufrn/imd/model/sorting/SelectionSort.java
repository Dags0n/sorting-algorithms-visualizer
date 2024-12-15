package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class SelectionSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public SelectionSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // Itera sobre cada elemento para encontrar o menor e colocá-lo na posição correta
        for (int i = 0; i < n - 1; i++) {
            checkPause(); // Verifica se o algoritmo deve pausar antes de continuar

            // Encontra o índice do menor elemento no subarray restante
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                checkPause(); // Verifica se o algoritmo deve pausar durante a busca
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Troca o menor elemento com o elemento atual
            if (minIndex != i) {
                swap(array, i, minIndex);
                visualizer.setArray(array); // Atualiza a visualização após a troca
                sleep(delay); // Adiciona um pequeno atraso para a visualização
            }
        }
    }

    // Adiciona um atraso para visualização
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
