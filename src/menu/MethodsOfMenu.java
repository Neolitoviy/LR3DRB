package menu;

import area.*;
import droid.*;
import utilits.SaveFight;

import java.util.List;
import java.util.Scanner;

/**
 * Клас, що містить методи для обробки опцій меню.
 */
public class MethodsOfMenu {

    /**
     * Виводить меню на екран.
     *
     * @param options масив рядків з варіантами меню
     */
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    /**
     * Створює дроїда на основі вибору користувача.
     *
     * @return створений дроїд
     */
    public static Droid createDroid() {
        Scanner scanner = new Scanner(System.in);

        String[] droidOptions = {"1- FireDroid", "2- FrostDroid", "3- NinjaDroid"};

        printMenu(droidOptions);

        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.print("Input DroidName:");
                scanner.nextLine();
                return new FireDroid(60, 9, scanner.nextLine(), 20);
            }
            case 2 -> {
                System.out.print("Input DroidName:");
                scanner.nextLine();
                return new FrostDroid(50, 10, scanner.nextLine(), 30);
            }
            case 3 -> {
                System.out.print("Input DroidName:");
                scanner.nextLine();
                return new NinjaDroid(40, 13, scanner.nextLine(), 25);
            }
            default -> {
            }
        }
        return new Droid(0, 0, "0");
    }

    /**
     * Виводить список дроїдів на екран.
     *
     * @param droids список дроїдів
     */
    public static void printList(List<Droid> droids) {
        for (Droid droid : droids) {
            System.out.println(droid);
        }
    }

    /**
     * Виконує бій між двома дроїдами та виводить переможця.
     *
     * @param droids список дроїдів, що беруть участь у бою (2 дроїда)
     * @throws InterruptedException виняток в разі переривання
     */
    public static void droidFight(List<Droid> droids) throws InterruptedException {
        Arena arena = new Arena(droids.get(0), droids.get(1));
        Droid winner = arena.startFight();

        SaveFight.log("--------------");
        SaveFight.log("The winner is " + winner.getName());
    }

    /**
     * Виконує командний бій між командами дроїдів та виводить переможця команди.
     *
     * @param droids список дроїдів, що беруть участь у бою
     * @throws InterruptedException виняток в разі переривання
     */
    public static void teamFight(List<Droid> droids) throws InterruptedException {
        TeamArena arena = new TeamArena(droids);
        arena.startFight();
    }
}
