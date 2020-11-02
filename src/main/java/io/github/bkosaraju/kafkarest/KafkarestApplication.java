package io.github.bkosaraju.kafkarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkarestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkarestApplication.class, args);
	}

}
