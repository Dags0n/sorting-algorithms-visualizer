package br.ufrn.imd.utils;

/**
 * Classe utilitária para lidar com atrasos na execução.
 *
 * <p>Fornece métodos para pausar a execução de uma thread por um determinado
 * período de tempo, facilitando a simulação de atrasos.</p>
 */
public class Sleep {

    /**
     * Pausa a execução da thread atual por um número especificado de milissegundos.
     *
     * @param ms o número de milissegundos para pausar a execução
     * @throws InterruptedException se a thread for interrompida durante o sono
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
