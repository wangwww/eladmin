package site.hyxy.rest.constants;

/**
 * 错误码定义：
 */
public enum IResultCode {
    // 请求成功
    SUCCESS(200, "成功"),

    // 请求失败
    ERROR(500),
    // 状态异常
    STATUS_ERROR(201);

    private Integer code;
    private String msg;

    IResultCode(Integer code) {
        this.code = code;
    }

    IResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return " 失败原因{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
