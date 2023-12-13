package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.Currency;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double interest;
    private long monthsToReturn;
    private Currency currency;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY)
    private List<LoanOrder> loanOrders;
}
