# gatling-scaffold

Run Gatling simulations with gradle container:

```
docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project --net="host" gradle:5.3.1-jre11-slim
```