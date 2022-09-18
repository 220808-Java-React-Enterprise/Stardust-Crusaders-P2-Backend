package com.revature.pokecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

@SpringBootApplication
public class PokeCareDriver {
    public static void main(String[] args) {
        SpringApplication.run(PokeCareDriver.class, args);
    }


}
