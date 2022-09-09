package org.example.to;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ToApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToApplication.class, args);
    }
}
