package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.dao.CrudDAOImpl;
import lk.ijse.orm.dao.custom.RegistrationDAO;
import lk.ijse.orm.entity.Registration;

public class RegistrationDAOImpl extends CrudDAOImpl<Registration, String> implements RegistrationDAO {
    @Override
    public int getLastRegId() throws Exception {
        return (int) session.createNativeQuery("SELECT regNo FROM Registration ORDER BY regNo DESC LIMIT 1").uniqueResult();
    }
}
