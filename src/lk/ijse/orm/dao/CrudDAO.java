package lk.ijse.orm.dao;

import lk.ijse.orm.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID extends Serializable> extends SuperDAO {
    List<T> getAll() throws Exception;

    T search(ID key) throws Exception;

    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(ID key) throws Exception;
}
