package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;

public class MergeSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public MergeSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        checkPause(); // Verifica se o algoritmo deve pausar antes de processar
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Cria arrays temporários
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

        int i = 0, j = 0;
        int k = left;

        // Mescla os subarrays
        while (i < n1 && j < n2) {
            checkPause(); // Verifica se o algoritmo deve pausar durante a mesclagem

            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;

            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização
        }

        // Copia os elementos restantes de leftArray
        while (i < n1) {
            checkPause(); // Verifica se o algoritmo deve pausar
            array[k] = leftArray[i];
            i++;
            k++;

            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona atraso para visualização
        }

        // Copia os elementos restantes de rightArray
        while (j < n2) {
            checkPause(); // Verifica se o algoritmo deve pausar
            array[k] = rightArray[j];
            j++;
            k++;

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
