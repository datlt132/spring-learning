package com.learning.base.dto;

import lombok.Getter;

@Getter
public class ServerResponse {

    public static final ServerResponse SUCCESS = new ServerResponse(ResponseCase.SUCCESS);
    public static final ServerResponse ERROR = new ServerResponse(ResponseCase.ERROR);

    private final ResponseCase status;
    private Object content;

    private ServerResponse(ResponseCase responseCase) {
        this.status = responseCase;
    }

    private ServerResponse(ResponseCase responseCase, Object content) {
        this.status = responseCase;
        this.content = content;
    }

    public static ServerResponse successWith(Object content) {
        return new ServerResponse(ResponseCase.SUCCESS, content);
    }

    public static ServerResponse errorWith(Object content) {
        return new ServerResponse(ResponseCase.ERROR, content);
    }

    public static ServerResponse with(ResponseCase responseCase) {
        return new ServerResponse(responseCase);
    }

    public static ServerResponse with(ResponseCase responseCase, Object content) {
        return new ServerResponse(responseCase, content);
    }
}
