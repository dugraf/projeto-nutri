package project.nutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nutri.entities.Client;
import project.nutri.entities.ClientEmail;
import project.nutri.repositories.ClientEmailRepository;

@Service
public class ClientEmailService {
    @Autowired
    private ClientEmailRepository repository;

    public List<ClientEmail> findAll() {
        return repository.findAll();
    }
    
    public ClientEmail findById(Long id) {
        Optional<ClientEmail> obj = repository.findById(id);
        return obj.get();
    }

    // public void saveOrUpdate(ClientEmail clientEmail) { //TODO
    //     if(clientEmail.getId() == null) {
    //         repository.save(clientEmail);
    //     }
    //     else {
    //         ClientEmail newClientEmail = repository.findById(clientEmail.getId()).orElse(null);
    //         newClientEmail.setEmail(clientEmail.getEmail());

    //         repository.save(newClientEmail);
    //     }
    // }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
