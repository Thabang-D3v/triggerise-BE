package com.triggerise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private LocalDateTime timeStamp=LocalDateTime.now();
}
