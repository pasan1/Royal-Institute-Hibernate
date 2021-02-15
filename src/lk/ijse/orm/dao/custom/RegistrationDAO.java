package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration, String> {
    public String getLastRegId() throws Exception;
}
