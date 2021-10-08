package com.example.web_socket_sample.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Greeting {
	private String content;
}
