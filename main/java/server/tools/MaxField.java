package server.tools;

import org.example.collections.*;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class has a similar destiny to {@link Sort} but it is used to get max values.
 */
public class MaxField extends Sort {
    private List<Dragon> sortlist = new ArrayList<>(new ObjectsCollectionManager().getAll());
    /**
     * Combines other methods in this class to call defined method.
     * @param fields enum of {@link Dragon} field which includes in {@link DragonFields}.
     * @return true if element is greater than max element in collection
     */
    public static Dragon max(DragonFields fields) {
        MaxField maxField = new MaxField();
        switch (fields) {
            case NAME -> {
                return maxField.maxName();
            }
            case COORDINATES -> {
                return maxField.maxCoordinates();
            }
            case AGE -> {
                return maxField.maxAge();
            }
            case COLOR -> {
                return maxField.maxColor();
            }
            case TYPE -> {
                return maxField.maxType();
            }
            case CHARACTER -> {
                return maxField.maxCharacter();
            }
            case CAVE -> {
                return maxField.maxCave();
            }
        }
        return null;
    }

    /**
     *
     * @return max value of {@link Dragon#getName()} name if collection is not empty.
     */
    public Dragon maxName() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByName());

        Collections.reverse(sortlist);
        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return string of the form {@link Dragon#getCoordinates() 'x; y'} (where x + y is max) if collection is not empty
     */
    public Dragon maxCoordinates() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByCoordinates());
        Collections.reverse(sortlist);

        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return max {@link Dragon#getAge() age} if collection is not empty.
     */
    public Dragon maxAge() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByAge());
        Collections.reverse(sortlist);

        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return max ordinal+1 of {@link Color} if collection is not empty.
     */
    public Dragon maxColor() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByColor());
        Collections.reverse(sortlist);

        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return max ordinal+1 of {@link DragonType} if collection is not empty.
     */
    public Dragon maxType() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByType());
        Collections.reverse(sortlist);

        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return max ordinal+1 of {@link DragonCharacter} if collection is not empty.
     */
    public Dragon maxCharacter() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByCharacter());
        Collections.reverse(sortlist);

        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     *
     * @return max {@link Dragon#getCave()} cave depth if collection is not empty.
     */
    public Dragon maxCave() {
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        sortlist.addAll(getters.getAll());
        sortlist.sort(new SortByCave());

        Collections.reverse(sortlist);
        return sortlist.size() > 0 ? sortlist.get(0) : new Dragon();
    }

    /**
     * This method checks that dragon's field does not have max format value.
     * @param fields dragon's field
     * @return true if the dragon with max value field is existed
     */
    public static boolean existence(DragonFields fields, Dragon dragon) {
        if(fields == DragonFields.COORDINATES && (dragon.getX() == Integer.MAX_VALUE || dragon.getY() == Long.MAX_VALUE)) {
            return false;
        } else if (fields == DragonFields.AGE && dragon.getAge() == Integer.MAX_VALUE) {
            return false;
        } else if (fields == DragonFields.COLOR && Objects.equals(dragon.getColor(), "Жёлтый")) {;
            return false;
        } else if (fields == DragonFields.TYPE && Objects.equals(dragon.getType(), "Огненный")) {
            return false;
        } else if (fields == DragonFields.CHARACTER && Objects.equals(dragon.getCharacter(), "Хаотичный")) {
            return false;
        } else if (fields == DragonFields.CAVE && dragon.getCave() == Double.MAX_VALUE) {
            return false;
        }
        return true;
    }
}
