package br.ifsp.techmaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TechMapsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechMapsApplication.class, args);
    }

}
