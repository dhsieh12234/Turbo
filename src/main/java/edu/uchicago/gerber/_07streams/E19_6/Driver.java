package edu.uchicago.gerber._07streams.E19_6;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<String> currencyNames = CurrencyNames.getCurrencyDisplayNames();
        currencyNames.forEach(System.out::println);
    }
}
