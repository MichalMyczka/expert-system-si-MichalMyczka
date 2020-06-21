package main.com.codecool.java.rule;

import main.com.codecool.java.XMLParser;
import main.com.codecool.java.rule.value.MultipleValue;
import main.com.codecool.java.rule.value.SingleValue;
import main.com.codecool.java.rule.value.Value;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.List;

public class RuleParser extends XMLParser {
    private final RuleRepository ruleRepository;

    public RuleParser(String xmlPath) {
        loadXmlDocument(xmlPath);
        ruleRepository = new RuleRepository();
    }


    private Answer getAnswers(Element element) {
        Answer answer = new Answer();
        NodeList answerList = element.getElementsByTagName("Selection");

        for (int i = 0; i < answerList.getLength(); i++) {
            Node answerNode = answerList.item(i);
            if (answerNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element answerElement = (Element) answerNode;
            Element valueNode = (Element) answerNode.getChildNodes().item(1);
            boolean answerType = Boolean.parseBoolean(answerElement.getAttribute("value"));
            Value value;

            if (valueNode.getNodeName().equals("SingleValue")) {
                String param = valueNode.getAttribute("value");
                value = new SingleValue(param, answerType);
            } else {
                List<String> params = Arrays.asList(valueNode.getAttribute("value").split(","));
                value = new MultipleValue(params, answerType);
            }
            answer.addValue(value);
        }
        return answer;
    }

    public RuleRepository getRuleRepository() {
        NodeList nList = doc.getElementsByTagName("Rule");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;
            String id = element.getAttribute("id");

            String questionText = element.getElementsByTagName("Question").item(0).getTextContent();

            Answer answer = getAnswers(element);

            Question question = new Question(id, questionText, answer);
            ruleRepository.addQuestion(question);
        }
        return ruleRepository;
    }
}
