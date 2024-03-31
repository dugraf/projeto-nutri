package project.nutri.services.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt
{
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encoder(String password)
    {
        return encoder.encode(password);
    }

    public static Boolean validatePassword(String password, String possiblePassoword)
    {
        return encoder.matches(password, possiblePassoword);
    }
}
