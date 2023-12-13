package org.kursovoi.server.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodOfCurrencyCoursesDto {

    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
}
