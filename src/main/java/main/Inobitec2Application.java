package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Inobitec2Application {

	public static void main(String[] args) {
		SpringApplication.run(Inobitec2Application.class, args);
	}

}
