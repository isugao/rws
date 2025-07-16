package com.gao.user.common;

/**
 * API调用结果状态枚举
 */
public enum ApiResultCode {

    /**
     * 操作成功
     **/
    SUCCESS(2000, "操作成功"),


    /**
     * 操作失败
     **/
    FAIL(4000, "操作失败"),

    /**
     * 登录失败
     **/
    LOGIN_EXCEPTION(4001, "登录失败"),
    /**
     * 非法访问
     **/
    UNAUTHORIZED(4002, "非法访问"),
    /**
     * 没有权限
     **/
    NOT_PERMISSION(4003, "没有权限"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(4004, "你请求的资源不存在"),

    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION(4005, "系统异常"),

    /**
     * 请求参数校验异常
     **/
    PARAMETER_EXCEPTION(4006, "请求参数校验异常"),
    /**
     * 请求参数解析异常
     **/
    PARAMETER_PARSE_EXCEPTION(4007, "请求参数解析异常"),
    /**
     * HTTP内容类型异常
     **/
    HTTP_MEDIA_TYPE_EXCEPTION(4008, "HTTP内容类型异常"),
    /**
     * 系统处理异常
     **/
    SPRING_BOOT_PLUS_EXCEPTION(4009, "系统处理异常"),


    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(4010, "METHOD NOT SUPPORTED"),
    /**
     * 业务处理异常
     **/
    BUSINESS_EXCEPTION(4011, "业务处理异常"),
    /**
     * 数据库处理异常
     **/
    DAO_EXCEPTION(4012, "数据库处理异常"),
    /**
     * 验证码校验异常
     **/
    VERIFICATION_CODE_EXCEPTION(4013, "验证码校验异常"),
    /**
     * 登录授权异常
     **/
    AUTHENTICATION_EXCEPTION(4014, "登录授权异常"),
    /**
     * 没有访问权限
     **/
    UNAUTHENTICATED_EXCEPTION(4015, "没有访问权限"),
    /**
     * JWT Token解析异常
     **/
    JWTDECODE_EXCEPTION(4017, "Token解析异常"),

    /**
     * 限制登录异常
     **/
    LIMITLOGIN_EXCEPTION(4018, "限制登录异常"),


    DOWNSTREAM_API_ERROR(4019, "下游api接口处理异常"),

    USER_EXIST_ERROR(4101, "用户已存在"),


    /**
     * openapi系统异常
     **/
    OPEN_API_ERROR(5000, "接口调用失败"),
    /**
     * openapi系统异常
     **/
    OPEN_API_SYSTEM_EXCEPTION(5001, "系统异常"),

    /**
     * openapi appkey异常
     **/
    OPEN_API_APP_KEY_ERROR(5002, "appKey验证异常"),

    /**
     * openapi签名验证失败
     **/
    OPEN_API_SIGN_CHECK_ERROR(5003, "签名验证失败"),

    /**
     * openapi业务处理异常
     **/
    OPEN_API_BUSINESS_ERROR(5004, "业务处理异常"),
    /**
     * openapi请求参数校验异常
     **/
    OPEN_API_PARAMETER_EXCEPTION(5005, "请求参数异常"),

    /**
     * openapi请求方法不支持
     */
    OPEN_API_METHOD_NOT_SUPPORTED(5006, "请求方法不支持"),
    ;

    /**
     * 开放接口异常CODE值集合
     */
    public static final int[] OPEN_API_CODES = new int[]{OPEN_API_ERROR.getCode(), OPEN_API_SYSTEM_EXCEPTION.getCode(),
            OPEN_API_APP_KEY_ERROR.getCode(), OPEN_API_SIGN_CHECK_ERROR.getCode(),
            OPEN_API_BUSINESS_ERROR.getCode(), OPEN_API_PARAMETER_EXCEPTION.getCode(),
            OPEN_API_METHOD_NOT_SUPPORTED.getCode()};

    private final int code;
    private final String message;

    ApiResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResultCode getApiResultCode(int code) {
        ApiResultCode[] ecs = ApiResultCode.values();
        for (ApiResultCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
