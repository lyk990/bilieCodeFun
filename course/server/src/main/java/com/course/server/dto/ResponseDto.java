package com.course.server.dto;

public class ResponseDto<T> {

    /**
     * whether request succeeded or not
     */
    private boolean success = true;


    private String code;

    private String message;

    /**
     * data, such as PageDto, ChapterDto
     */
    private T content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
