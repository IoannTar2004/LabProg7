package org.example.tools;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class for working with files
 */
public class FileManager {
    private static File currentFile;
    private static Set<File> filesInStack = new LinkedHashSet<>();

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void setCurrentFile(File currentFile) {
        FileManager.currentFile = currentFile;
    }

    /**
     * Methods checks whether the file is empty or not.
     * @param file existed file
     * @return true or false
     */
    public static boolean isNotEmpty(File file) {
        try {
            FileReader reader = new FileReader(file);
            if(reader.read() == -1) {return false;}
        } catch (Exception ignored) {}
        return true;
    }

    public static boolean addFileToStack(File file) {
        if (!filesInStack.add(file)) {
            return false;
        }
        return true;
    }

    public static void clear() {
        filesInStack.clear();
    }
}
