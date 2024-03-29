package com.onysakura.webtools.verticle;

import com.onysakura.webtools.common.config.Configs;
import com.onysakura.webtools.common.config.Constants;
import com.onysakura.webtools.common.config.EventBuses;
import com.onysakura.webtools.common.config.log.LoggerUtil;
import com.onysakura.webtools.common.dto.Msg;
import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.utils.SecurityUtil;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;
import java.util.Objects;

public class AuthVerticle extends RouterVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(AuthVerticle.class);
    private static final String ALGORITHM = "RS512";

    private JWTAuth auth;

    @Override
    public void start() throws Exception {
        routers.add(new RouterHandle(HttpMethod.POST, "/login", this::login, true));
        routers.add(new RouterHandle("/*", this::auth, true));

        JWTAuthOptions jwtAuthOptions = new JWTAuthOptions()
                .addPubSecKey(new PubSecKeyOptions().setAlgorithm(ALGORITHM).setBuffer(config().getString(Configs.JWT_PUBLIC_KEY.key())))
                .addPubSecKey(new PubSecKeyOptions().setAlgorithm(ALGORITHM).setBuffer(config().getString(Configs.JWT_PRIVATE_KEY.key())));
        auth = JWTAuth.create(vertx, jwtAuthOptions);
    }

    public void login(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        String username = body.getString("username");
        String passwordMD5 = body.getString("password");
        log.info("login: {}, {}", username, passwordMD5);
        vertx.eventBus()
                .request(EventBuses.DB_USER_SELECT, new JsonObject().put("username", username))
                .onSuccess(ar -> {
                    Msg msg = Msg.fromJson(ar.body());
                    if (msg.isOk()) {
                        JsonObject row = msg.getJsonObject("row");
                        String passwordDB = row.getString("password");
                        String salt = row.getString("salt");
                        String password = SecurityUtil.md5(passwordMD5 + SecurityUtil.md5(salt));
                        if (Objects.equals(passwordDB, password)) {
                            String token = auth.generateToken(
                                    new JsonObject().put("username", username),
                                    new JWTOptions().setAlgorithm(ALGORITHM).setExpiresInMinutes(30)
                            );
                            context.json(Msg.ok().put("token", token));
                        } else {
                            context.json(Msg.fail("password incorrect!"));
                        }
                    } else {
                        context.json(Msg.fail("user not found!"));
                    }
                }).onFailure(t -> {
                    log.warn("get user error", t);
                    context.json(Msg.fail("get user error!"));
                });
    }

    public void auth(RoutingContext context) {
        String normalizedPath = context.normalizedPath();
        HttpMethod method = context.request().method();
        log.debug("auth: {} {}", method, normalizedPath);
        if (normalizedPath.contains("/auth/") && Arrays.asList(HttpMethod.POST, HttpMethod.PUT).contains(method)) {
            String authorization = context.request().getHeader(HttpHeaderNames.AUTHORIZATION);
            auth.authenticate(new JsonObject().put("token", authorization))
                    .onSuccess(user -> {
                        context.next();
                        log.debug("pass");
                    })
                    .onFailure(err -> {
                        log.debug("fail");
                        context.json(Msg.fail(Constants.Status.INVALID_TOKEN, "invalid token!"));
                    });
        } else {
            log.debug("pass");
            context.next();
        }
    }

}
