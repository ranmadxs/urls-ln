FROM ripley-docker.jfrog.io/ripley-dmr/dmr-base-jmsa@sha256:9cebdc2b48df2549824f726ba79f3910e2b407289d30fd665388f3d1f6115150 AS builder
WORKDIR /project
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests -s settings-template.xml
RUN java -Djarmode=layertools -jar target/dmr-integration-gde.jar extract

FROM ripley-docker.jfrog.io/ripley-dmr/dmr-base-jmsa@sha256:8a4d277cf53de8739c5d877af1da47453a3c96ad4466c6967f1e936f6eaef39d
RUN apk --no-cache add curl dumb-init
WORKDIR /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
USER javauser

COPY --from=builder --chown=javauser:javauser /project/dependencies/ ./
COPY --from=builder --chown=javauser:javauser /project/snapshot-dependencies/ ./
COPY --from=builder --chown=javauser:javauser /project/spring-boot-loader/ ./
COPY --from=builder --chown=javauser:javauser /project/application/ ./

## --------------------------------
EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher" ]
#CMD "dumb-init" "java" "org.springframework.boot.loader.JarLauncher"