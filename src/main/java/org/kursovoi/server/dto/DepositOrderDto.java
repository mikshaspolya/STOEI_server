package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kursovoi.server.model.constant.DepositOrderStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositOrderDto {

    private long id;
    private String status;
    private long sum;
    private String dateOfIssue;
    private String dateOfEnd;
    private long idDeposit;
    private long idUser;
}
