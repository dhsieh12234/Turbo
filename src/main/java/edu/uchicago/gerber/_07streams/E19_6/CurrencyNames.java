package edu.uchicago.gerber._07streams.E19_6;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyNames {
    public static List<String> getCurrencyDisplayNames() {
        return Currency.getAvailableCurrencies().stream()
                .map(Currency::getDisplayName)
                .sorted()
                .collect(Collectors.toList());
    }
}
