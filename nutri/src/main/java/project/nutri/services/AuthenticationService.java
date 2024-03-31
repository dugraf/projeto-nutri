package project.nutri.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nutri.entities.User;

@Service
public class AuthenticationService
{
    @Autowired
    private UserService userService;

    public boolean authentication(String name, String password)
    {
        User user = userService.findByName(name);
        if(user != null)
        {
            user.setLastLogin(LocalDateTime.now());
            userService.save(user);
            return user.getPassword().equals(password);
        }
        return false;
    }
}
