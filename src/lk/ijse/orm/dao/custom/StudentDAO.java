package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student, String> {
    public String getLastStudentId() throws Exception;

    public List<Student> getSearchStudentDetails(String key) throws Exception;
}
