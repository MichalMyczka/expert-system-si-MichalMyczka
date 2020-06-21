package main.com.codecool.java.rule;

import main.com.codecool.java.rule.value.Value;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Answer {
    private final List<Value> values;

    Answer() {
        values = new ArrayList<>();
    }

    void addValue(Value value) {
        values.add(value);
    }

    boolean evaluateAnswerByInput(String input) throws InputMismatchException {
        for (Value value : values) {
            if (value.getInputPattern().contains(input)) {
                return value.getSelectionType();
            }
        }
        throw new InputMismatchException();
    }
}
