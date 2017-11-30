package com.ideal.audit.common.framework;

import java.io.Serializable;

/**
 * Created by xingsen on 15/2/9.
 */
public class JsonAlert implements Serializable {
    private String title;
    private String message;
    private String type;

    public JsonAlert(String title, String message, WebMessageLevel level) {
        this.title = title;
        this.message = message;
        this.type = level.name().toLowerCase();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
