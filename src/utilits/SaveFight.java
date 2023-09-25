package utilits;

import java.io.*;

/**
 * Клас, який відповідає за логування і збереження інформації про бій.
 */
public class SaveFight {
    private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private static final PrintStream originalStdOut = System.out;

    /**
     * Логує текст та виводить його на консоль.
     *
     * @param text текст для логування
     */
    public static void log(String text) {
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        System.out.println(text);
        System.setOut(originalStdOut);
        System.out.println(text);
    }

    /**
     * Зберігає лог у файл з вказаним ім'ям.
     *
     * @param fileName ім'я файлу для збереження логу
     * @throws IOException викидається, якщо виникає помилка під час збереження в файл
     */
    public static void save(String fileName) throws IOException {
        try (OutputStream fos = new FileOutputStream(fileName)) {
            baos.writeTo(fos);
        }
    }
}