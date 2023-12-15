package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с конфигурацией проекта для тестов.
 */
@Config.Sources({"classpath:apteka_tests_config.properties"})
public interface AptekaTestsConfig extends Config {

    /**
     * Метод возвращает параметр inputSearch из apteka_tests_config.properties.
     *
     * @return - параметр строки поиска.
     */
    String inputSearch();

}
