package project.nutri.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nutri.entities.User;
import project.nutri.services.utils.Encrypt;

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
            userService.saveOrUpdate(user);
            return Encrypt.validatePassword(password, user.getPassword());
        }
        return false;
    }
}
