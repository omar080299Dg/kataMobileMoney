package katabank.kata.bank.service;

import katabank.kata.bank.domain.Client;
import katabank.kata.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("client not found"));
    }
}
