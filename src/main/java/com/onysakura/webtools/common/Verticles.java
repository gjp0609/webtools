package com.onysakura.webtools.common;

import com.onysakura.webtools.api.ShortUrlServer;
import io.vertx.core.Verticle;

public enum Verticles {

    API("/api", new ShortUrlServer()),
    ;

    private final String path;
    private final Verticle verticle;

    Verticles(String path, Verticle verticle) {
        this.path = path;
        this.verticle = verticle;
    }

    public String getPath() {
        return path;
    }

    public Verticle getVerticle() {
        return verticle;
    }
}
