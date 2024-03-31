package project.nutri.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.nutri.services.utils.Encrypt;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime registrationDate, lastLogin;

    public User(Long id, String name, String email, String password, LocalDateTime registrationDate, LocalDateTime lastLogin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = Encrypt.encoder(password);
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
    }
}
