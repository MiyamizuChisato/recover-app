package com.zxc.find.recover.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Miya
 */
public class Response {
    private Integer code;
    private String message;
    private Map<String, Object> tags;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public static final Response SUCCEED() {
        Response response = new Response();
        response.setTags(new HashMap<String, Object>(256));
        response.setCode(200);
        response.setMessage("服务器响应成功");
        return response;
    }

    public static final Response DEFEAT() {
        Response response = new Response();
        response.setTags(new HashMap<String, Object>(256));
        response.setCode(400);
        response.setMessage("服务器响应失败");
        return response;
    }

    public final Response carry(String key, Object value) {
        this.getTags().put(key, value);
        return this;
    }
}
