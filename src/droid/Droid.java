package droid;

import mechanics.StatusDamage;

/**
 * Базовий клас, що представляє дроїда.
 */
public class Droid {

    /** Ім'я дроїда. */
    protected String name;

    /** Початковий урон, який може наносити дроїд. */
    protected double damage;

    /** Початкове здоров'я дроїда. */
    protected double health;

    /**
     * Конструктор класу Droid.
     *
     * @param health початкове здоров'я дроїда
     * @param damage початковий урон, який може наносити дроїд
     * @param name   ім'я дроїда
     */
    public Droid(double health, double damage, String name) {
        this.health = health;
        this.damage = damage;
        this.name = name;
    }

    /**
     * Отримати статус урону, який дроїд може нанести.
     *
     * @return статус урону
     */
    public StatusDamage getDamage() {
        return new StatusDamage("default", 0,0, 0);
    }

    /**
     * Отримати ім'я дроїда.
     *
     * @return ім'я дроїда
     */
    public String getName() {
        return name;
    }

    /**
     * Перевіряє, чи живий дроїд (має здоров'я більше 0).
     *
     * @return true, якщо дроїд живий; false, якщо дроїд мертвий
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Застосовує удар на дроїда та повертає фактичний завданий урон.
     *
     * @param damage статус урону, який буде застосований
     * @return фактичний завданий урон
     */
    public double getHit(StatusDamage damage) {

        double actualDamage = 0;
        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }

    /**
     * Візуалізація дроїда (перевизначена в підкласах).
     */
    public void visualize() {}

    /**
     * Повертає рядок з інформацією про дроїда.
     *
     * @return рядок з інформацією про дроїда
     */
    @Override
    public String toString() {
        return name + " health " + health;
    }
}
