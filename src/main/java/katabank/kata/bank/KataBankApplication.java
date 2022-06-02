package katabank.kata.bank;

import katabank.kata.bank.domain.Client;
import katabank.kata.bank.domain.Compte;
import katabank.kata.bank.repository.ClientRepository;
import katabank.kata.bank.repository.CompteRepository;
import katabank.kata.bank.repository.Operationrepository;
import katabank.kata.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class KataBankApplication implements CommandLineRunner {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    Operationrepository operationrepository;
    @Autowired
    OperationService operationService;

    public static void main(String[] args) {
        SpringApplication.run(KataBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();
        client.setNom("Omar");
        client.setEmail("99omar.niang@gmail.com");
        clientRepository.save(client);
        Compte compte= new Compte();
        compte.setSolde(200d);
        compte.setClient(client);
        compte.setDateCreation(Date.from(Instant.now()));
        compte.setDecouvert(100d);
        compteRepository.save(compte);
       // operationService.deposit(200d, compte);
        operationService.retrait(2000000d, compte);
    }
}
