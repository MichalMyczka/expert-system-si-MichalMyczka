package main.com.codecool.java;

import main.com.codecool.java.fact.FactParser;
import main.com.codecool.java.rule.RuleParser;

public class Main {
    public static void main(String[] args) {
        ESProvider eSProvider = new ESProvider(
                new FactParser("src/main/com/codecool/resources/Facts.xml"),
                new RuleParser("src/main/com/codecool/resources/Rules.xml"));

        System.out.println("\nLet's See Which Game Suits You Best:\n");
        eSProvider.collectAnswers();
        System.out.println(eSProvider.evaluate());
    }
}
