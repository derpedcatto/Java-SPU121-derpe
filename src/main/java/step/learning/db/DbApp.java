package step.learning.db;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbApp {
    private Connection connection;
    @Inject
    private RandomGenService randGen;

    public void demo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8",
                    "spu121",
                    "pass121");
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
        System.out.println("Connection OK");

        String sql = "CREATE TABLE IF NOT EXISTS randoms (" +
                     "id         CHAR(36) PRIMARY KEY," +
                     "rand_int   INT," +
                     "rand_float FLOAT," +
                     "rand_str   TEXT" +
                     ") Engine = InnoDB, DEFAULT CHARSET = utf8";

        try (Statement statement = connection.createStatement()) {  // ~ SqlCommand (ADO.NET)
            statement.executeUpdate(sql);
            System.out.println("TABLE OK");
        }
        catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }


        String insertSql = "INSERT INTO randoms (id, rand_int, rand_float, rand_str) VALUES (" +
                           "\"" + java.util.UUID.randomUUID() + "\", " +
                           randGen.randInt(256) + ", " +
                           randGen.randFloat() + ", " +
                           "\"" + randGen.randStr(10) + "\"" +
                           ");";

        try (Statement statement = connection.createStatement()) {  // ~ SqlCommand (ADO.NET)
            statement.executeUpdate(insertSql);
            System.out.println("INSERT OK");
        }
        catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
    }
}

/*
JBDC - технологія доступа до даних, аналог ADO.NET

1. Встановлюємо коннектор - драйвер БД
2. БД та рядок підключення (на прикладі Planetscale)
    jdbc:mysql://aws.connect.psdb.cloud/aspnet-applist?sslMode=VERIFY_IDENTITY
    jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8
 */