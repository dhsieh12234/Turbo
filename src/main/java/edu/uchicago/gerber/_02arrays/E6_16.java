package edu.uchicago.gerber._02arrays;

public class E6_16 {
    public static void main(String[] args) {
        int[] arr = {20, 3, 10, 21};
        int max_int = find_max(arr);
        array_to_asterick(arr, max_int);

    }
    public static int find_max(int[] values) {
        int max = values[0];
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    public static void array_to_asterick(int[] values, int max) {
        for (int row = 20; row > 0; row--) {
            for (int i = 0; i < values.length; i++) {
                int scaledValue = (int) (20.0 * values[i] / max);
                if (scaledValue >= row) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
