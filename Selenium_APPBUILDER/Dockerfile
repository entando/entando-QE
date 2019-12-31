FROM entando/entando-smoke-tests-base:5.2.0-SNAPSHOT
COPY --chown=maven:root . /entando-smoke-tests/
WORKDIR /entando-smoke-tests
RUN  mvn verify -Dtags=none && chmod a+rw /entando-smoke-tests -R
ENV ENTANDO_RUN_HEADLESS=true

