package project.nutri.resources;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.nutri.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource
{
    public ResponseEntity<User> findAll()
    {
        User u = new User(1L, "Teste", "teste@gmail.com", "teste", LocalDateTime.now(), LocalDateTime.now());
        return ResponseEntity.ok().body(u);
    }
}