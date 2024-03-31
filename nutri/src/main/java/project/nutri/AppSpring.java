package project.nutri;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javafx.application.Application;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AppSpring {
	public static void main(String[] args) {
		Application.launch(AppJavaFx.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
