package qbert.level;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import qbert.exceptions.MissingAttributeException;

import java.net.URL;
import java.util.*;

public class LevelReader {

    private static final SAXReader reader = new SAXReader();

    private LevelReader() {}

    public static Level read(URL url) {
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        Level level = new Level();
        List<Node> listNodes = document.selectNodes("/blocks/block");
        for (Node n : listNodes) {
            Element e = (Element) n;
            Attribute idAttribute = e.attribute("id");
            if (idAttribute == null) {
                throw new MissingAttributeException("Every block must have an attribute \"id\"");
            }

            int id;
            try {
                id = Integer.parseInt(idAttribute.getValue());
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Attribute \"id\" must be an integer!");
            }

            Attribute startingBlockAttribute = e.attribute("startingBlock");
            boolean isStartingBlock = (startingBlockAttribute != null && startingBlockAttribute.getValue().equalsIgnoreCase("true"));

            Block block = level.findBlock(id).orElse(new Block(id));
            setChildren(block, e, level);
            level.addBlock(block);

            if (isStartingBlock) {
                level.setStartingBlock(block);
            }
        }

        return level;

    }

    private static void setChildren(Block block, Element e, Level level) {
        Map<Neighbors, Element> neighbors = new HashMap<>();
        for (Neighbors neighbor : Neighbors.values()) {
            neighbors.put(neighbor, (Element) e.selectSingleNode(neighbor.toString()));
        }

        neighbors.forEach((neighbor, element) -> {
            if (element != null && !element.getStringValue().isEmpty()) {
                try {
                    int id = Integer.parseInt(element.getStringValue());
                    Block nb = level.findBlock(id).orElse(new Block(id));
                    level.addBlock(nb);
                    block.setNeighbor(nb, neighbor);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException(String.format("Value of tag \"%s\" must be an integer!", neighbor.toString()));
                }
            }
        });
    }

}
