package com.xtalk.server.xtalkserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author xin.z
 * @date 2021/1/20 12:05 上午
 */
@Slf4j
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    @ApiModelProperty("接口返回状态")
    private Status status;

    @ApiModelProperty("接口返回状态码")
    private Integer code;

    @ApiModelProperty("返回内容")
    private T payload;

    @ApiModelProperty("错误对象")
    private Object errors;

    @ApiModelProperty("元数据")
    private Object metadata;

    @ApiModelProperty("随机数")
    public static final Random random = new Random();

    public static <T> Response<T> create(Status status, T payload) {
        Response response = new Response<T>();
        response.setStatus(status);
        response.setCode(status.ordinal());
        response.setPayload(payload);
        return response;
    }

    public static Response badRequest() {
        return create(Status.BAD_REQUEST, null);
    }

    public static Response ok() {
        return create(Status.OK, null);
    }

    public static <T> Response<T> ok(T payload) {
        return create(Status.OK, payload);
    }

    public static <T> Response<T> ok(Optional<T> payload) {
        return ok(payload.get());
    }

    public static <T> Response<List<T>> list(List<T> payload) {
        return create(Status.OK, payload);
    }


    public static Response unauthorized() {
        return create(Status.UNAUTHORIZED, null);
    }

    public static Response validationException() {
        return create(Status.VALIDATION_EXCEPTION, null);
    }

    public static Response wrongCredentials() {
        return create(Status.WRONG_CREDENTIALS, null);
    }

    public static Response accessDenied() {
        return create(Status.ACCESS_DENIED, null);
    }

    public static Response notFound() {
        return create(Status.NOT_FOUND, null);
    }

    public static Response duplicateEntity() {
        return create(Status.DUPLICATE_ENTITY, null);
    }

    public static <T> Response<T> exception(String msg, Integer code) {
        Response response = create(Status.EXCEPTION, null);
        Optional.ofNullable(code).ifPresent(x -> response.setCode(x));
        response.setErrors(msg);
        return response;
    }

    @Deprecated
    public static <T> Response<T> exception(String msg) {
        return exception(msg, null);
    }

    public static <T> Response<T> exception(Exception e) {
        log.error("Response:[exception]", e);
        return exception(e.getMessage() == null ? e.toString() : e.getMessage());
    }

    public static <T> Response<T> exception() {
        return exception(null, null);
    }



    public enum Status {

        OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY,

    }

}

