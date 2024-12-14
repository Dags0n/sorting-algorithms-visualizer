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
        if (left < right) {
            int middle = (left + right) / 2;

            // Dividir o array em duas partes
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Combinar as partes ordenadas
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1; // Tamanho da subarray esquerda
        int n2 = right - middle;   // Tamanho da subarray direita

        // Arrays temporários
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copiar dados para os arrays temporários
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        // Indices iniciais das subarrays e do array principal
        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Adiciona um atraso para visualização
            k++;
        }

        // Copiar os elementos restantes de leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay);
            i++;
            k++;
        }

        // Copiar os elementos restantes de rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay);
            j++;
            k++;
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
