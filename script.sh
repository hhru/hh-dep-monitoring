#!/usr/bin/env bash

WORKDIR=.
SERVICE_DIR=/srv/hh-dep-monitoring
SERVER=a.linkov@hh-dep-monitoring.pyn.ru

cd ${WORKDIR}

if [ -z "$1" ] || [ -z "$2" ]
	then
		echo 'ERROR: provide github and bamboo tokens'
		exit
fi

mvn clean install -P docker
mvn clean compile -P frontend

docker save -o ${WORKDIR}/target/service-monitoring.tar service-monitoring:latest

sed '/master.jdbcUrl/s/localhost:5432/postgres-monitoring/' \
        ${WORKDIR}/hh-dep-monitoring-service/src/etc/service.properties \
        >> ${WORKDIR}/target/service.properties
echo 'github.oauth='$1 >> ${WORKDIR}/target/service.properties
echo 'bamboo.link='$2 >> ${WORKDIR}/target/service.properties

ssh ${SERVER} "cd ${SERVICE_DIR} && git reset --hard HEAD && git pull"
ssh ${SERVER} "rm -rf ${SERVICE_DIR}/frontend/build"

scp ${WORKDIR}/target/service-monitoring.tar ${SERVER}:${SERVICE_DIR}/service-monitoring.tar
scp -rp ${WORKDIR}/frontend/build ${SERVER}:${SERVICE_DIR}/frontend/build
scp ${WORKDIR}/target/service.properties \
	${SERVER}:${SERVICE_DIR}/src/etc/service.properties

ssh ${SERVER} "docker load -i ${SERVICE_DIR}/service-monitoring.tar"
ssh ${SERVER} "cd ${SERVICE_DIR} && docker-compose up -d --force-recreate && docker image prune -f"

rm ${WORKDIR}/target/service.properties
rm ${WORKDIR}/target/service-monitoring.tar
