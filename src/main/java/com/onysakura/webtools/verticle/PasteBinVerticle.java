package com.onysakura.webtools.verticle;

import com.onysakura.webtools.common.Constants;
import com.onysakura.webtools.common.dto.Msg;
import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.config.log.LoggerUtil;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class PasteBinVerticle extends RouterVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(PasteBinVerticle.class);

    @Override
    public void start() throws Exception {
        routers.add(new RouterHandle(HttpMethod.GET, "/:code", this::get));
        routers.add(new RouterHandle(HttpMethod.GET, "/raw/:code", this::get));
        routers.add(new RouterHandle(HttpMethod.GET, "/redirect/:code", this::get));
        routers.add(new RouterHandle(HttpMethod.POST, "/save", this::set, true));
    }

    public void get(RoutingContext context) {
        String code = context.pathParams().get("code");
        log.info("code: {}", code);
        vertx.eventBus()
                .request(Constants.EventBusAddress.DB_PASTE_SELECT, new JsonObject().put("code", code))
                .onSuccess(ar -> {
                    Msg msg = Msg.fromJson(ar.body());
                    if (msg.isOk()) {
                        JsonObject row = msg.getJsonObject("row");
                        String routePath = context.currentRoute().getPath();
                        String content = row.getString("content");
                        if (routePath.startsWith("/redirect") && content.startsWith("http")) {
                            context.redirect(content);
                        } else if (routePath.startsWith("/raw")) {
                            context.end(content);
                        } else {
                            context.json(Msg.ok(content));
                        }
                    } else {
                        context.json(Msg.fail("code not found!"));
                    }
                }).onFailure(t -> {
                    log.warn("query error", t);
                    context.json(Msg.fail("query error!"));
                });
    }

    private void set(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        String code = body.getString("code");
        String content = body.getString("content");
        if (StringUtils.isBlank(code)) {
            code = RandomStringUtils.randomAlphanumeric(4);
        }
        vertx.eventBus()
                .request(Constants.EventBusAddress.DB_PASTE_INSERT,
                        new JsonObject()
                                .put("code", code)
                                .put("content", content)
                )
                .onSuccess(ar -> {
                    context.json(ar.body());
                }).onFailure(t -> {
                    log.warn("save error", t);
                    context.json(Msg.fail("save error!"));
                });
    }
}
