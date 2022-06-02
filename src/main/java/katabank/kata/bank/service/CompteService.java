package katabank.kata.bank.service;

import katabank.kata.bank.domain.Client;
import katabank.kata.bank.domain.Compte;
import katabank.kata.bank.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Compte findCompteById(Long id) {
        return compteRepository.findById(id).orElseThrow(() -> new RuntimeException("compte not found"));
    }
}
