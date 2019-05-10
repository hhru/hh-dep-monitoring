#!/usr/bin/env bash

WORKDIR=.
SERVER=a.linkov@hh-dep-monitoring.pyn.ru

cd $WORKDIR

if [ -z "$1" ] || [ -z "$2" ]
	then 
		echo 'ERROR: provide github and bamboo tokens'
		exit
fi

mvn clean install -P docker
mvn clean compile -P frontend

docker save -o ./target/service-monitoring.tar service-monitoring:latest

sed '/master.jdbcUrl/s/localhost:5432/postgres-monitoring/' \
        ./hh-dep-monitoring-service/src/etc/service.properties \
        >> ./target/service.properties
echo 'github.oauth='$1 >> ./target/service.properties
echo 'bamboo.link='$2 >> ./target/service.properties	


scp target/service-monitoring.tar $SERVER:/srv/hh-dep-monitoring/service-monitoring.tar
scp -rp frontend/build $SERVER:/srv/hh-dep-monitoring/frontend/build
scp frontend/nginx.conf $SERVER:/srv/hh-dep-monitoring/frontend/nginx.conf
scp docker-compose.yml $SERVER:/srv/hh-dep-monitoring/docker-compose.yml
scp hh-dep-monitoring-service/src/etc/hibernate.properties \ 
	$SERVER:hh-dep-monitoring-service/src/etc/hibernate.properties
scp target/service.properties \
	$SERVER:hh-dep-monitoring-service/src/etc/service.properties	

ssh $SERVER "docker load -i /srv/hh-dep-monitoring/service-monitoring.tar"
ssh $SERVER "cd /srv/hh-dep-monitoring"
ssh $SERVER "docker-compose start"

rm target/service.properties
rm target/service-monitoring.tar
