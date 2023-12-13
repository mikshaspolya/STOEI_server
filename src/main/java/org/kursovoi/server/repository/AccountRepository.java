package org.kursovoi.server.repository;

import org.kursovoi.server.model.Account;
import org.kursovoi.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByHolder(User holder);

}
