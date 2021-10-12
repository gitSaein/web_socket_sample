package com.example.web_socket_sample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
    private String type;
    private String message;
}