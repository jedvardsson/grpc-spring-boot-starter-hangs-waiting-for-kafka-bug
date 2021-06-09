package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HangsWaitingForKafkaWorkaroundApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HangsWaitingForKafkaWorkaroundApplication.class)
                .profiles("workaround")
                .build()
                .run(args);
    }
}