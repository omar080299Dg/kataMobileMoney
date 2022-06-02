package katabank.kata.bank.service;

import katabank.kata.bank.IntegrationTest;
import katabank.kata.bank.domain.Client;
import katabank.kata.bank.domain.Compte;
import katabank.kata.bank.domain.Operation;
import katabank.kata.bank.repository.ClientRepository;
import katabank.kata.bank.repository.CompteRepository;
import katabank.kata.bank.repository.Operationrepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@IntegrationTest
public class OperationServiceTest {
    @Autowired
    OperationService operationService;
    @Autowired
    Operationrepository operationrepository;
    Operation operation;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;
    @BeforeEach
    void setUp(){
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

    @Test
    @DisplayName("given operation when operationList then ok")
    void givenOperationWhenOperationListThenOk(){
        List<Operation> operationList = operationService.operationList();
        Assertions.assertTrue(operationList.size()>0);
    }

    @Test
    @DisplayName("test to get an operation with an existing operationID")
    void testToGetAnOperationWithAnExistingOperationIDthenOk(){
        Operation operation = operationService.afficherReleve(1L);
        Assertions.assertNotNull(operation);
    }
    @Test
    @DisplayName("test to get an operation with a not existing operationID")
    void testToGetAnOperationWithAnExistingOperationIDthenThrowRuntimeException(){

        Assertions.assertThrows(RuntimeException.class, ()->{
            operationService.afficherReleve(1000L);
        });
    }
}
