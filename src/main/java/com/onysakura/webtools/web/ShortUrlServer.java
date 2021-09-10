package com.onysakura.webtools.web;

import com.onysakura.webtools.common.Constants;
import com.onysakura.webtools.common.dto.Msg;
import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.config.log.LoggerUtil;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;

public class ShortUrlServer extends RouterVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(ShortUrlServer.class);

    @Override
    public void start() {
        routers.add(new RouterHandle(HttpMethod.GET, "/get", this::get));
        routers.add(new RouterHandle(HttpMethod.GET, "/test", this::test));
        routers.add(new RouterHandle("/test", this::test));
    }

    long[] x = new long[1000];
    int i = 0;

    public void get(RoutingContext context) {
        String authorization = context.request().getHeader(HttpHeaderNames.AUTHORIZATION);
        vertx.eventBus().request(Constants.EventBusAddress.UserAuth, new JsonObject().put("token", authorization), ar -> {
            if (ar.succeeded()) {
                Msg msg = Msg.fromJson(ar.result().body());
                log.info("body: {}", msg);
                if (msg.isOk()) {
                    String name = msg.getString("username");
                    context.json(Msg.ok("Hello " + name));
                } else {
                    context.json(Msg.fail("auth error"));
                }
            } else {
                context.json(Msg.fail("error"));
            }
        });
    }

    public void test(RoutingContext context) {
        log.info("x: {}", Arrays.toString(x));
        context.end();
    }
}
