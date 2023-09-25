package droid;

import mechanics.StatusDamage;
import utilits.SaveFight;

/**
 * Підклас класу Droid, що представляє вогняного дроїда, який має здатність завдає урон вогнем та має броню.
 */
public class FireDroid extends Droid {
    int armor;

    /**
     * Конструктор класу FireDroid.
     *
     * @param health початкове здоров'я дроїда
     * @param damage початковий урон, який може наносити дроїд
     * @param name   ім'я дроїда
     * @param armor  рівень броні дроїда
     */
    public FireDroid(double health, double damage, String name, int armor) {
        super(health, damage, name);
        this.armor = armor;
    }

    /**
     * Повертає статус урону, який наносить дроїд (вогонь).
     *
     * @return статус урону "Fire"
     */
    @Override
    public StatusDamage getDamage() {

        return new StatusDamage("Fire", 0, this.damage, 0);
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
                "░░░░░░░░░░░░░    Health: " + this.health,
                "░░░▀▄░░░▄▀░░░    Base Damage: " + this.damage,
                "░░▄█▀███▀█▄░░    Armor: " + this.armor,
                "░█▀███████▀█░",
                "░█░█▀▀▀▀▀█░█░",
                "░░░░▀▀░▀▀░░░░",
                "-----------------------"
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
            case "Fire" ->
                    actualDamage = damage.getFire() * 0.5 * (1 - ((double) armor / 100));  //Вроджений резист до вогню 0.5
            case "Ice" ->
                    actualDamage = damage.getIce() * 1.5 * (1 - ((double) armor / 100));   //Збільшений урон морозом на 50%
            default -> {
                System.out.println("Unknown damage type");
                actualDamage = 0;
            }
        }
        this.health -= actualDamage;


        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }
}
