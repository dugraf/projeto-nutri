package project.nutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nutri.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);
}
