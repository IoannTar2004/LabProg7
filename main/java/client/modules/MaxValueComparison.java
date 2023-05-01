package client.modules;

import org.example.collections.*;
import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.OutputText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaxValueComparison {
    public static boolean compare(Dragon dragon, DragonFields fields, Object element) {
        int compare = 0;
        String getter = "get" + fields.getField();
        switch (fields) {
            case NAME -> compare = ((String) element).compareTo(dragon.getName());
            case COORDINATES -> compare = ((Coordinates) element).sum().compareTo(dragon.getSumCoordinate());
            case AGE -> compare = ((int) element) - dragon.getAge();
            case COLOR -> compare = (((Color) element).ordinal() + 1) - (Color.getEnumColor(dragon.getColor()).ordinal() + 1);
            case TYPE -> compare = (((DragonType) element).ordinal() + 1) - (DragonType.getEnumType(dragon.getType()).ordinal()+1);
            case CHARACTER -> compare = (((DragonCharacter) element).ordinal() + 1) - (DragonCharacter.getEnumCharacter(dragon.getCharacter()).ordinal()+1);
            case CAVE -> compare = ((DragonCave) element).getDepth().compareTo(dragon.getCave());
        }
        Matcher matcher = Pattern.compile("get(\\w)\\w*").matcher(getter);

        if (matcher.matches()) {
            getter = getter.replaceFirst("(?<=get)(?=\\w*)\\w", (matcher.group(1).toUpperCase()));
        }

        Class<Dragon> dragonClass = Dragon.class;
        if (compare < 0) {
            try {
                System.out.println(OutputText.errorWithArgs(fields.getField() + "Less", dragonClass.getDeclaredMethod(getter)
                        .invoke(dragon)));
            } catch (Exception ignored) {}
        } else if (compare == 0) {
            try {
                System.out.println(OutputText.errorWithArgs(fields.getField() + "Equal", dragonClass.getDeclaredMethod(getter)
                        .invoke(dragon)));
            } catch (Exception ignored) {}
        }
        return compare > 0;
    }
}
