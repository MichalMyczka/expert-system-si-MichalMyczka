package main.com.codecool.java.fact;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {
    private final String description;
    private final Map<String, Boolean> values;

    Fact(String description) {
        this.description = description;
        values = new HashMap<>();
    }

    public Set<String> getIdSet() {
        return values.keySet();
    }

    void setFactValueById(String id, boolean value) {
        values.put(id, value);
    }

    public boolean getValueById(String id) {
        return values.get(id);
    }

    public String getDescription() {
        return description;
    }
}
