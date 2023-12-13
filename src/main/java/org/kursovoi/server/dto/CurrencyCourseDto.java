package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class CurrencyCourseDto {

    private long id;
    private String date;
    private double costUsd;
    private double costEur;
    private double costRub;
}
