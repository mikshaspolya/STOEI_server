package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class UpdateCurrencyCourseDto {

    private double costUsd;
    private double costEur;
    private double costRub;
}
