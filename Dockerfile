FROM glassfish:latest

COPY out/artifacts/devoir02.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/