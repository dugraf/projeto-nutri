package project.nutri.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.nutri.entities.User;
import project.nutri.repositories.UserRepository;
import project.nutri.services.utils.Encrypt;

@Configuration
@Profile("prod")
public class Config implements CommandLineRunner
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "SUPERVISOR", "eduardor.graf@hotmail.com", "123", LocalDateTime.now(), null);
        User u2 = new User(null, "Luciana", "luciana.rodrigues72@hotmail.com", "110872", LocalDateTime.now(), null);
        userRepository.saveAll(Arrays.asList(u1, u2));
        User u3 = new User(null, "BALTAZAR", "23124@hotmail.com", "123", LocalDateTime.now(), null);
        userRepository.save(u3);
    }
}
