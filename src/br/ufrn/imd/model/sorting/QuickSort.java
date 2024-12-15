package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class QuickSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public QuickSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        checkPause(); // Verifica se o algoritmo deve pausar antes de continuar
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            // Ordena recursivamente as duas metades
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Escolhe o último elemento como pivô
        int i = low - 1;

        for (int j = low; j < high; j++) {
            checkPause(); // Verifica se o algoritmo deve pausar durante a partição

            if (array[j] < pivot) {
                i++;
                swap(array, i, j); // Move o elemento menor que o pivô
                visualizer.setArray(array); // Atualiza a visualização após cada troca
                sleep(delay); // Adiciona atraso para visualização
            }
        }

        // Move o pivô para sua posição correta
        swap(array, i + 1, high);
        visualizer.setArray(array); // Atualiza a visualização após a troca do pivô
        sleep(delay); // Adiciona atraso para visualização

        return i + 1; // Retorna o índice do pivô
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
