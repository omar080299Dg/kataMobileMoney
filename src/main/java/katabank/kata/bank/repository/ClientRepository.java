package katabank.kata.bank.repository;

import katabank.kata.bank.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
