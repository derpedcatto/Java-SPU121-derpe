package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.async.AsyncApp;
import step.learning.async.AsyncConfigModule;
import step.learning.db.DbApp;
import step.learning.db.DbConfigModule;

public class App 
{
    public static void main( String[] args )
    {
        // new Variables().demo();
        // new ArraysLoops().demo();
        // new Collections().demo();
        // new TickTackToe().init();
        // new Library().demo();

        // Injector injector = Guice.createInjector( new IoCConfigModule() );
        // injector.getInstance( IocApp.class ).demo();

        Injector injector = Guice.createInjector( new AsyncConfigModule() );
        injector.getInstance( AsyncApp.class ).demo();
    }
}

/* Інсталляція
> java -version
якщо працює, переходимо далі,
якщо помилка - встановлюємо JRE

Встановлюємо JDK
або) через скачаний файл
або) через інструмент "новий проєкт" в Idea

Новий проєкт
- (ліворуч) Maven Archetype
- назва проєкту Java-SPU-121
- JDK - вибираємо (або скачуємо) 1.8
- Archetype: quickstart
     org.apache.maven.archetypes:maven-archetype-quickstart
- Advanced GameSettings
  = GroupId: step.learning

Після створення проекту налаштовуємо конфігурацію запуску
(за замовчанням - Current File)
Edit Configuration -> + -> Application -> назва: App, Main class-> вибір -> App
*/

/* Вступ
Java - мова програмування
- тип: транслятор (з проміжним кодом)
  - платформа: JRE (Java Runtime Environment)
  - засоби (компілятор): JDK (Development Kit)
    - вихідний код: .java
    - проміжний код: *.class
- покоління: 4GL
- парадигма: ООП (+ мультипарадигма)

Комплекти:
  Java SE (Standard Edition) - базовий набір - консоль, десктоп
  Java EE (Enterprise Edition) - SE + мережа (у т.ч. веб)

Версії:
  1: 1.0 - 1.8 (+ підтримка 1.8, напр. 1.8.361)
  11+
  Особливості - у Java повна підтримка зворотної сумісности,
  будь-який код із попередніх версій має виконуватись на наступних

Системи збирання:
  Maven, Gradle, Ant - структура папок проекту, способи зазначення залежностей, тощо

Особливості:
  - залежність структури проекту та файлової системи:
    - назва файлу має збігатись із назвою класу, що в ньому описаний
      = в одному файлі може бути тілько один клас public
      = один клас повністю визначається в одному файлі
      = для імен класів рекомендується CapitalCamelCase
      = для змінних та методів - lowerCamelCase
  - папка відповідає за пакет (package) - namespace
    = назва пакету має збігатись з назвою папки та її структурою
    = для просторів імен snake_case
*/