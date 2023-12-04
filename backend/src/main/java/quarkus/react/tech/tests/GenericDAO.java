package quarkus.react.tech.tests;

public interface GenericDAO<E, PK> {
    public E findById(PK id);

    public void update(E entity);

    public void delete(PK id);

    public void insert(E entity);
}
