package step.learning.basics;

/*
JavaDoc - система коментарів / документації (/** == ///)
 */

/**
 * Основи Java - змінні та типи даних
 */
public class Variables {
    /**
     * Запуск демонстрації
     */
    public void demo() {
        /*
        У Java всі типи даних референсні окрім примітивних типів:

         */
        byte xb = 10;        // Цілочисельні типи даніх
        short xs = 100;       // Всі ці типи - знакові
        int xi = 10000;      // Беззнакових типів не існує
        long xl = 10000000;  //

        float yf = 0.01f;
        double yd = 1.15e-3;

        char c = 'A';           // UTF-16 -- 2 bytes
        char ii = 'Ї';

        boolean b = true;
        /*
        З примітивними типами можут виникати обмеження при спробі
        Їх використання у generic виразах (<T>)
        Немає автоматичного boxing / unboxing
        Для таких цілей є референсні аналоги цих типів:
         */
        Byte xxb = 10;
        Short xxs = 100;
        Integer xxi = xxs + xxb;
        // etc

        /*
        String - аналогічно з C# - незмінний (immutable), всі операції утворюють нові об'єкти.
        І також аналогично діє пул рядків (компілятор збирає усі рядки з коду, якщо трапляються
        однакові, то замінюється на вже наявне посилання, новий літерал не створюється).
        Порівняння (==) за референсом а не за вмістом.
         */
        String str1 = "Hello World";    // Завдяки пулу це буде один рядок (ресурс)
        String str2 = "Hello World";    // та два посилання на нього
        String str3 = "Hello " + " World";  // Такий + виконує компілятор і теж не створить
                                            // новий ресурс

        /*
        Перевантаження операторов у Java немає.
        Оператор порівняння (==) діє за референсним принципом,
        тобто true для двох посилань на один об'єкт.
         */
        if (str1 == str2) {
            System.out.println("str1 == str2"); // true
        }
        else {
            System.out.println("str1 != str2");
        }

        String str4 = new String("Hello World");
        String str5 = new String("Hello World");
        if (str4 == str5) {
            System.out.println("str4 == str5");
        }
        else {
            System.out.println("str4 != str5"); // true
        }

        if (str4.equals(str5)) {
            System.out.println("str4 equals str5"); // true
        }
        else {
            System.out.println("str4 !equals str5");
        }

        /*
        Object - батьківський об'єкт для всіх типів
         */
        Object obj = new Object();
        System.out.println(obj);    // -> obj.toString()
    }
}