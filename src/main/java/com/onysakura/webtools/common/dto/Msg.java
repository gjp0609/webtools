package com.onysakura.webtools.common.dto;

import com.onysakura.webtools.common.config.Constants;
import io.vertx.core.json.JsonObject;

public class Msg extends JsonObject {

    public static JsonObject ok() {
        return new Msg().put("status", Constants.Status.OK);
    }

    public static JsonObject ok(String msg) {
        return new Msg().put("status", Constants.Status.OK).put("msg", msg);
    }

    public static JsonObject fail() {
        return new Msg().put("status", Constants.Status.FAIL);
    }

    public static JsonObject fail(String msg) {
        return new Msg().put("status", Constants.Status.FAIL).put("msg", msg);
    }

    public static JsonObject fail(int status, String msg) {
        return new Msg().put("status", status).put("msg", msg);
    }

    public static Msg fromJson(Object jsonObject) {
        if (jsonObject != null) {
            if (jsonObject instanceof Msg) {
                return (Msg) jsonObject;
            } else if (jsonObject instanceof JsonObject) {
                return (Msg) new Msg().mergeIn((JsonObject) jsonObject);
            }
        }
        return new Msg();
    }

    public boolean isOk() {
        if (this.containsKey("status")) {
            return 1 == this.getInteger("status");
        }
        return false;
    }
}
