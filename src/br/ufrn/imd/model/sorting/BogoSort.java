package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import java.util.Random;

public class BogoSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    public BogoSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    @Override
    public void sort(int[] array) {
        Random random = new Random();

        // Continua embaralhando o array até que ele esteja ordenado
        while (!isSorted(array)) {
            checkPause(); // Verifica se o algoritmo deve pausar antes de embaralhar
            shuffleArray(array, random);
            visualizer.setArray(array); // Atualiza a visualização após o embaralhamento
            sleep(delay); // Adiciona um atraso para a visualização
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

    // Embaralha o array aleatoriamente
    private void shuffleArray(int[] array, Random random) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            swap(array, i, randomIndex); // Utiliza o método de troca da classe base
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
