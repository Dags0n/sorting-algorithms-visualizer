package br.ufrn.imd.utils;

import java.util.Random;

/**
 * Classe utilitária para geração de arrays.
 *
 * <p>Fornece métodos para criar arrays preenchidos com números aleatórios,
 * permitindo especificar o tamanho do array e o intervalo dos valores.</p>
 */
public class ArrayGenerator {

    /**
     * Gera um array de inteiros preenchido com valores aleatórios.
     *
     * @param size o tamanho do array a ser gerado
     * @param min o valor mínimo (inclusivo) dos números gerados
     * @param max o valor máximo (inclusivo) dos números gerados
     * @return um array de inteiros preenchido com valores aleatórios no intervalo especificado
     */
    public static int[] generateRandomArray(int size, int min, int max) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
