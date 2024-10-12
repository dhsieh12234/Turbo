package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Random;

public class E6_12 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[20];
        for (int i = 0; i < 20; i ++) {
            arr[i] = rand.nextInt(99);
        }
        System.out.println("Original Array: " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));


    }
}
