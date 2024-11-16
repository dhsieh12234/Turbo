package edu.uchicago.gerber._07streams.E19_5;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamtoComma {
    public static <T> String toString(Stream<T> stream, int n) {
        return stream.limit(n)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
