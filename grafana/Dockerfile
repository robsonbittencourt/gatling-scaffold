FROM grafana/grafana:8.2.6-ubuntu

USER root

RUN apt-get update && apt-get install -y gettext-base procps && rm -rf /var/lib/apt/lists/*

WORKDIR /etc/grafana

COPY datasources ./datasources
COPY dashboard/dashboards.yaml /etc/grafana/provisioning/dashboards
COPY dashboard/gatling-dashboard.json /etc/grafana/provisioning/dashboards

WORKDIR /app
COPY entrypoint.sh ./
RUN chmod u+x entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
