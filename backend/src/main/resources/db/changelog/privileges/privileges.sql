

IF NOT EXISTS(SELECT * FROM pg_catalog.pg_roles WHERE rolname = ''quarkus_tests'')
    THEN
        CREATE USER quarkus_tests WITH PASSWORD ''quarkus_tests'';
    END IF;
    GRANT CONNECT ON DATABASE quarkus TO quarkus_tests;
    GRANT USAGE ON SCHEMA quarkus_tests TO quarkus_tests;
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA quarkus_tests TO quarkus_tests;
    GRANT USAGE ON ALL SEQUENCES IN SCHEMA quarkus_tests TO quarkus_tests;
    GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA quarkus_tests TO quarkus_tests;
