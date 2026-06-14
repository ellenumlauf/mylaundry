psql "postgresql://postgres:postgres@localhost:5432/"
CREATE USER mylaundry WITH PASSWORD 'mylaundry';
CREATE DATABASE mylaundry OWNER mylaundry;
quit;
