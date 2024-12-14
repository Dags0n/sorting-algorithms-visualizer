package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import java.util.Random;

public class BogoSort extends Sorting {
    private SortingVisualizer visualizer;
    private Random random;
    private final int delay;

    public BogoSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.random = new Random();
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        while (!isSorted(array)) {
            shuffle(array);
            visualizer.setArray(array); // Atualiza a visualização
            sleep(delay); // Atraso para visualizar o embaralhamento
        }
    }

    // Verifica se o array está ordenado
    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // Embaralha os elementos do array
    private void shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            swap(array, i, randomIndex);
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

