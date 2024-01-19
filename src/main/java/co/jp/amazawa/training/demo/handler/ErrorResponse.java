package co.jp.amazawa.training.demo.handler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

public record ErrorResponse(LocalDateTime timestamp, String code, String message, Map<String, Object> detail) {
    public ErrorResponse(BusinessErrorStatus businessErrorStatus, Map<String, Object> detail) {
        this(LocalDateTime.now(), businessErrorStatus.getCode(), businessErrorStatus.getMessage(), detail);
    }

    public ErrorResponse(BusinessErrorStatus businessErrorStatus) {
        this(LocalDateTime.now(), businessErrorStatus.getCode(), businessErrorStatus.getMessage(), Collections.emptyMap());
    }

    public ErrorResponse(LocalDateTime timestamp, BusinessErrorStatus businessErrorStatus, Map<String, Object> detail) {
        this(timestamp, businessErrorStatus.getCode(), businessErrorStatus.getMessage(), detail);
    }
}
