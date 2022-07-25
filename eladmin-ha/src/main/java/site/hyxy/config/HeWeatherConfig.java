package site.hyxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "heweather")
@Data
public class HeWeatherConfig {
    private String key;
    private String id;
    private String location;
    private String domain;
}
