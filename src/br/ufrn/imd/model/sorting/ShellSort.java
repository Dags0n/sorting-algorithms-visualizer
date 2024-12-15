package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class ShellSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public ShellSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // Inicializa os gaps
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                checkPause(); // Verifica se o algoritmo deve pausar

                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    checkPause(); // Verifica se o algoritmo deve pausar
                    array[j] = array[j - gap];
                    visualizer.setArray(array); // Atualiza a visualização
                    sleep(delay); // Adiciona atraso para visualização
                }

                array[j] = temp;
                visualizer.setArray(array); // Atualiza a visualização após a inserção
                sleep(delay); // Adiciona atraso para visualização
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
