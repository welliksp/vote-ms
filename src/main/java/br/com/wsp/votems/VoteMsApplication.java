package br.com.wsp.votems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class VoteMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteMsApplication.class, args);
	}

}
