package com.onysakura.webtools.common.dto;

import com.onysakura.webtools.common.Constants;
import io.vertx.core.json.JsonObject;

public class Msg extends JsonObject {

    public static JsonObject ok() {
        return new Msg().put("status", Constants.Status.ok);
    }

    public static JsonObject ok(String msg) {
        return new Msg().put("status", Constants.Status.ok).put("msg", msg);
    }

    public static JsonObject fail() {
        return new Msg().put("status", Constants.Status.fail);
    }

    public static JsonObject fail(String msg) {
        return new Msg().put("status", Constants.Status.fail).put("msg", msg);
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
        return 1 == this.getInteger("status");
    }
}
