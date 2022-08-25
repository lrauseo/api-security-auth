package com.lrrauseo.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseTokenDTO {

    private String token;
    private String tipo;
    private String message;
    private String expiration;
}
