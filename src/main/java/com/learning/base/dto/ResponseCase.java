package com.learning.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCase {

    INTERNAL_EXCEPTION(0, "INTERNAL_EXCEPTION"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    SUCCESS(1000, "SUCCESS"),

    ERROR(4, "ERROR"),
    ROOM_NOT_AVAILABLE(5, "ROOM_NOT_AVAILABLE"),
    IN_USE(6, "IN_USE"),
    EXIST_USERNAME_OR_EMAIL(8, "EXIST_USERNAME_OR_EMAIL"),

    NOT_FOUND(7, "NOT_FOUND");

    private final int code;
    private final String message;

    ResponseCase(int code, String message) {
        this.code = code;
        this.message = message;
    }
}