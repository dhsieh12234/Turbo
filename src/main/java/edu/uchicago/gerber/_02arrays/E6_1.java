package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Random;

public class E6_1 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i ++) {
            arr[i] = rand.nextInt(1000);
        }
        System.out.println(Arrays.toString(arr));
        print_even_index(arr);
        print_even_number(arr);
        reverse_array(arr);
        first_and_last(arr);

    }
    public static void print_even_index(int[] array) {
        String res = "";
        for (int i = 0; i < array.length; i += 2) {
            res = res + array[i] + " ";
        }
        System.out.println("Even indexes: " +  res);
    }
    public static void print_even_number(int[] array) {
        String res = "";
        for (int i = 0; i < array.length; i ++) {
            if (array[i] % 2 == 0) {
                res = res + array[i] + " ";
            }
        }
        System.out.println("Even Values: " + res);
    }
    public static void reverse_array(int[] array) {
        String res = "";
        for (int i = array.length - 1; i >= 0; i--) {
            res += array[i] + " ";
        }
        System.out.println("{" + res + "}");
    }
    public static void first_and_last(int[] array) {
        System.out.printf("First: %d, Last: %d\n", array[0], array[array.length - 1]);
    }
}
