package co.jp.amazawa.training.demo.handler;

public enum BusinessErrorStatus {

    BAD_REQUEST("0001", "パラメータエラー"),
    NOT_FOUND("0002", "指定データなし"),
    CONFLICT("0003", "データ整合性違反エラー"),
    ;

    private final String code;
    private final String message;

    BusinessErrorStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
