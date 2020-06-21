package main.com.codecool.java.fact;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class FactRepository {
    private final Deque<Fact> facts;

    FactRepository() {
        facts = new ArrayDeque<>();
    }

    void addFact(Fact fact) {
        facts.add(fact);
    }

    public Iterator<Fact> getIterator() {
        return new FactIterator();
    }

    private class FactIterator implements Iterator<Fact> {
        private final Deque<Fact> factsToIterate;

        private FactIterator() {
            factsToIterate = facts;
        }

        @Override
        public boolean hasNext() {
            return !factsToIterate.isEmpty();
        }

        @Override
        public Fact next() {
            return factsToIterate.pop();
        }
    }
}
