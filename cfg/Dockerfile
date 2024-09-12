FROM alpine:3.18.6
LABEL mantainer="NTTDATA Peru" \
      build="docker build --build-arg nombreArtefacto=artefacto -t microservicio:1.0 -f Dockerfile ." \
      run="docker run -dit --restart on-failure:5 --name microservicio -p 8080:8080 microservicio:1.0"

# General configuration
ENV JAVA_HOME=/usr/lib/jdk
ENV PATH=${PATH}:${JAVA_HOME}/bin
ENV TZ=America/Lima

# Default to UTF-8 file.encoding
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

# Artifact name
ARG nombreArtefacto=artefacto.jar
ENV nombreArtefacto ${nombreArtefacto}

# Assign and create user and group
ARG USERNAME=app
ARG GROUP_NAME=appcontainer
ARG USER_UID=10001
ARG USER_GID=20001
ARG USER_HOME=/home/app/

# New relic arguments 
# ARG NEW_RELIC_AGENT_VERSION=8.0.0
# ARG NEW_RELIC_AGENT_ENABLED
# ARG NEW_RELIC_ENVIRONMENT

RUN addgroup --gid ${USER_GID} ${GROUP_NAME} && \
    adduser --uid ${USER_UID} -D -G ${GROUP_NAME} ${USERNAME} &&  \
    chown -R ${USER_UID}:${USER_GID} ${USER_HOME}

# (Optional) Add extra packages for fontconfig and ttf-dejavu to support server-side image generation
RUN apk add --no-cache fontconfig libretls musl-locales musl-locales-lang ttf-dejavu tzdata zlib curl unzip && \
# Download and extract JDK 17
    wget -nv -O jdk.tar.gz https://aka.ms/download-jdk/microsoft-jdk-17.0.7-alpine-x64.tar.gz && \
    mkdir ${JAVA_HOME} && \
    tar xf jdk.tar.gz -C ${JAVA_HOME} --strip-components 1 --no-same-owner && \
# Download and extract New Relic
    # curl -q https://download.newrelic.com/newrelic/java-agent/newrelic-agent/${NEW_RELIC_AGENT_VERSION}/newrelic-java.zip --output ./newrelic.zip && \
    # unzip -q ./newrelic.zip -d /opt && \
    # rm /opt/newrelic/newrelic.yml && \
    # rm ./newrelic.zip && \
# Update and upgrade dependencies from Alpine
    apk update && apk upgrade && \
	apk add ca-certificates && \
	update-ca-certificates && \
# Change TimeZone
	apk add --update tzdata && \
# Clean cache
    rm -rf /var/cache/apk/*

# Change to non-root user
USER ${USER_UID}
# Copy *.jar and resources files
COPY --chown=$USER_UID:$USER_GID ${nombreArtefacto} ${USER_HOME}
WORKDIR ${USER_HOME}

# Run service without New Relic
CMD ["sh","-c","java -jar ${nombreArtefacto}"]
# Run service with New Relic
# CMD ["sh","-c","java -javaagent:/opt/newrelic/newrelic.jar -jar ${nombreArtefacto}"]