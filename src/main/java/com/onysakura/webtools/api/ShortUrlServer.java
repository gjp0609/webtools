package com.onysakura.webtools.api;

import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class ShortUrlServer extends AbstractVerticle implements RouterVerticle {

    @Override
    public void start() {
        routers.add(new RouterHandle(HttpMethod.GET, "/get", this::get));
        routers.add(new RouterHandle("/test", this::test));
    }

    public void get(RoutingContext context) {
        List<String> name = context.queryParam("name");
        context.json(new JsonObject().put("msg", "Hello " + name.get(0)));
    }

    public void test(RoutingContext context) {
        context.end();
    }
}
