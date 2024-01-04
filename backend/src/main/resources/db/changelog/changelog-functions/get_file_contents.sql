create or replace function get_file_contents(filename text) returns bytea as
'
    declare
        lo_oid oid;
        retval bytea;
    begin
        lo_oid := lo_import(filename);
        retval := lo_get(lo_oid);
        perform lo_unlink(lo_oid);
        return retval;
    end;
' language plpgsql;