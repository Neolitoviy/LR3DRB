package menu;

/**
 * Клас, що представляє меню програми.
 */
public class Menu {
    private final String[] options = {"1- Create Droid",
            "2- List of created Droids",
            "3- Fight 1 vs 1",
            "4- Team Fight",
            "5- Save Fight",
            "6- Show Fight",
            "7- Exit"
    };

    /**
     * Повертає варіанти меню у вигляді масиву рядків.
     *
     * @return масив рядків з варіантами меню
     */
    public String[] getOptions() {
        return options;
    }
}
