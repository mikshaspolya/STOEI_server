package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDto {

    private long id;
    private double interest;
    private long monthsToReturn;
    private String currency;
}
