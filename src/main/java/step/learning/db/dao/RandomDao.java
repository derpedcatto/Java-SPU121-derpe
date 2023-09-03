package step.learning.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import step.learning.db.dto.RandomRecord;
import step.learning.services.random.RandomGenService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
DAO - Data Access Object - об'єкт доступу до даних -
сукупність методів, які працюють з БД та DTO
 */

@Singleton
public class RandomDao {
    private final Connection connection;
    private final Logger logger; // Guice автоматично реєструє logger
    private final RandomGenService randGen;

    @Inject
    public RandomDao(@Named("Local")Connection connection, Logger logger, RandomGenService randGen) {
        this.connection = connection;
        this.logger = logger;
        this.randGen = randGen;
    }

    public void ensureCreate() {
        String sql = "CREATE TABLE IF NOT EXISTS randoms (" +
                "id         CHAR(36) PRIMARY KEY," +
                "rand_int   INT," +
                "rand_float FLOAT," +
                "rand_str   TEXT" +
                ") Engine = InnoDB, DEFAULT CHARSET = utf8";

        try (Statement statement = connection.createStatement()) {  // ~ SqlCommand (ADO.NET)
            statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() );
            throw new RuntimeException("CREATE fails. Details in log.");
        }
    }

    public List<RandomRecord> getAll() {
        String displaySql = "SELECT * FROM randoms";
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(displaySql);

            /*  JDBC                            DBMS
                 sql -------------------------> execute ->
                 res <------------------------- iterator
                 res.next() ------------------> iterator.next() ->
                 (1)-id <---------------------- [id, int, float, str]
                 (2)-int ...
                 res.getString(1) == id (першого рядку)

                 res.next() ------------------> iterator.next() ->
                 (1)-id <---------------------- [id, int, float, str]
                 (2)-int ... (заміна раніше одержаних даних)
                 res.getString(1) == id (другого рядку)
             */

            List<RandomRecord> ret = new ArrayList<>();
            while (res.next()) {
                ret.add( new RandomRecord(res));
            }
            return ret;
        }
        catch (SQLException  ex) {
            logger.log(Level.SEVERE, ex.getMessage() );
            throw new RuntimeException("GET fails. Details in log.");
        }
    }

    public void insertRandom() {
        // Підготовлений (параметризований) запит - SQL окремо, дані окремо
        String insertSQl = "INSERT INTO randoms(id, rand_int, rand_float, rand_str) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQl)) {
            // заповнення даних - за номерами параметрів (плейсхолдерів - ?)
            // на місце 1-го знака ? буде підставлено id як String
            preparedStatement.setString(1, UUID.randomUUID().toString());

            // !! у JDBC відклик починається з 1 !!

            preparedStatement.setInt(2, randGen.randInt(256));
            preparedStatement.setFloat(3, randGen.randFloat());
            preparedStatement.setString(4, randGen.randStr(10));

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() );
            throw new RuntimeException("INSERT fails. Details in log.");
        }
    }

    public void insert(RandomRecord record) {
        String insertSql = "INSERT INTO randoms(id, rand_int, rand_float, rand_str) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, record.getId().toString());
            preparedStatement.setInt(2, record.getRandInt());
            preparedStatement.setFloat(3, record.getRandFloat());
            preparedStatement.setString(4, record.getRandStr());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("INSERT fails. Details in log.");
        }
    }

    public void delete(RandomRecord record) {
        String sql = "DELETE FROM randoms WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, record.getId().toString());

            preparedStatement.executeUpdate();
        }
        catch (SQLException  ex) {
            logger.log(Level.SEVERE, ex.getMessage() );
            throw new RuntimeException("DELETE fails. Details in log.");
        }
    }

    public void update(RandomRecord record) {
        String updateSql = "UPDATE randoms " +
                           "SET rand_int = (?), rand_float = (?), rand_str = (?) " +
                           "WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, record.getRandInt());
            preparedStatement.setFloat(2, record.getRandFloat());
            preparedStatement.setString(3, record.getRandStr());
            preparedStatement.setString(4, record.getId().toString());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("UPDATE fails. Details in log.");
        }
    }

    public boolean recordExists(RandomRecord record) {
        String sql = "SELECT * FROM randoms WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, record.getId().toString());

            ResultSet res = preparedStatement.executeQuery();
            return res != null;
        }
        catch (SQLException  ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("DELETE fails. Details in log.");
        }
    }
}