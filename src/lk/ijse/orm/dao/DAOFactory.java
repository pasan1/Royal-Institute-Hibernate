package lk.ijse.orm.dao;

import lk.ijse.orm.dao.custom.impl.CourseDAOImpl;
import lk.ijse.orm.dao.custom.impl.QueryDAOImpl;
import lk.ijse.orm.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.orm.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        COURSE, REGISTRATION, STUDENT, QUERY
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        switch (daoType) {
            case COURSE:
                return (T) new CourseDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
