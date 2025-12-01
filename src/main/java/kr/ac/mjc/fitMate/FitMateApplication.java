package kr.ac.mjc.fitMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FitMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitMateApplication.class, args);
	}

}
