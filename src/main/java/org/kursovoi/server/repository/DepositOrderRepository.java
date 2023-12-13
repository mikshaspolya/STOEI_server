package org.kursovoi.server.repository;

import org.kursovoi.server.model.DepositOrder;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.DepositOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositOrderRepository extends JpaRepository<DepositOrder, Long> {
    List<DepositOrder> findByUser(User user);

    List<DepositOrder> findByStatus(DepositOrderStatus status);

}
