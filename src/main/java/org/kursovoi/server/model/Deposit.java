package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.Currency;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double interest;
    private long monthToExpire;
    private Currency currency;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "deposit", fetch = FetchType.LAZY)
    private List<DepositOrder> depositOrders;
}
