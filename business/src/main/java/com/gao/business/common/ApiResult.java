package com.gao.business.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * API调用结果容器对象
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
//@Builder
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 8004487252556526569L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T result;

    public ApiResult() {

    }

    public static <T> ApiResult<T> result(ApiResultCode apiResultCode, T data) {
        return result(apiResultCode.getCode(), apiResultCode.getMessage(), data);
    }

    public static <T> ApiResult<T> result(Integer code, String message, T data) {
        return new ApiResult<T>().setCode(code).setMsg(message).setResult(data);
    }

    public static <T> ApiResult<T> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return result(ApiResultCode.SUCCESS, data);
    }

    public static <T> ApiResult<T> fail() {
        return fail(ApiResultCode.FAIL);
    }

    public static <T> ApiResult<T> fail(ApiResultCode ApiResultCode) {
        return result(ApiResultCode.getCode(), ApiResultCode.getMessage(), null);
    }

    public static <T> ApiResult<T> fail(String message) {
        return result(ApiResultCode.FAIL.getCode(), message, null);

    }

    public static <T> ApiResult<T> fail(Integer code, String message) {
        return result(code, message, null);
    }

    public static <T> ApiResult<T> fail(ApiResultCode apiResultCode, T data) {
        return result(apiResultCode, data);
    }
}
