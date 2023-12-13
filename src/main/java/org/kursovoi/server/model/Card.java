package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.CardIssuer;
import org.kursovoi.server.model.constant.CardType;
import org.kursovoi.server.model.constant.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String holderName;
    private LocalDate dateOfExpire;
    private String cvv;
    private String pin;
    private Status status;
    private CardIssuer cardIssuer;
    private CardType type;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
