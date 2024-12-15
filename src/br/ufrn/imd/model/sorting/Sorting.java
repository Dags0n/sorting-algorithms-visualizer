package br.ufrn.imd.model.sorting;

public abstract class Sorting {
    // Variável de controle de execução
    private volatile boolean shouldRun = true;

    // Método abstrato para implementar a lógica do algoritmo
    public abstract void sort(int[] array);

    // Método para pausar a execução
    public void pause() {
        shouldRun = false;
    }

    // Método para retomar a execução
    public void resume() {
        shouldRun = true;
    }

    // Método para verificar o estado de pausa e gerenciar o controle
    protected void checkPause() {
        while (!shouldRun) { // Se estiver pausado
            try {
                Thread.sleep(50); // Aguarda 50ms antes de verificar novamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura o estado de interrupção
            }
        }
    }

    // Método utilitário para troca de elementos no array
    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
