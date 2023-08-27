package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class IoCConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind( HashService.class )
                .annotatedWith(Names.named("Hash-128"))
                .to( MD5HashService.class );
        bind( HashService.class )
                .annotatedWith(Names.named("Hash-160"))
                .to( SHAHashService.class );
    }
}

/*
Inversion of Control (IoC) - інверсія управління
Існує декілька близьких термінів:
- DI  - Dependency Injection
- DIP - Dependency Inversion Principle
- IoC - Inversion of Control


Інверсія управління (чим?) життєвим циклом об'єктів - виділення певного
'центру управління', який буде 'постачати' об'єкти у програму.
Замість інструкцій 'obj = new Class()'
переходимо до інструкцій
'obj = IoC.getInstance(Class)',
які можуть вживати раніше створені об'єкти / створювати нові


DI - інжекція залежностей - спосіб (засіб) передачі залежних об'єктів у
інші об'єкти. Розрізняють інжецію у поля, у конструктор, у методи
    Dependency {}  --------  Class { Dependency _dependency ; }

    без інжеції     Class { Dependency _dependency = IoC.getInstance(Dependency) ; }
    з інжецією (у поле)     Class { @Inject Dependency _dependency ; }


DIP - Dependency Inversion Principle (sOlid)
Принцип рекомендує відмовитись від типізації залежностей як класів:
    Dependency {}  --------  Class { Dependency _dependency ; }
і перейти до інтерфейсів
    IService Dependency : IService {}       Class { ISevice _dependency ; }
Це значно спрощує перехід до нових реалізацій залежностей
    DependencyNew : IService {} залишається сумісною з Class { IService _dependency ; }
    (але не буде сумісною з Class { Dependency _dependency ; }

У Java є декілька поширених способів інверсії управління
javax - "з коробки" - найпростіші схеми
Spring - потужний комплекс, не лише призначений для IoC
Guice - рішення від Google, спеціалізований для IoC
...

На прикладі Guice
1. Встановлюємо
    - mvnrepository.com/artifact/com.google.inject/guice
    - додаємо залежність у конфіг проекту (pom.xml)
    - оновлюємо (завантажуємо) залежності
2. Створюємо клас-конфігуратор (IoCConfigModule) як нащадок AbstractModule
переозначаємо у ньому метод-конфігуратор ( configure() )
3. Змінюємо структуру проєкту. Замість створення основного класу
    'new Library().demo() ;'
утворюємо інжектор на 'пропускаємо' через нього клас, утворючи об'єект (Resolve)
    Injector injector = Guice.createInjector( new IoCConfigModule() );
    injector.getInstance( Library.class ).demo();

*/