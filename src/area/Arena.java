package area;

import droid.Droid;
import utilits.SaveFight;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Клас, який представляє арену 1 vs 1 для бою між дроїдами.
 */
public class Arena {
    private final Droid firstDroid;
    private final Droid secondDroid;
    private Droid attacker;
    private Droid defender;
    private int currentRound = 0;

    /**
     * Конструктор класу Arena.
     *
     * @param firstDroid  перший дроїд
     * @param secondDroid другий дроїд
     */
    public Arena(Droid firstDroid, Droid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }

    /**
     * Ініціалізує бійців і визначає, який дроїд атакує першим.
     */
    private void initFighters() {
        Random random = new Random();
        boolean isFirstAttacker = random.nextBoolean();

        attacker = isFirstAttacker ? firstDroid : secondDroid;
        defender = isFirstAttacker ? secondDroid : firstDroid;
    }

    /**
     * Підготовлює раунд бою: ініціалізує бійців, збільшує лічильник раунду і відображає інформацію про дроїдів.
     */
    private void prepareRound() {
        initFighters();
        currentRound++;
        SaveFight.log("-------------------------------------");
        SaveFight.log("Round " + currentRound);

        firstDroid.visualize();
        secondDroid.visualize();
    }

    /**
     * Проводить бій між дроїдами і повертає отриманий удар.
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
     * Починає бій між дроїдами і повертає переможця.
     *
     * @return переможець бою
     * @throws InterruptedException викидається, якщо виникає помилка при паузі між раундами
     */
    public Droid startFight() throws InterruptedException {
        do {
            prepareRound();
            double actualDamage = doFight();
            printRoundInfo(actualDamage);

            TimeUnit.SECONDS.sleep(1);
        } while (defender.isAlive());

        return attacker;
    }
}