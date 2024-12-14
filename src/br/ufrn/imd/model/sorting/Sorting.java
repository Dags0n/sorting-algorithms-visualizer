package br.ufrn.imd.model.sorting;

public abstract class Sorting {
    public abstract void sort(int[] array);

    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
