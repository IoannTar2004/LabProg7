package org.example.collections;

import java.io.Serializable;

/**
 * Enums of dragon's type
 */
public enum DragonType implements Serializable {
    WATER("Водный"), UNDERGROUND("Подземельный"), AIR("Воздушный"), FIRE("Огненный");

    private String type;
    DragonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @param type type in Russian
     * @return DragonType
     */
    public static DragonType getEnumType(String type) {
        if (type.matches("\\s*Водный\\s*")) {return WATER;}
        else if (type.matches("\\s*Подземельный\\s*")) {return UNDERGROUND;}
        else if (type.matches("\\s*Воздушный\\s*")) {return AIR;}
        else if (type.matches("\\s*Огненный\\s*")) {return FIRE;}

        return null;
    }

    /**
     *
     * @param type ordinal+1 type
     * @return DragonType
     */

}
