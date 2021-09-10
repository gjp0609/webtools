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