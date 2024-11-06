package store.repository;

public interface JpaRepository<T> {
    void save(final T t);
}
