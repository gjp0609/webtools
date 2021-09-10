package com.onysakura.webtools.common;

import com.onysakura.webtools.common.jdbc.H2DBVerticle;
import com.onysakura.webtools.web.AuthServer;
import com.onysakura.webtools.web.ShortUrlServer;
import io.vertx.core.Verticle;

public enum Verticles {

    AUTH("/auth", new AuthServer()),
    ShortUrl("/api", new ShortUrlServer()),
    SQLITE("/sqlite", new H2DBVerticle()),
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
