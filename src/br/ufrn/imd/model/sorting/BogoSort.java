package br.ufrn.imd.model.sorting;

import br.ufrn.imd.view.SortingVisualizer;
import br.ufrn.imd.utils.Sleep;
import java.util.Random;

/**
 * Implementação do algoritmo BogoSort, também conhecido como "embaralhamento sort".
 * Esse algoritmo continua embaralhando o array até que ele esteja ordenado.
 *
 * <p>É um algoritmo extremamente ineficiente e é usado principalmente para fins educacionais.</p>
 */
public class BogoSort extends Sorting {
    private SortingVisualizer visualizer;
    private final int delay;

    /**
     * Construtor da classe BogoSort.
     *
     * @param visualizer o objeto responsável pela visualização do algoritmo
     * @param delay o tempo de atraso em milissegundos entre os passos do algoritmo
     */
    public BogoSort(SortingVisualizer visualizer, int delay) {
        this.visualizer = visualizer;
        this.delay = delay;
    }

    /**
     * Realiza a ordenação do array utilizando o algoritmo BogoSort.
     *
     * @param array o array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        Random random = new Random();

        // Continua embaralhando o array até que ele esteja ordenado
        while (!isSorted(array)) {
            checkPause(); // Verifica se o algoritmo deve pausar antes de embaralhar
            shuffleArray(array, random);
            visualizer.setArray(array); // Atualiza a visualização após o embaralhamento
            Sleep.sleep(delay); // Adiciona um atraso para a visualização
        }
    }

    /**
     * Verifica se o array está ordenado.
     *
     * @param array o array a ser verificado
     * @return {@code true} se o array estiver ordenado, {@code false} caso contrário
     */
    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Embaralha o array aleatoriamente.
     *
     * @param array o array a ser embaralhado
     * @param random o gerador de números aleatórios a ser usado no embaralhamento
     */
    private void shuffleArray(int[] array, Random random) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            swap(array, i, randomIndex); // Utiliza o método de troca da classe base
        }
    }
}
