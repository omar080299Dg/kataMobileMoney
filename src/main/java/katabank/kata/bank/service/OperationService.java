package katabank.kata.bank.service;

import katabank.kata.bank.domain.Compte;
import katabank.kata.bank.domain.Operation;
import katabank.kata.bank.repository.CompteRepository;
import katabank.kata.bank.repository.Operationrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OperationService {

    @Autowired
    private Operationrepository operationrepository;
    @Autowired
    CompteRepository compteRepository;
    public List<Operation>operationList(){
        return operationrepository.findAll();
    }
    public Operation afficherReleve(Long id){
        return operationrepository.findById(id).orElseThrow(() -> new RuntimeException("client not found"));
    }
    public void deposit(double montant, Compte compte){
        Compte cc = compteRepository.findById(compte.getId()).orElseThrow(null);
        cc.setSolde(cc.getSolde() + montant);
        compteRepository.save(cc);
        Operation operation = new Operation();
        operation.setDateOperation(Date.from(Instant.now()));
        operation.setTypeOperation("Depot");
        operation.setMontant(montant);
        operation.setCompte(cc);
        operationrepository.save(operation);
    }
    public void retrait(double montant, Compte compte){
        Compte cc = compteRepository.findById(compte.getId()).orElseThrow(null);
        if(cc.getDecouvert() >= cc.getSolde() - montant){
            log.error("Solde insuffisant");
            return ;
        }
        cc.setSolde(cc.getSolde() - montant);
        compteRepository.save(cc);
        Operation operation = new Operation();
        operation.setDateOperation(Date.from(Instant.now()));
        operation.setTypeOperation("retrait");
        operation.setMontant(montant);
        operation.setCompte(cc);
        operationrepository.save(operation);
    }
    public  void historique(){
        List<Operation> operationList = operationrepository.findAll();
        for(Operation operation : operationList){
            log.info("type Operation:"+operation.getTypeOperation());
            log.info("montant:"+operation.getMontant());
            log.info("Date opeartion:"+operation.getDateOperation());
            log.info("Compte:"+operation.getCompte().getId());
         }
    }
}
