package project.nutri.services.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException()
    {
        super("Usuário ou senha inválidos!");
    }   
}
