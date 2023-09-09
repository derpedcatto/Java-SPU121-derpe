package step.learning.async;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncApp {
    private final int x = 10;
    private final Object locker = new Object();

    public void demo() {
        // Для багатозадачності створюється окремий об'єкт - виконавець (пул)
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Виконавець здатен приймати як Runnable, так і Callable (з поверненням)
        Future<String> task1 = executor.submit( // Передача задачі автоматично її запускає
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        try {
                            synchronized (locker) {
                                System.out.println("Task 1 Starts, x = " + x);
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.err.println("Task 1 Break");
                        }
                        System.out.println("Task 1 Finish");
                        return "Hello from Task 1";
                    }
                }
        );

        Future<String> task2 = executor.submit( () -> {
            try {
                System.out.println("Task 2 Start");
                Thread.sleep(500);
            } catch(InterruptedException e) {
                System.err.println("Task 2 Break");
            }
            System.out.println("Task 2 Finish");
            return "Hello from Task 2";
        });

        try {
            System.out.println(task2.get());    // замість join() -- get(), до того ж він повертає результат
            task1.cancel(true);
            // executor.shutdownNow();
            System.out.println(task1.get());
        } catch (Exception e) {
            System.err.println("Get error");
        }

        // наявність пулу потоків (виконавця) не дає завершитись основному потоку
        executor.shutdown();

        System.out.println("Main Finish");
    }

    public void demoThread() {
        /*
        Для реалізації багатопоточності є об'єкт Thread
        У його конструктор передається об'єкт функціонального інтерфейсу
        [функціональний інтерфейс - який має лише 1 метод]
        частіше за все - інтерфейс Runnable або його анонімне вираження через ляимбду
         */

        // Створення об'єкту НЕ запускає потік
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 Break");
                }
                System.out.println("Hello Thread");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Thread 2 Break");
                    }
                    System.out.println("Hello Thread 2");
                }
        );
        thread2.start();

        // очікування завершення потоку
        try {
            thread2.join();
            thread1.join(); // якщо треба чекати кілька потоків - кожному join
        } catch (InterruptedException e) {
            System.err.println("Thread 2 Join Break");
        }

        ParamThread p = new ParamThread("My data");
        try {
            p.start();
            p.join();
            System.out.println("Thread 3 Result: " + p.getResult());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Main finishes");
    }

    static class ParamThread extends Thread {
        private final String data;
        private String result;

        public String getResult() {
            return result;
        }

        public ParamThread(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            // super.run();
            try {
                Thread.sleep(500);
                result = "My result";   // "повернення" - результат роботи потоку
            } catch (InterruptedException e) {
                System.err.println("Thread 3 Break");
            }
            System.out.println("Hello Thread 3 - " + data);
        }
    }
}

/*
Асинхронність (у програмуванні) - це можливість виконання різних частин коду
параллельно один з іншим.
Синхронізація - заходи з серіалізації - утворення послідовності роботи коду

Асинхронність досягається:
- багатозадачність / multi-task (задача - об'єкт мови програмування)
- багатопоточність / multi-thread (потік - об'єкт ОС, частина процесу)
- багатопроцесність / multi-process (процес - об'єкт ОС, у рамках одного ПК)
- grid-технології
- network-технології

Рекомендована схема використання:
- виділяємо процедури, які треба виконати (підключення до БД, зчитати файл конфіг,
    запитати API, ...)
- складаємо план: які дії можуть виконуватись паралельно, а які вимагають завершення
    попередніх
- першими стартуємо максимально довгі задачі, далі більш швидкі
- поки виконуються довгі задачі програмуємо дії, які не вимагають завершення тих задач
Наприклад:                                     | Не оптимально
    = запит API (не потребує БД та конфіг)     | = запит API - очікування
    = зчитування конфіг                        | = зчитування конфіг - очікування
    = ініціалізація незалежних об'єктів        | = підключення БД - очікування
    = очікування конфіг                        | = ініціалізація незалежних об'єктів
    = підключення до БД                        | = подальша робота
    = очікування API та БД                     |
    = подальша робота                          |

Багатопоточність
- створення об'єкту-потоку (thread) не стартує потік
- запуск активності може бути thread.run() - синхронно, thread.start() - асинхронно
- потік продовжує активність навіть після зупинки основного потоку
= головний недолік - ускладненність передачі даних у потік та з потоку ( void run() )
    взаємодія здійснюється через "глобальні" змінні - поля класу, який стартує потоки
    або через створення власних класів - нащадків Thread

Багатозадачність
- потребує виконавця - пул потоків / executor (в інших мовах виконавець може бути за замовченням)
    пул потоків може обмежувати граничку кількість одночасних потоків, а також
    повторно використовувати звільнені ресурси
- задачі запускаються відразу, як додаються до виконавця -
    створення об'єкту-задачі (task) запускає активність
- задачі можуть повертати результати (в інших мовах - і приймати параметри)
= головні переваги над потоками
    - групування у пул, через що можна чекати всі задачі
        з пулу, чи їх усі зупинити (потоки - кожен окремо)
    - обмеження кількості одночасно виконуваних потоків
 */