# gatling-scaffold

Run Gatling simulations with gradle container:

```
docker run -it --rm --net="host" \
    -v "$PWD"/src/gatling/resources:/opt/gatling/conf \
    -v "$PWD"/src/gatling/:/opt/gatling/user-files \
    -v "$PWD"/build/reports/gatling:/opt/gatling/results \
    denvazh/gatling -s sample.BasicSimulation
```