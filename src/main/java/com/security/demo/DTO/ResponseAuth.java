package com.security.demo.DTO;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ResponseAuth {
    private String jwt;
    private HttpStatus code;

}
