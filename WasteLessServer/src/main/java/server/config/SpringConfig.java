package server.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import server.db.JPAConfig;

@Configuration
@EnableAutoConfiguration
@Import({JPAConfig.class})
@ComponentScan({"server.controller", "server.service"})
public class SpringConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
