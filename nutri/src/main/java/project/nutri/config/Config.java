package project.nutri.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import project.nutri.entities.User;
import project.nutri.repositories.UserRepository;

@Configuration
@Profile("prod")
public class Config implements CommandLineRunner
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u = new User(1L, "Teste", "teste@gmail.com", "teste", LocalDateTime.now(), LocalDateTime.now());
        userRepository.saveAll(Arrays.asList(u));
    }
    
}
