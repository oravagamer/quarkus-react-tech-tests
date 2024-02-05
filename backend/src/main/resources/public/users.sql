CREATE DATABASE quarkus;
CREATE SCHEMA quarkus_tests;
CREATE USER quarkus_tests WITH PASSWORD 'quarkus_tests';
GRANT CONNECT ON DATABASE quarkus TO quarkus_tests;
GRANT USAGE ON SCHEMA quarkus_tests TO quarkus_tests;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA quarkus_tests TO quarkus_tests;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA quarkus_tests TO quarkus_tests;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA quarkus_tests TO quarkus_tests;

CREATE USER quarkus_tests_liquibase WITH PASSWORD 'quarkus_tests_liquibase';
GRANT CONNECT ON DATABASE quarkus TO quarkus_tests_liquibase;
GRANT ALL PRIVILEGES ON SCHEMA quarkus_tests TO quarkus_tests_liquibase;
GRANT All PRIVILEGES ON ALL TABLES IN SCHEMA quarkus_tests TO quarkus_tests_liquibase;