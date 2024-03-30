package project.nutri.services;

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
        return user != null && user.getPassword().equals(password);
    }
}
