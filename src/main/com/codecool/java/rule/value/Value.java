package main.com.codecool.java.rule.value;

import java.util.ArrayList;
import java.util.List;

public abstract class Value {
    private final List<String> inputPattern;
    private final boolean selectionType;

    protected Value(String pattern, boolean selectionType) {
        inputPattern = new ArrayList<>();
        inputPattern.add(pattern);
        this.selectionType = selectionType;
    }

    protected Value(List<String> patterns, boolean selectionType) {
        inputPattern = patterns;
        this.selectionType = selectionType;
    }

    public List<String> getInputPattern() {
        return inputPattern;
    }

    public boolean getSelectionType() {
        return selectionType;
    }
}
