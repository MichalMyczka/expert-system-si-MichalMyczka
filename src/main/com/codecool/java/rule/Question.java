package main.com.codecool.java.rule;

public class Question {
    private final String id;
    private final String question;
    private final Answer answer;

    Question(String id, String question, Answer answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public boolean getEvaluatedAnswer(String input) {
        return answer.evaluateAnswerByInput(input);
    }

    public String getQuestion() {
        return question;
    }

    public String getId() {
        return id;
    }



}
