package step.learning.db;

import com.google.inject.Inject;
import step.learning.db.dao.RandomDao;
import step.learning.db.dto.RandomRecord;
import step.learning.services.random.RandomGenService;

import java.util.UUID;

public class DbApp {
    private final RandomGenService randGen;
    private final RandomDao randomDao;


    @Inject
    public DbApp(RandomGenService randGen, RandomDao randomDao) {
        this.randGen = randGen;
        this.randomDao = randomDao;
    }


    public void demo() {
        // region Створення таблиці randoms

        try {  // ~ SqlCommand (ADO.NET)
            randomDao.ensureCreate();
            System.out.println("TABLE OK");
        }
        catch (RuntimeException ex) {
            System.err.println( ex.getMessage() );
        }

        // endregion



        // region Додати до БД випадковий рядок

        try {
            randomDao.insertRandom();
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("RANDOM INSERT OK");

        //endregion



        // region Додати до БД рядок

        try {
            RandomRecord record = new RandomRecord(
                    UUID.randomUUID(),
                    randGen.randInt(256),
                    randGen.randFloat(),
                    randGen.randStr(15)
            );
            randomDao.insert(record);
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("INSERT OK");

        // endregion



        // region Видалити перший елемент таблиці

        try {
            RandomRecord record = randomDao.getAll().get(0);
            randomDao.delete(record);
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("DELETE OK");

        // endregion



        // region Обновити перший елемент таблиці

        try {
            RandomRecord record = randomDao.getAll().get(0);
            record.setRandInt(randGen.randInt(256));
            record.setRandFloat(randGen.randFloat());
            record.setRandStr(randGen.randStr(12));

            randomDao.update(record);
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("UPDATE OK");

        // endregion


        // region Перевірка існування першого рядка таблиці

        try {
            RandomRecord record = randomDao.getAll().get(0);

            if (randomDao.recordExists(record)) {
                System.out.println(record.getId() + " exists");
            }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("RECORDEXISTS OK");

        // endregion


        // region Виведення даних

        try {
            for (RandomRecord rec : randomDao.getAll()) {
                System.out.print(rec);
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
        System.out.println("-----------------------------------");

        // endregion
    }
}

/*
JBDC - технологія доступа до даних, аналог ADO.NET

1. Встановлюємо коннектор - драйвер БД
2. БД та рядок підключення (на прикладі Planetscale)
    jdbc:mysql://aws.connect.psdb.cloud/aspnet-applist?sslMode=VERIFY_IDENTITY
    jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8
 */