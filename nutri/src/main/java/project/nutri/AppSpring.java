package project.nutri;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class AppSpring {
	public static void main(String[] args) {
		//SpringApplication.run(NutriApplication.class, args);
		//SpringApplication.run(AppJavaFx.class, args);
		Application.launch(AppJavaFx.class, args);
	}

}
