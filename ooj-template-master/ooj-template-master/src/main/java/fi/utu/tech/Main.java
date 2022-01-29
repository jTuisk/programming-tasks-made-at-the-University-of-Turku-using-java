package fi.utu.tech;

import fi.utu.tech.fizzbuzz.FizzBuzz;

public class Main {
    /**
     * Main class.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        for(String i: FizzBuzz.fizzBuzz())
            System.out.println(i);
    }
}
