package step.learning.db.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

/*
DTO - Data Transfer Object - об'єкт для передачі даних, синонім - Entity, ORM тощо
-- "Пасивний" об'єкт, задача якого "перевести" дані з я когось формату (JSON, DB, XML, ...)
-- у формат мови програмування
 */

public class RandomRecord {
    private UUID id;
    private int randInt;
    private float randFloat;
    private String randStr;


    public RandomRecord() { }

    public RandomRecord(ResultSet res) throws SQLException {
        // Звертання до полів за назвою не вимагає певного порядку
        // їх слідування у результаті (у запиті)
        setId(UUID.fromString(res.getString("id")));
        setRandInt(res.getInt("rand_int"));
        setRandFloat(res.getFloat("rand_float"));
        setRandStr(res.getString("rand_str"));
    }

    public RandomRecord(UUID id, int randInt, float randFloat, String randStr) {
        this.id = id;
        this.randInt = randInt;
        this.randFloat = randFloat;
        this.randStr = randStr;
    }


    @Override
    public String toString() {
        return String.format(Locale.US,
                "%s... %d %f %s\n",
                getId().toString().substring(0, 4),
                getRandInt(),
                getRandFloat(),
                getRandStr());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRandInt() {
        return randInt;
    }

    public void setRandInt(int randInt) {
        this.randInt = randInt;
    }

    public float getRandFloat() {
        return randFloat;
    }

    public void setRandFloat(float randFloat) {
        this.randFloat = randFloat;
    }

    public String getRandStr() {
        return randStr;
    }

    public void setRandStr(String randStr) {
        this.randStr = randStr;
    }
}
