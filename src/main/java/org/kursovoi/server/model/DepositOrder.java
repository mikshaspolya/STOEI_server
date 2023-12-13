package org.kursovoi.server.model;

import lombok.*;
import org.kursovoi.server.model.constant.DepositOrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "deposit_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DepositOrderStatus status;
    private long sum;
    private LocalDate dateOfIssue;
    private LocalDate dateOfEnd;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;
}
