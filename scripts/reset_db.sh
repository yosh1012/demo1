#!/bin/bash

set -euo pipefail

WORKDIR="/home/yoshi/demo1/"
cd ${WORKDIR}

DB_NAME="demo1"
DB_USER="demo1_user"
DB_PASS="Vfr800f!"

echo "reset DB dbname: ${DB_NAME} dbuser: ${DB_USER}..."

sudo -u postgres psql <<EOF

SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '${DB_NAME}' AND pid <> pg_backend_pid();
DROP DATABASE IF EXISTS ${DB_NAME};
DROP USER IF EXISTS ${DB_USER};
CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASS}';
CREATE DATABASE ${DB_NAME} OWNER ${DB_USER} ENCODING 'UTF8';
\c ${DB_NAME}
GRANT ALL ON SCHEMA public TO ${DB_USER};
EOF

if [[ $? -ne 0 ]];then
  echo "ERROR: sql failed, exit with 1"
  exit 1
fi

export PGPASSWORD=${DB_PASS}

echo "applying initial schema..."
psql -h localhost -U ${DB_USER} -d ${DB_NAME} -f database/migrations/V1__initial_schema.sql

if [[ $? -ne 0 ]];then
  echo "ERROR: sql failed, exit with 1"
  exit 1
fi

echo "Database reset complete!"

exit 0
