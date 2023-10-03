package droid;

import mechanics.StatusDamage;
import utilits.SaveFight;

/**
 * Підклас класу Droid, що представляє морозного дроїда, який має здатність завдавати урон морозом та має щит.
 */
public class FrostDroid extends Droid {
    int shield;

    /**
     * Конструктор класу FrostDroid.
     *
     * @param health початкове здоров'я дроїда
     * @param damage початковий урон, який може наносити дроїд
     * @param name   ім'я дроїда
     * @param shield рівень щита дроїда (одиниці щита діляться на 5)
     */
    public FrostDroid(double health, double damage, String name, int shield) {
        super(health, damage, name);
        this.shield = shield / 5;
    }

    /**
     * Повертає статус урону, який наносить дроїд (мороз).
     *
     * @return статус урону "Ice"
     */
    @Override
    public StatusDamage getDamage() {
        return new StatusDamage("Ice", 0, 0, this.damage);
    }

    /**
     * Візуалізує дроїда на екрані.
     */
    @Override
    public void visualize() {
        String[] visualization = {
                //Використовую ANSI коди для зміни кольору тексту
                "-----------------------\u001B[38;5;195m", // Переключаю на світло-синій колір
                getClass().getSimpleName(),
                "Name: " + name,
                " ❄❄❄❄❄❄❄❄ ",
                "❄  ▄▄████▄▄  ❄",
                "❄ ██████████ ❄   Health: " + this.health,
                "❄ ██▄▄██▄▄██ ❄   Base Damage: " + this.damage,
                "❄  ▄▀▄▀▀▄▀▄  ❄   Shield: " + this.shield,
                "❄ ▀        ▀ ❄",
                " ❄❄❄❄❄❄❄❄ ",
                "\u001B[39m-----------------------"
        };

        for (String line : visualization) SaveFight.log(line);
    }

    /**
     * Застосовує урон на дроїда та повертає фактичний завданий урон.
     *
     * @param damage статус урону, який буде застосований
     * @return фактичний завданий урон
     */
    @Override
    public double getHit(StatusDamage damage) {

        double actualDamage;

        switch (damage.getStatus()) {
            case "Slash" -> actualDamage = damage.getSlash();
            case "Fire" -> actualDamage = damage.getFire() * 1.4;  //Збільшений урон вогню в 1.4
            case "Ice" -> actualDamage = damage.getIce() * 0.5;   //Вроджений резист морозу 50%
            default -> {
                System.out.println("Unknown damage type");
                actualDamage = 0;
            }
        }
        if (this.shield > 0) {
            this.shield -= actualDamage;
        } else {
            this.health -= actualDamage;
        }


        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }


}
