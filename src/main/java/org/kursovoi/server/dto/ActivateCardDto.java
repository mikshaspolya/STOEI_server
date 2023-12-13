package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class ActivateCardDto {

    private long cardId;
    private String newPin;
}
