package com.ideal.audit.common.framework;

import java.io.Serializable;

/**
 * Created by xingsen on 15/2/9.
 */
public class WebMessage implements Serializable {
    private WebMessageLevel level;
    private String message;


    public WebMessage(String message, WebMessageLevel level) {
        this.message = message;
        this.level = level;
    }

    public WebMessageLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}