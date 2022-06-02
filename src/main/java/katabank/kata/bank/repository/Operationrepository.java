package katabank.kata.bank.repository;

import katabank.kata.bank.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Operationrepository extends JpaRepository<Operation, Long> {
}
