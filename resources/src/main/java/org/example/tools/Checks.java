package org.example.tools;

import org.example.collections.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Consists of methods which check entered data, formats them and return finished values. Some of them can be saved to object fields.
 */
public class Checks {

    private String input;

    /**
     *
     * @param input entered by user or read from script string
     */
    public Checks(String input) {
        this.input = input;
    }

    /**
     *
     * @return name or null in case of empty string
     */
    public String nameChecker() {
        if (input.matches("\\s*")) {
            System.out.println(OutputText.error("nameIncorrect"));
            return null;
        }
        return input;
    }

    /**
     *
     * @return new {@link Coordinates} or null in case of invalid input or exceeding the max value.
     */
    public Coordinates coordinatesChecker() {
        Pattern pattern = Pattern.compile("\\s*(-?\\d+((\\s*;\\s*)|(\\s+))-?\\d+)\\s*");
        Matcher matcher = pattern.matcher(input);
        int x; long y;

        if (matcher.matches()) {
            String[] coordinates = matcher.group(1).split("(\\s*;\\s*)|(\\s+)");
            try {
                x = Integer.parseInt(coordinates[0]);
            } catch (NumberFormatException e) {
                System.out.println(OutputText.errorWithArgs("X_IntegerFormatExceeded", Integer.MAX_VALUE));
                return null;
            }

            try {
                y = Long.parseLong(coordinates[1]);
            } catch (NumberFormatException e) {
                System.out.println(OutputText.errorWithArgs("Y_LongFormatExceeded", Long.MAX_VALUE));
                return null;
            }
            return new Coordinates(x, y);
        }
        System.out.println(OutputText.error("coordinatesIncorrect"));
        return null;
    }

    /**
     *
     * @return 'int input' if entered input is a positive integer number; -2 in case of exceeding Integer.MAX_VALUE; -1 in other cases.
     */
    public Integer ageChecker() {
        Matcher matcher = Pattern.compile("[1-9][0-9]*").matcher(input);
        Matcher matcher1 = Pattern.compile("-\\d+|0").matcher(input);
        Matcher matcher2 = Pattern.compile("-?\\d+\\.\\d+").matcher(input);

        if (matcher.matches()) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(OutputText.errorWithArgs("IntegerFormatExceeded", Integer.MAX_VALUE));
                return null;
            }
        } else if (matcher1.matches()) {
            System.out.println("Возраст - положительное число!");
        } else if (matcher2.matches()) {
            System.out.println("Возраст - целое положительное число!");
        } else {
            System.out.println("Возраст должен быть положительным целым числом в 10 сс!");
        }
        return null;
    }

    /**
     *
     * @return {@link Color color} in relation to number
     */
    public Color colorChecker() {
        if (input.matches("\\s*1\\s*")) {return Color.BLACK;}
        else if (input.matches("\\s*2\\s*")) {return Color.BLUE;}
        else if (input.matches("\\s*3\\s*")) {return Color.YELLOW;}

        System.out.println(OutputText.error("colorIncorrect"));
        return null;
    }

    /**
     *
     * @return {@link DragonType type} in relation to number
     */
    public DragonType typeChecker() {
        if (input.matches("\\s*1\\s*")) {return DragonType.WATER;}
        else if (input.matches("\\s*2\\s*")) {return DragonType.UNDERGROUND;}
        else if (input.matches("\\s*3\\s*")) {return DragonType.AIR;}
        else if (input.matches("\\s*4\\s*")) {return DragonType.FIRE;}

        System.out.println(OutputText.error("typeIncorrect"));
        return null;
    }

    /**
     *
     * @return {@link DragonCharacter character} in relation to number
     */
    public DragonCharacter characterChecker() {
        if (input.matches("\\s*1\\s*")) {return DragonCharacter.CUNNING;}
        else if (input.matches("\\s*2\\s*")) {return DragonCharacter.EVIL;}
        else if (input.matches("\\s*3\\s*")) {return DragonCharacter.CHAOTIC;}

        System.out.println(OutputText.error("characterIncorrect"));
        return null;
    }

    /**
     *
     * @return new {@link DragonCave} or null in case of invalid input or exceeding Double.MAX_VALUE.
     */
    public DragonCave caveChecker() {
        Matcher matcher = Pattern.compile("-?(\\d+\\.\\d*)|(\\d+)").matcher(input);
        Matcher matcher1 = Pattern.compile("-?\\d+\\.-\\d+").matcher(input);

        if (matcher.matches()) {
            try {
                return new DragonCave(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println(OutputText.errorWithArgs("DoubleFormatExceeded", Double.MAX_VALUE));
            }
        } else if (matcher1.matches()) {
            System.out.println("Дробное часть числа не может быть отрицательной!");
        } else {
            System.out.println("Глубина пещеры - дробное число через точку!");
        }

        return null;
    }

    /**
     * Method checks existence of file.
     * @return new {@link File} or null in case of absence.
     */
    public File fileChecker() throws FileNotFoundException {
        Pattern pattern = Pattern.compile("\\s*(\\S.*)\\s*");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {input = matcher.group(1);}
        input = input.replaceAll("~",System.getProperty("user.home"));

        if (System.getenv(input) != null) {
            File file = new File(System.getenv(input));
            return file;
        }
        throw new FileNotFoundException();
    }
}