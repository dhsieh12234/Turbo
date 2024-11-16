package edu.uchicago.gerber._07streams.E19_5;

import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5, 6);
        System.out.println(StreamtoComma.toString(numberStream, 3));
    }
}
