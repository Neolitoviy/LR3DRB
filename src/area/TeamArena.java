package area;

import droid.Droid;
import utilits.SaveFight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Клас, який представляє арену для бою між командами дроїдів.
 */
public class TeamArena {

    private final List<Droid> blueDroids = new ArrayList<>();
    private final List<Droid> redDroids = new ArrayList<>();
    private Droid attacker;
    private Droid defender;
    private int currentRound = 0;

    /**
     * Конструктор класу TeamArena.
     *
     * @param droids список дроїдів, які беруть участь у бою
     */
    public TeamArena(List<Droid> droids) {

        for (int i = 0; i < droids.size(); i++) {
            if ((i + 1) % 2 == 0) {
                this.blueDroids.add(droids.get(i));
            } else {
                this.redDroids.add(droids.get(i));
            }
        }
    }

    /**
     * Виводить інформацію про команди дроїдів на консоль.
     */
    private void visualizeTeams() {
        System.out.println("Blue Team:");
        for (Droid blueDroid : blueDroids) {
            blueDroid.visualize();
        }

        System.out.println("Red Team:");
        for (Droid redDroid : redDroids) {
            redDroid.visualize();
        }
    }

    /**
     * Починає бій між командами дроїдів і виводить переможця.
     *
     * @throws InterruptedException викидається, якщо виникає помилка при паузі між раундами
     */
    public void startFight() throws InterruptedException {
        String winningTeam = null;

        do {
            prepareRound();
            double actualDamage = doFight();
            printRoundInfo(actualDamage);

            TimeUnit.SECONDS.sleep(1);
        } while (defender.isAlive());

        // Визначення переможеної команди
        if (redDroids.contains(attacker)) {
            winningTeam = "Red";
        } else if (blueDroids.contains(attacker)) {
            winningTeam = "Blue";
        }

        SaveFight.log("--------------");
        if (winningTeam != null) {
            SaveFight.log("The winner is the " + winningTeam + " team");
        } else {
            SaveFight.log("It's a draw!");
        }

    }

    /**
     * Проводить бій між атакуючим і захищаючим дроїдами і повертає отриманий удар.
     *
     * @return отриманий удар
     */
    private double doFight() {
        return defender.getHit(attacker.getDamage());
    }

    /**
     * Виводить інформацію про раунд бою, зокрема, про отриманий удар і стан дроїдів.
     *
     * @param actualDamage отриманий удар
     */
    private void printRoundInfo(double actualDamage) {
        SaveFight.log(defender.getName() + " get hit with " + actualDamage + " damage");
        SaveFight.log("Defender " + defender);
        SaveFight.log("Attacker " + attacker);
    }

    /**
     * Підготовлює раунд бою: ініціалізує бійців, збільшує лічильник раунду і відображає інформацію про команди дроїдів.
     */
    private void prepareRound() {
        initFighters();
        currentRound++;
        SaveFight.log("-------------------------------------");
        SaveFight.log("Round " + currentRound);
        visualizeTeams();
    }

    /**
     * Ініціалізує атакуючого і захищаючого дроїдів для поточного раунду.
     */
    private void initFighters() {
        Random random = new Random();
        Droid attacker, defender;

        // Вибираємо випадкового дроїда з кожної команди
        Droid blue = blueDroids.get(random.nextInt(blueDroids.size()));
        Droid red = redDroids.get(random.nextInt(redDroids.size()));

        // Визначаємо, хто атакує, а хто захищається
        if (random.nextBoolean()) {
            attacker = blue.isAlive() ? blue : red;
            defender = blue.isAlive() ? red : blue;
        } else {
            attacker = red.isAlive() ? red : blue;
            defender = red.isAlive() ? blue : red;
        }

        this.attacker = attacker;
        this.defender = defender;
    }
}