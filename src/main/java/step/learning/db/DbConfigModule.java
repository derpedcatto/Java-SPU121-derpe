package step.learning.db;

import com.google.inject.AbstractModule;
import step.learning.db.JavaUtilRandomGenService;
import step.learning.db.RandomGenService;

public class DbConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RandomGenService.class)
                .to(JavaUtilRandomGenService.class);
    }
}
