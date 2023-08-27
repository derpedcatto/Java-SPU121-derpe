package step.learning.db;

import com.google.inject.Singleton;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class JavaUtilRandomGenService implements RandomGenService {
    private static final Random rand = new Random();

    @Override
    public int randInt(int bound) {
        return rand.nextInt(bound);
    }

    @Override
    public float randFloat() {
        return rand.nextFloat();
    }

    @Override
    public String randStr(int size) {
        byte[] arr = new byte[size];
        for (int i = 0; i < size; i++) {
            // ASCII Range 33 - 126
            arr[i] = (byte) ((char) rand.nextInt(93) + 33);
        }
        return new String(arr, StandardCharsets.UTF_8);
    }
}
