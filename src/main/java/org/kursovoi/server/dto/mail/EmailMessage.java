package org.kursovoi.server.dto.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessage {

    private String to;
    private String sub;
    private String body;
}
