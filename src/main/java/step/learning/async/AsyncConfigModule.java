package step.learning.async;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import step.learning.services.random.JavaUtilRandomGenService;
import step.learning.services.random.RandomGenService;

import java.sql.Connection;
import java.sql.DriverManager;

public class AsyncConfigModule extends AbstractModule {
    private Connection localConnection;


    @Override
    protected void configure() {
        // bind(RandomGenService.class)
        //         .to(JavaUtilRandomGenService.class);
    }


    @Provides
    @Named("Local")
    private Connection getLocalConnection() {
        /*
        Методи-провайдери -- розширення засобів інжеції на випадки,
        коли треба більше контролю за об'єктами
        - у служби параметризовані конструкти і треба ними керувати
        - треба більше контролю за миттєвим циклом служб
        Методи "ориєнтуються" на тип повернення (тут - Connection),
        тобто постачають об'єкти в точці інжекції заданого типу.
        За необхідності кількох однотипних провайдерів - іменування
         */

        if (localConnection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                localConnection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8",
                        "spu121",
                        "pass121");
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return localConnection;
    }
}
