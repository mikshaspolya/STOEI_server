package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OperationDto {

    private long id;
    private String type;
    private String description;
    private String timestamp;
    private long idUser;
}
