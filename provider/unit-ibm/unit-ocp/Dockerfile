FROM openjdk:8-jdk-alpine

ARG ENTITLEMENT_URL
ARG APP_NAME=os-unit-service
ENV ENTITLEMENT_URL=$ENTITLEMENT_URL
ENV SLB_HOME /opt/slb
ENV APP_HOME $SLB_HOME/$APP_NAME
WORKDIR $APP_HOME

EXPOSE 8080

COPY target/unit-ocp-*.jar root.jar

ENTRYPOINT java -jar root.jar
