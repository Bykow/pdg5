FROM mysql:5.6.28

MAINTAINER Maxime Guillod <maxime.guillod@heig-vd.ch>

EXPOSE 3306

# script use to initialise the database
ADD creation_script.sql /docker-entrypoint-initdb.d
