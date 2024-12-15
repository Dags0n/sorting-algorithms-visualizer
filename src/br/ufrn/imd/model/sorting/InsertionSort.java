package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class InsertionSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public InsertionSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            checkPause(); // Verifica se o algoritmo deve pausar antes de cada iteração

            int key = array[i];
            int j = i - 1;

            // Move os elementos maiores que a chave para uma posição à frente
            while (j >= 0 && array[j] > key) {
                checkPause(); // Verifica se o algoritmo deve pausar durante a movimentação
                array[j + 1] = array[j];
                j--;

                visualizer.setArray(array); // Atualiza a visualização
                sleep(delay); // Adiciona atraso para visualização
            }

            // Insere a chave na posição correta
            array[j + 1] = key;
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização
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
