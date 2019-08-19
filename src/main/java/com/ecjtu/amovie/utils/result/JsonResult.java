package com.ecjtu.amovie.utils.result;


import java.util.Objects;

/**
 * 返回给前端的数据格式
 *
 * @author me
 * Created by me on 2018-05-21 21:54
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class JsonResult<T> {

    private int code;
    private String message;
    private T data;

    private JsonResult() {
    }

    private JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <ResultType> JsonResult<ResultType> result(int code, String msg, ResultType data) {
        return JsonResult.<ResultType>builder().code(code).message(msg).data(data).build();
    }

    public static <ResultType> JsonResult<ResultType> success(String msg, ResultType data) {
        return result(200, msg, data);
    }

    public static <ResultType> JsonResult<ResultType> error(int status, String msg) {
        return result(status, msg, null);
    }

    public static <T> JsonResult.JsonResultBuilder<T> builder() {
        return new JsonResultBuilder<>();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonResult<?> that = (JsonResult<?>) o;
        return code == that.code &&
                message.equals(that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        return "JsonResult(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }


    public static class JsonResultBuilder<T> {
        private int code;
        private String message;
        private T data;

        JsonResultBuilder() {
        }

        JsonResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public JsonResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public JsonResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public JsonResult<T> build() {
            return new JsonResult<>(this.code, this.message, this.data);
        }

        @Override
        public String toString() {
            return "JsonResult.JsonResultBuilder(=" + this.code + ", message=" + this.message + ", data=" + this.data + ")";
        }
    }
}
