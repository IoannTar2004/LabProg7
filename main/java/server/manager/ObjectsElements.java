package server.manager;

import org.example.collections.Dragon;

import java.util.HashMap;
import java.util.Map;

/**
 * class for processing dragon's fields
 */
public class ObjectsElements extends CollectionManager {

    /**
     * Method which can build string with dragon's elements in relation on entered numbers.
     * @param dragon
     * @param fields if filter by elements is required
     */
    public String element(Dragon dragon, String... fields) {
        if (fields.length == 1) {return dragon.toString();}
        else {
            Map<String, String> elements = new HashMap<>();

            elements.put("1", "id: " + dragon.getId());
            elements.put("2", "Имя: " + dragon.getName());
            elements.put("3", "координаты: " + dragon.getCoordinates());
            elements.put("4", "возраст: " + dragon.getAge());
            elements.put("5", "дата создания: " + dragon.getCreationDate());
            elements.put("6", "цвет: " + dragon.getColor());
            elements.put("7", "тип: " + dragon.getType());
            elements.put("8", "характер: " + dragon.getCharacter());
            elements.put("9", "глубина пещеры: " + dragon.getCave());

            String show = "| ";
            boolean unknownNumber = false;
            for (int i = 1; i < fields.length; i++) {
                if (elements.get(fields[i]) != null) {
                    show = show + elements.get(fields[i]) + " | ";
                    unknownNumber = true;
                }
            }
            if  (unknownNumber) {return show;}
        }
        return "";
    }
}
