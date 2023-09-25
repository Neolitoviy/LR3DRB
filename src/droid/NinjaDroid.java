package droid;

import mechanics.StatusDamage;

import java.util.Random;
import utilits.SaveFight;

/**
 * Підклас класу Droid, що представляє дроїда-ніндзю, який має високу витривалість та здатність ухилятися від ударів.
 */
public class NinjaDroid extends Droid {
    int evasion;

    /**
     * Конструктор класу NinjaDroid.
     *
     * @param health  початкове здоров'я дроїда
     * @param damage  початковий урон, який може наносити дроїд
     * @param name    ім'я дроїда
     * @param evasion рівень витривалості дроїда, відсоток успішності ухилення від ударів
     */
    public NinjaDroid(double health, double damage, String name, int evasion) {
        super(health, damage, name);
        this.evasion = evasion;
    }

    /**
     * Візуалізує дроїда на екрані.
     */
    @Override
    public void visualize() {
        String[] visualization = {
                "-----------------------",
                getClass().getSimpleName(),
                "Name: " + name,
                "    ▄██▄        Health: " + this.health,
                "  ▄██████▄      Base Damage: " + this.damage,
                " ███▄██▄███     Evasion: " + this.evasion,
                "   ▄▀▄▄▀▄   ",
                "  ▀ ▀  ▀ ▀  ",
                "-----------------------"
        };

        for (String line : visualization) SaveFight.log(line);
    }

    /**
     * Повертає статус урону, який наносить дроїд (удар клинком).
     *
     * @return статус урону "Slash"
     */
    @Override
    public StatusDamage getDamage() {
        return new StatusDamage("Slash", this.damage, 0, 0);
    }

    /**
     * Застосовує урон на дроїда та повертає фактичний завданий урон.
     *
     * @param damage статус урону, який буде застосований
     * @return фактичний завданий урон, або 0, якщо дроїд ухилився від удару
     */
    @Override
    public double getHit(StatusDamage damage) {
        Random random = new Random();

        double actualDamage;

        switch (damage.getStatus()) {
            case "Slash" -> actualDamage = damage.getSlash() * 2;
            case "Fire" -> actualDamage = damage.getFire();
            case "Ice" -> actualDamage = damage.getIce();
            default -> {
                System.out.println("Unknown damage type");
                actualDamage = 0;
            }
        }
        if (random.nextInt(100) > evasion) {
            this.health -= actualDamage;
            if (health < 0) {
                health = 0;
            }
            return actualDamage;
        }

        return 0;
    }

}
