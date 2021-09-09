package com.onysakura.webtools.common.router;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public class RouterHandle {
    private HttpMethod httpMethod;
    private String path;
    private Handler<RoutingContext> handler;

    public RouterHandle() {
    }

    public RouterHandle(String path, Handler<RoutingContext> handler) {
        this.path = path;
        this.handler = handler;
    }

    public RouterHandle(HttpMethod httpMethod, String path, Handler<RoutingContext> handler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.handler = handler;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public Handler<RoutingContext> getHandler() {
        return handler;
    }
}
