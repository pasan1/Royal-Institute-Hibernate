package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.dao.CrudDAOImpl;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.entity.Student;

import java.util.List;

public class StudentDAOImpl extends CrudDAOImpl<Student, String> implements StudentDAO {
    @Override
    public String getLastStudentId() throws Exception {
        return (String) session.createNativeQuery("SELECT id FROM Student ORDER BY id DESC LIMIT 1").uniqueResult();
    }

    @Override
    public List<Student> getSearchStudentDetails(String key) throws Exception {
        return session.createNativeQuery("SELECT * FROM Student WHERE id=" + key + "").list();
//        return session.createQuery("FROM " + entity.getName()).list();
    }
}
