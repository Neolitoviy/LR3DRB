package mechanics;

/**
 * Клас, що представляє статус урону, включаючи урон клинком, вогнем та морозом.
 */
public class StatusDamage {
    private String status;// Тип урону
    private double slash; // Урон клинком
    private double fire;  // Урон від вогню
    private double ice;   // Урон від морозу

    /**
     * Конструктор класу StatusDamage.
     *
     * @param status тип урону (наприклад, "Slash" для удару клинком)
     * @param slash  урон клинком
     * @param fire   урон від вогню
     * @param ice    урон від морозу
     */
    public StatusDamage(String status, double slash, double fire, double ice) {
        this.status = status;
        this.slash = slash;
        this.fire = fire;
        this.ice = ice;
    }

    /**
     * Повертає тип урону.
     *
     * @return тип урону
     */
    public String getStatus() {
        return status;
    }

    /**
     * Повертає урон клинком.
     *
     * @return урон клинком
     */
    public double getSlash() {
        return slash;
    }

    /**
     * Повертає урон від вогню.
     *
     * @return урон від вогню
     */
    public double getFire() {
        return fire;
    }

    /**
     * Повертає урон від морозу.
     *
     * @return урон від морозу
     */
    public double getIce() {
        return ice;
    }
}
