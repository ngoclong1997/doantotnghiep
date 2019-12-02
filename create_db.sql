CREATE USER admin_user WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'Dragon1997';
	
--create db bc_publisher
CREATE DATABASE graduation_thesis
    WITH 
    OWNER = admin_user
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE graduation_thesis
  IS 'This is my graduation thesis project';
  
