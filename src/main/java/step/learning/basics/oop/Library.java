package step.learning.basics.oop;

/*
Бібліотеки: є фонди, які містять різну літературу - книги, газети, журнали, тощо.
Є спільні риси - картка для каталога - вона існує для будь-якої літератури
Є відмінні риси - у книги є автор(и), у газет - дата виходу, у журналів - номер
---------------------------
Предметна галузь: книги, газети, журнали

створюємо об'єкти:
Book { Author, Title }
Newspaper { Date, Title }
Journal { Number, Title }

абстрагування: виділення структурно-ієрархічних 'вузлів', які призначені
для групування (поліморфізму) та спрощення (відокремлення спільних даних)
Literature { Title }
    Book { Author }
    Newspaper { Date }
    Journal { Number }

Спільна та індивідуальна функціональність
Потрібний засіб (метод, властивість) для формування картки (каталогу)
Literature { Title, getCard() }
На рівні класу Literature метод getCard() не має сенсу реалізовувати,
тому він залишається абстрактним. Значить кожен з об'єктів має надати
свою реалізацію
 */


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Literature> funds = new ArrayList<>();

    private void fillFunds() {
        Book book = new Book();
        book.setAuthor("D. Knuth");
        book.setTitle("Art of Programming");
        funds.add(book);

        Newspaper newspaper = new Newspaper();
        newspaper.setTitle("Daily Telegraph");
        try { newspaper.setDate("2023-08-19"); }
        catch (ParseException e) {
            System.err.println("Invalid date");
        }
        funds.add(newspaper);

        Journal journal = new Journal();
        journal.setTitle("Lorem Ipsum Jorno");
        journal.setNumber(4);
        funds.add(journal);

        Booklet booklet = new Booklet("Derpe Publishing");
        booklet.setTitle("The Return");
        funds.add(booklet);

        Poster poster = new Poster();
        poster.setTitle("Cool Guy");
        poster.setColored(true);
        funds.add(poster);
    }

    /**
     * Виводить тільки періодичні видання
     */
    private void showPeriodic() {
        for (Literature literature : funds) {
            if (literature instanceof Periodic) {
                System.out.print(literature.getCard());
                System.out.println(" Comes " + ((Periodic) literature).getPeriod());
            }
        }
    }

    private void showNonPeriodic() {
        for (Literature literature : funds) {
            if (!(literature instanceof Periodic)) {
                System.out.print(literature.getCard() + "\n");
            }
        }
    }

    private void showCopyable() {
        for (Literature literature : funds) {
            if (literature instanceof Copyable) {
                System.out.print("\"" + literature.getCard() + "\" is copyable.\n");
            }
        }
    }

    private void showNonCopyable() {
        for (Literature literature : funds) {
            if (!(literature instanceof Copyable)) {
                System.out.print("\"" + literature.getCard() + "\" is not copyable.\n");
            }
        }
    }

    public void demo() {
        System.out.println("Library");
        fillFunds();

        for (Literature literature : funds ) {
            System.out.println(literature.getCard());
        }

        System.out.println();
        System.out.println("------------- PERIODIC -------------");
        showPeriodic();

        System.out.println();
        System.out.println("----------- NON PERIODIC -----------");
        showNonPeriodic();

        System.out.println();
        System.out.println("------------- COPYABLE -------------");
        showCopyable();

        System.out.println();
        System.out.println("----------- NON COPYABLE -----------");
        showNonCopyable();
    }
}


/*
ООП - об'єктно-орієнтована парадигма програмування
Для того щоб опанувати ООП у новій мові програмування слід розглянути:
- оголошення класів, різновиди (чи є інтерфейси, enum, тощо);
- конструктори та створення об'єктів
- спадкування та реалізація
- поліморфізм - сумісність типів даних
- перетворення типів

Парадигми:
- Імперативна ~алгоритмічна - повне управління виконавцем (процесором);
    поняття: інструкція (її номер/адреса, перехід до іншої інструкції);
    відгалуження: процедурний підхід, структурне програмування;
    мови: машинний код, ассемблери, С/C++, Basic, Fortran;

- Декларативна - встановлення певних правил, відношень, аксіом та
    перевірка на базі них різних тверджень;
    поняття: домен, clause, бектрекінг;
    відгалуження: логічна парадигма;
    мови: Prolog;

- Функціональна - послідовне виконання функцій над вхідними даними до
    досягнення потрібної мети;
    поняття: лямбда (функція), кортеж, голова-хвіст;
    відгалужень: чисті парадигми;
    мови: LISP, Python, J,  Haskell;

- ООП - програма подається як об'єкти та їх взаємодія
    поняття: інкапсуляція, поліморфізм, наслідування (спадкування), абстракція
    відгалуження: прототипне програмування (JS)
    мови: C#, Java, ...
 */