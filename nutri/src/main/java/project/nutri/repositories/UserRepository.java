package project.nutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nutri.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {}
