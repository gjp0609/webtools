#### Vert.x web DEMO

Vert.x native api server

build: `mvn package`

run: `./target/webtools`

```text
options:
    --http_port=8999
    --log_path=/data/logs
    --conf_file=/data/config.json
```

conf_file:

```json
{
  "vertxConfig": {
    "http_port": "8888",
    "log_path": "/data/logs",
    "jwt_private_key": "",
    "jwt_public_key": ""
  }
}
```

#### database

SQLite

sql:

```sql
-- user
create table user
(
    id       integer not null
        constraint user_pk primary key autoincrement,
    username text,
    password text,
    salt     text
);

create unique index user_username_uindex on user (username);


```
