package server;

import org.springframework.boot.SpringApplication;
import server.config.SpringConfig;

public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }
}

