package com.example.web_socket_sample.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RabbitMq {
    CREATE("create.room.%d"), MESSAGE("message.%d"), LEAVE("leave.room.%d");

    private final String key;
}
