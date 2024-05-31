package project.nutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.nutri.entities.ClientEmail;

public interface ClientEmailRepository extends JpaRepository<ClientEmail, Long>{
    
}
