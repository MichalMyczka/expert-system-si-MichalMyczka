package main.com.codecool.java;

import main.com.codecool.java.fact.Fact;
import main.com.codecool.java.fact.FactParser;
import main.com.codecool.java.rule.Question;
import main.com.codecool.java.rule.RuleParser;

import java.util.*;

public class ESProvider {
    private final Scanner scanner;
    private final FactParser factParser;
    private final RuleParser ruleParser;
    private final Map<String, Boolean> userSelection;

    ESProvider(FactParser factParser, RuleParser ruleParser) {
        scanner = new Scanner(System.in);
        this.factParser = factParser;
        this.ruleParser = ruleParser;
        userSelection = new HashMap<>();
    }

    void collectAnswers() {
        Iterator<Question> questionIterator = ruleParser.getRuleRepository().getIterator();

        while (questionIterator.hasNext()) {
            Question question = questionIterator.next();
            String questionId = question.getId();
            System.out.println(question.getQuestion());
            boolean userAnswer = getAnswerByQuestion(question);

            userSelection.put(questionId, userAnswer);
        }
        scanner.close();
    }

    private boolean getAnswerByQuestion(Question question) {
        String userInput;
        boolean validInput = false;
        boolean answer = false;
        while (!validInput) {
            userInput = scanner.next();
            try {
                answer = question.getEvaluatedAnswer(userInput);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("You can only type yes or no");
            }
        }
        return answer;
    }

    String evaluate() {
        String result = "";
        Iterator<Fact> factIterator = factParser.getFactRepository().getIterator();
        Fact fact;
        while (factIterator.hasNext()) {
            fact = factIterator.next();
            if (!checkMatch(fact.getIdSet(), fact)) {
                continue;
            }
            result = fact.getDescription();
        }
        return "\nThe Game That Is Best For You Is:\n\n" + result;
    }

    private boolean checkMatch(Set<String> idSet, Fact fact) {
        for (String id : idSet) {
            if (fact.getValueById(id) != userSelection.get(id)) {
                return false;
            }
        }
        return true;
    }
}
