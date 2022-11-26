package com.example.firstservice;

import java.util.Random;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizationPort implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        Random random = new Random();
        var port = random.ints(10000, 51000)
                .findFirst()
                .getAsInt();

        server.setPort(port);
    }
}
