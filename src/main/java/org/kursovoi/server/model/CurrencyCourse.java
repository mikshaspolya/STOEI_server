package org.kursovoi.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "currency_course")
@Entity
@Data
@NoArgsConstructor
public class CurrencyCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private double costUsd;
    private double costEur;
    private double costRub;

}
