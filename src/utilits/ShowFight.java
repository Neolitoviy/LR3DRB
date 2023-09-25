package utilits;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Клас, що дозволяє виводити вміст зазначеного файлу на консоль.
 */
public class ShowFight {

    /**
     * Виводить вміст зазначеного файлу на консоль.
     *
     * @param fileName назва файлу, який потрібно вивести
     */
    public static void show(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
