package com.example.web_socket_sample.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatType {
    CREATE('C'), MESSAGE('M'), LEAVE('L');

    private final char code;
}
