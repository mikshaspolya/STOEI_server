package org.kursovoi.server.repository;

import org.kursovoi.server.model.Account;
import org.kursovoi.server.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByAccount(Account account);
}
