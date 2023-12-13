package org.kursovoi.server.model;

import lombok.*;
import org.kursovoi.server.model.constant.LoanOrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "loan_order")
@NoArgsConstructor
@AllArgsConstructor
public class LoanOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateOfIssue;
    private long sum;
    private LoanOrderStatus status;
    private LocalDate dateOfEnd;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
