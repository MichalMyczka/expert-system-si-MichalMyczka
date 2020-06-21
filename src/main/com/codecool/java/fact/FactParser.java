package main.com.codecool.java.fact;

import main.com.codecool.java.XMLParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FactParser extends XMLParser {
    private final FactRepository factRepository;

    public FactParser(String xmlPath) {
        loadXmlDocument(xmlPath);
        factRepository = new FactRepository();
    }

    public FactRepository getFactRepository() {
        NodeList nList = doc.getElementsByTagName("Fact");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;

            Element subElement = (Element) element.getElementsByTagName("Description").item(0);
            String elementDescription = subElement.getAttribute("value");

            Fact fact = new Fact(elementDescription);
            setFactValues(fact, element);

            factRepository.addFact(fact);
        }
        return factRepository;
    }

    private void setFactValues(Fact fact, Element element) {
        Element valuesNode = (Element) element.getElementsByTagName("Evals").item(0);

        NodeList valuesList = valuesNode.getElementsByTagName("Eval");

        for (int i = 0; i < valuesList.getLength(); i++) {
            Element value = (Element) valuesList.item(i);
            String id = value.getAttribute("id");
            String worth = value.getTextContent();
            fact.setFactValueById(id, Boolean.parseBoolean(worth));
        }
    }
}
