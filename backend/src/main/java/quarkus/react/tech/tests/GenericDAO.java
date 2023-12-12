package quarkus.react.tech.tests;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public interface GenericDAO<E, PK> {
    public E findById(PK id);

    public void update(E entity);

    public void delete(PK id);

    public void insert(E entity);
    public void closeConnection();
}
