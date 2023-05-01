package server.tools;

import org.apache.commons.lang3.SerializationUtils;
import org.example.collections.*;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsElements;

import java.util.*;

/**
 * class for sort objects in collection
 */
public class Sort {

    /**
     * Sort elements in collection using nested classes which implement Comparator interface and print them.
     * @param fieldNum number of {@link Dragon} field (1 - name, 2 - coordinates, 3 - age, etc).
     */
    public static List<String> sort(DragonFields fieldNum, String... args) {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();
        List<String> dragonList = new LinkedList<>();

        List<Dragon> sortlist = new ArrayList<>(getters.getAll());
        switch (fieldNum) {
            case NAME -> sortlist.sort(new SortByName());
            case COORDINATES -> sortlist.sort(new SortByCoordinates());
            case AGE -> sortlist.sort(new SortByAge());
            case COLOR -> sortlist.sort(new SortByColor());
            case TYPE -> sortlist.sort(new SortByType());
            case CHARACTER -> sortlist.sort(new SortByCharacter());
            case CAVE -> sortlist.sort(new SortByCave());
            default -> {
                return null;
            }
        }
        if (sortlist.size() == 0) {
            return List.of(OutputText.result("Empty"));
        }
        Collections.reverse(sortlist);
        for (Dragon dragon : sortlist) {
            ObjectsElements objectsElements = new ObjectsElements();
            dragonList.add(objectsElements.element(dragon, args));
        }
        return dragonList;
    }


    public static class SortByName extends Sort implements Comparator<Dragon>{
        @Override
        public int compare(Dragon d1, Dragon d2) {
            return d1.getName().compareTo(d2.getName());
        }
    }
    public static class SortByCoordinates extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon d1, Dragon d2) {
            return d1.getSumCoordinate().compareTo(d2.getSumCoordinate());
        }
    }

    public static class SortByAge extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon d1, Dragon d2) {
            return d1.getAge().compareTo(d2.getAge());
        }
    }

    public static class SortByColor extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon d1, Dragon d2) {
            int x = Color.getEnumColor(d1.getColor()).ordinal();
            int y = Color.getEnumColor(d2.getColor()).ordinal();
            return x - y;
        }
    }

    public static class SortByType extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon d1, Dragon d2) {
            int x = DragonType.getEnumType(d1.getType()).ordinal();
            int y = DragonType.getEnumType(d2.getType()).ordinal();;
            return x - y;
        }
    }

    public static class SortByCharacter extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon d1, Dragon d2) {
            int x = DragonCharacter.getEnumCharacter(d1.getCharacter()).ordinal();
            int y = DragonCharacter.getEnumCharacter(d2.getCharacter()).ordinal();
            return x - y;
        }
    }

    public static class SortByCave extends Sort implements Comparator<Dragon>{
        @Override
        public int compare(Dragon d1, Dragon d2) {
            return (int) (d1.getCave()-d2.getCave());
        }
    }

    public static class SortBySize extends Sort implements Comparator<Dragon> {
        @Override
        public int compare(Dragon o1, Dragon o2) {
            int sum1 = 0;
            int sum2 = 0;
            for (byte num: SerializationUtils.serialize(o1)) {
                sum1 = sum1 + num;
            }
            for (byte num: SerializationUtils.serialize(o2)) {
                sum2 = sum2 + num;
            }
            return sum1-sum2;
        }
    }
}