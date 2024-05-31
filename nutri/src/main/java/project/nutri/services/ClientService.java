package project.nutri.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nutri.entities.Client;
import project.nutri.repositories.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }
    
    public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.get();
    }

    public Client findByName(String name) {
        return repository.findByName(name);
    }

    public void saveOrUpdate(Client client) {
        if(client.getId() == null) {
            repository.save(client);
        }
        else {
            Client newClient = repository.findById(client.getId()).orElse(null);
            newClient.setName(client.getName());
            newClient.setBirthDate(client.getBirthDate());
            newClient.setAddress(client.getAddress());
            newClient.setWeight(client.getWeight());
            newClient.setHeight(client.getHeight());
            newClient.setRestrictions(client.getRestrictions());
            newClient.setGoals(client.getGoals());

            repository.save(newClient);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
