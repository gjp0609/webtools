package com.onysakura.webtools.verticle;

import com.onysakura.webtools.common.config.EventBuses;
import com.onysakura.webtools.common.dto.Msg;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.common.config.log.LoggerUtil;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCConnectOptions;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;

public class DBVerticle extends RouterVerticle {
    private static final LoggerUtil.Log log = LoggerUtil.getLogger(DBVerticle.class);

    private JDBCPool pool;

    @Override
    public void start() throws Exception {
        init();
        vertx.eventBus().consumer(EventBuses.DB_USER_SELECT, this::userSelectByUsername);
        vertx.eventBus().consumer(EventBuses.DB_PASTE_SELECT, this::pasteSelectByCode);
        vertx.eventBus().consumer(EventBuses.DB_PASTE_INSERT, this::pasteInsert);
    }

    private void userSelectByUsername(Message<JsonObject> message) {
        pool.preparedQuery("select * from user where username = ?")
                .execute(Tuple.of(message.body().getString("username")))
                .onSuccess(rows -> {
                    if (rows.size() > 0) {
                        for (Row row : rows) {
                            message.reply(Msg.ok().put("row", row.toJson()));
                            return;
                        }
                    } else {
                        message.reply(Msg.fail("user not found"));
                    }
                }).onFailure(t -> {
                    log.warn("query err", t);
                    message.reply(Msg.fail("query err"));
                });
    }

    private void pasteInsert(Message<JsonObject> message) {
        JsonObject body = message.body();
        String code = body.getString("code");
        String content = body.getString("content");
        log.info("save {}, {}", code, content);
        pool.preparedQuery("insert into paste(code, content) VALUES (?, ?)")
                .execute(Tuple.of(code, content))
                .onSuccess(rows -> {
                    log.info("insert rows: {}", rows);
                    message.reply(Msg.ok(code));
                }).onFailure(t -> {
                    log.warn("insert err", t);
                    message.reply(Msg.fail("insert err"));
                });
    }

    private void pasteSelectByCode(Message<JsonObject> message) {
        pool.preparedQuery("select * from paste where code = ?")
                .execute(Tuple.of(message.body().getString("code")))
                .onSuccess(rows -> {
                    if (rows.size() > 0) {
                        for (Row row : rows) {
                            message.reply(Msg.ok().put("row", row.toJson()));
                            return;
                        }
                    } else {
                        message.reply(Msg.fail("paste not found"));
                    }
                }).onFailure(t -> {
                    log.warn("query err", t);
                    message.reply(Msg.fail("query err"));
                });
    }

    private void init() {
        JsonObject dataSource = config().getJsonObject("dataSource");
        pool = JDBCPool.pool(
                vertx,
                new JDBCConnectOptions()
                        .setJdbcUrl(dataSource.getString("url"))
                        .setUser(dataSource.getString("user"))
                        .setPassword(dataSource.getString("password")),
                new PoolOptions()
                        .setMaxSize(16)
        );
    }
}
