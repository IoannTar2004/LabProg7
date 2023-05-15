package org.example.collections;

public enum DragonFields {
    NAME("name"), COORDINATES("coordinates"), AGE("age"), COLOR("color"), TYPE("type"), CHARACTER("character"), CAVE("cave");

    private String field;

    DragonFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static DragonFields getFieldByNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            for (DragonFields fields : DragonFields.values()) {
                if (fields.ordinal() + 1 == num) {
                    return fields;
                }
            }
        } catch (NumberFormatException ignored){}
        return null;
    }
}
