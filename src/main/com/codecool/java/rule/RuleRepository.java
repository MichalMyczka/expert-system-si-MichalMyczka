package main.com.codecool.java.rule;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class RuleRepository {
    private final Deque<Question> questions;

    public Iterator<Question> getIterator() {
        return new QuestionIterator();
    }

    RuleRepository() {
        questions = new ArrayDeque<>();
    }

    void addQuestion(Question question) {
        questions.add(question);
    }


    private class QuestionIterator implements Iterator<Question> {
        private final Deque<Question> questionsToIterate;

        private QuestionIterator() {
            questionsToIterate = questions;
        }

        @Override
        public boolean hasNext() {
            return !questionsToIterate.isEmpty();
        }

        @Override
        public Question next() {
            return questionsToIterate.pop();
        }
    }
}
