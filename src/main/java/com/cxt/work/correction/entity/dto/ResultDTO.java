package com.cxt.work.correction.entity.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *     前端响应DTO
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/21 16:13
 */
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO implements Serializable {
    /**
     * 标识位
     */
    private Boolean flag;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultDTO SUCCESS(Object data) {
        return new ResultDTO(true, "success", data);
    }

    public static ResultDTO FAIL(String message) {
        return new ResultDTO(false, message, null);
    }
}
