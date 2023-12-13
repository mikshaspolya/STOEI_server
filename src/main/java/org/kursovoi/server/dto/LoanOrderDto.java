package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOrderDto {

    private long id;
    private String dateOfIssue;
    private String dateOfEnd;
    private long sum;
    private String status;
    private long idLoan;
    private long idUser;
}
