package br.ufrn.imd.utils;

import java.util.Random;

public class DataGenerator {
    public static int[] generateRandomArray(int size, int min, int max) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(max - min + 1) + min;
        }
        return array;
    }
}

