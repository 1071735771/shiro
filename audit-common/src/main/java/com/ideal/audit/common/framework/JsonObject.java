package com.ideal.audit.common.framework;

import java.io.Serializable;

/*
 * Created by xingsen on 15/2/9.
 */
public class JsonObject<T> implements Serializable {
    public final static String typeRefresh = "refresh";
    public final static String typeAlert = "alert";
    public final static String typeUpdater = "updater";
    public final static String typeDialog = "dialog";
    public final static String typeData = "data";
    public final static String typeRedirect = "redirect";

    private String type;
    private T data;
    private boolean isSuccess = true;
    private String message;

    public static JsonObject refresh(String message) {
        return new JsonObject<String>(typeRefresh, message);
    }

    public static JsonObject refresh() {
        return new JsonObject<String>(typeRefresh, "");
    }

    public static JsonObject updater(String html) {
        return new JsonObject<String>(typeUpdater, html);
    }

    public static JsonObject dialog(JsonDialog dialog) {
        return new JsonObject<JsonDialog>(typeDialog, dialog);
    }

    public static JsonObject<String> success() {
        return new JsonObject<String>(typeData, "", true, "");
    }

    public static <T> JsonObject<T> success(T data) {
        return new JsonObject<T>(typeData, data, true, "");
    }

    public static JsonObject<String> error(String message) {
        return new JsonObject<String>(typeDialog, message, false, message);
    }

    public static JsonObject<String> redirect(String url){
        return new JsonObject<String>(typeRedirect, url);
    }

    public static JsonObject alert(String message, WebMessageLevel level) {
        return new JsonObject<JsonAlert>(typeAlert, new JsonAlert(level.getText(), message, level));
    }


    public JsonObject(String type, T data) {
        this(type, data, true, "");
    }

    public JsonObject(String type, T data, boolean success, String message) {
        this.type = type;
        this.data = data;
        this.isSuccess = success;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
