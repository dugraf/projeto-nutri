package project.nutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.nutri.entities.ClientPhone;

public interface ClientPhoneRepository extends JpaRepository<ClientPhone, Long> {
    
}
