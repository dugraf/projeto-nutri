package project.nutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.nutri.entities.ClientEmail;
import project.nutri.entities.ClientPhone;
import project.nutri.repositories.ClientPhoneRepository;

@Service
public class ClientPhoneService {
    @Autowired
    private ClientPhoneRepository repository;

    public List<ClientPhone> findAll() {
        return repository.findAll();
    }
    
    public ClientPhone findById(Long id) {
        Optional<ClientPhone> obj = repository.findById(id);
        return obj.get();
    }

    // public void saveOrUpdate(ClientPhone clientPhone) { //TODO
    //     if(clientPhone.getId() == null) {
    //         repository.save(clientPhone);
    //     }
    //     else { //FIXME - LOGICA
    //         ClientPhone newClientPhone = repository.findById(clientPhone.getId()).orElse(null);
    //         newClientPhone.setPhone(clientPhone.getPhone());

    //         repository.save(newClientPhone);
    //     }
    // }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
