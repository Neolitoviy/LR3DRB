import droid.Droid;
import menu.*;

import utilits.SaveFight;
import utilits.ShowFight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static menu.MethodsOfMenu.*;

/**
 * Головний клас програми
 */
public class Main {

    /**
     * Головний метод програми.
     *
     * @param args аргументи командного рядка (не використовуються в цій програмі)
     * @throws InterruptedException викидається, якщо виникає помилка при паузі між раундами бою
     * @throws IOException викидається, якщо виникає помилка при роботі з файлами
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);


        List<Droid> droids = new ArrayList<>();

        boolean Start = true;
        while (Start) {
            printMenu(menu.getOptions());

            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("---------------------");
                    droids.add(MethodsOfMenu.createDroid());
                    System.out.println("---------------------");
                }
                case 2 -> {
                    System.out.println("---------------------");
                    MethodsOfMenu.printList(droids);
                    System.out.println("---------------------");
                }
                case 3 -> {
                    SaveFight.log("---------------------");
                    MethodsOfMenu.droidFight(droids);
                    SaveFight.log("---------------------");
                }
                case 4 -> {
                    System.out.println("---------------------");
                    MethodsOfMenu.teamFight(droids);
                    System.out.println("---------------------");
                }
                case 5 -> SaveFight.save("SavedFight.txt");
                case 6 -> ShowFight.show("SavedFight.txt");
                case 7 -> Start = false;
                default -> {
                }
            }
        }
    }
}

