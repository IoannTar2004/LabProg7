package org.example.collections;

import java.io.Serializable;

/**
 * Enums of dragon's color
 */
public enum Color implements Serializable {
    BLACK("Чёрный"), BLUE("Синий"), YELLOW("Жёлтый");

    private String color;
    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    /**
     *
     * @param color color in Russian
     * @return color
     */
    public static Color getEnumColor(String color) {
        if (color.matches("\\s*Ч(ё|е)рный\\s*")) {return BLACK;}
        else if (color.matches("\\s*Синий\\s*")) {return BLUE;}
        else if (color.matches("\\s*Ж(ё|е)лтый\\s*")) {return YELLOW;}

        return null;
    }

    /**
     *
     * @param color ordinal+1 color
     * @return color
     */

}
