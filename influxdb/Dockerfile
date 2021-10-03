FROM influxdb:1.8.9-alpine

WORKDIR /app
COPY entrypoint.sh ./
RUN chmod u+x entrypoint.sh
COPY influxdb.conf /etc/influxdb/influxdb.conf

ENTRYPOINT ["/app/entrypoint.sh"]
