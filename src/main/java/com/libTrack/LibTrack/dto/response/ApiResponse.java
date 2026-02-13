package com.libTrack.LibTrack.dto.response;

import com.libTrack.LibTrack.dto.BIbliotecarioDTO;
import com.libTrack.LibTrack.model.Bibliotecario;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private int code;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    public ApiResponse() {}

    public ApiResponse(int code, String message, LocalDateTime timestamp, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }
}
