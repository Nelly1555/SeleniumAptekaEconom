package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с сосновной конфигурацией проекта.
 */
@Config.Sources({"classpath:config.properties"})
public interface BaseConfig extends Config {

    String url();
    String driverPath();
    String driverProperty();

}
