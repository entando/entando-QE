FROM entando/entando-smoke-tests-base:latest
COPY . /entando-smoke-tests/
WORKDIR /entando-smoke-tests
RUN mvn verify -DskipTests
ENV ENTANDO_RUN_HEADLESS=true

