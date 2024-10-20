package edu.uchicago.gerber._03objects.P8_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        List<Country> countries = new ArrayList<>();
        System.out.print("Enter the number of countries: ");
        int n = console.nextInt();
        console.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for country " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = console.nextLine();

            System.out.print("Population: ");
            int population = console.nextInt();

            System.out.print("Area: ");
            double area = console.nextDouble();
            console.nextLine(); // Consume the newline character

            countries.add(new Country(name, population, area));
        }
        Country largestAreaCountry = largest_area(countries);
        System.out.println("Country with the largest area: " + largestAreaCountry.getName());

        Country largestPopulationCountry = largest_population(countries);
        System.out.println("Country with the largest population: " + largestPopulationCountry.getName());

        Country highestDensityCountry = largest_density(countries);
        System.out.println("Country with the highest population density: " + highestDensityCountry.getName());

        console.close();

    }

    public static Country largest_area(List<Country> countries) {
        Country largest = countries.get(0);
        for (Country country : countries) {
            if (country.getArea() > largest.getArea()) {
                largest = country;
            }
        }
        return largest;
    }

    public static Country largest_population(List<Country> countries) {
        Country largest = countries.get(0);
        for (Country country : countries) {
            if (country.getPopulation() > largest.getPopulation()) {
                largest = country;
            }
        }
        return largest;
    }

    public static Country largest_density(List<Country> countries) {
        Country largest = countries.get(0);
        for (Country country : countries) {
            if (country.getPopulationDensity() > largest.getPopulationDensity()) {
                largest = country;
            }
        }
        return largest;
    }
}
