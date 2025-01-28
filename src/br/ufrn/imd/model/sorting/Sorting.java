package br.ufrn.imd.model.sorting;

/**
 * Classe abstrata que serve como base para os algoritmos de ordenação.
 *
 * <p>Esta classe fornece métodos utilitários para controle de execução (pausar e retomar),
 * verificar o estado de pausa e realizar operações comuns, como troca de elementos no array.</p>
 */
public abstract class Sorting {
    private volatile boolean shouldRun = true;

    /**
     * Método abstrato que deve ser implementado pelas subclasses para definir
     * a lógica específica do algoritmo de ordenação.
     *
     * @param array o array a ser ordenado
     */
    public abstract void sort(int[] array);

    /**
     * Pausa a execução do algoritmo.
     */
    public void pause() {
        shouldRun = false;
    }

    /**
     * Retoma a execução do algoritmo.
     */
    public void resume() {
        shouldRun = true;
    }

    /**
     * Verifica se a execução está pausada e aguarda até que seja retomada.
     *
     * <p>Se o algoritmo estiver pausado, o método faz a thread aguardar
     * por 50ms antes de verificar novamente o estado de execução.</p>
     */
    protected void checkPause() {
        while (!shouldRun) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Troca os elementos de duas posições em um array.
     *
     * @param array o array onde os elementos serão trocados
     * @param i o índice do primeiro elemento
     * @param j o índice do segundo elemento
     */
    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
