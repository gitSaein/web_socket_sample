package com.example.web_socket_sample.dto;

import java.time.LocalDate;

import org.apache.logging.log4j.Level;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ChatMessageDto {
    @NonNull
	private int roomIdx;
    @NonNull
    private int senderIdx;
    @NonNull
    private char messageType;
    private String message;
    private LocalDate regDate;

}