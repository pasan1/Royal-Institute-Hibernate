package lk.ijse.orm.bo;

import lk.ijse.orm.bo.custom.impl.CourseBOImpl;
import lk.ijse.orm.bo.custom.impl.LoginBOImpl;
import lk.ijse.orm.bo.custom.impl.RegistrationBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public enum BOType {
        STUDENT, REGISTRATION, COURSE, LOGIN
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            case COURSE:
                return (T) new CourseBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            default:
                return null;
        }
    }
}
