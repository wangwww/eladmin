package site.hyxy.rest.constants;

import lombok.Data;

import java.util.Objects;

// 统一的返回结果
@Data
public class IResult {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    public IResult() {
    }

    // 成功静态方法
    public static IResult success() {
        IResult result = new IResult();
        result.success = true;
        result.code = IResultCode.SUCCESS.getCode();
        result.message = IResultCode.SUCCESS.getMsg();
        return result;
    }

    // 错误静态方法
    public static IResult error() {
        IResult result = new IResult();
        result.success = false;
        result.code = IResultCode.ERROR.getCode();
        return result;
    }

    // 个业务返回错误码使用这个方法
    public static IResult error(IResultCode iResultCode) {
        IResult result = new IResult();
        result.success = false;
        result.code = iResultCode.getCode();
        result.message = iResultCode.getMsg();
        return result;
    }

    public IResult success(Boolean success) {
        this.success = success;
        return this;
    }

    public IResult code(Integer code) {
        this.code = code;
        return this;
    }

    public IResult message(String message) {
        this.message = message;
        return this;
    }

    public IResult appendMessage(String message) {
        this.message = this.message + message;
        return this;
    }

    public IResult data(Object data) {
        if (data != null) {
            this.data = data;
        }
        return this;
    }
}
