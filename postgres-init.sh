#!bin/bash

docker pull postgres:latest
mkdir -p "$HOME"/docker/volumes/postgres
docker run --rm  --restart=always --name pg-docker -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v "$HOME"/docker/volumes/postgres:/var/lib/postgresql/data  postgres

# if postgres password is different here are the
# steps to change it:

# docker exec -it pg-docker /bin/bash
# psql -h localhost -U postgres -d postgres
# alter user postgres with password 'postgres';