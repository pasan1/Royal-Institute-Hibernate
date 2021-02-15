package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.StudentBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            studentDAO.save(new Student(
                    dto.getStudentId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact(),
                    Date.valueOf(dto.getDob()),
                    dto.getGender()
            ));
            tx.commit();
            return true;
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            studentDAO.update(new Student(
                    dto.getStudentId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContact(),
                    Date.valueOf(dto.getDob()),
                    dto.getGender()
            ));
            tx.commit();
            return true;
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            studentDAO.delete(id);
            tx.commit();
            return true;
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public StudentDTO searchStudent(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        Student student = null;
        try {
            tx = session.beginTransaction();
            student = studentDAO.search(id);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return new StudentDTO(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                String.valueOf(student.getDob()),
                student.getGender()
        );
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        ArrayList<StudentDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Student> all = studentDAO.getAll();
            for (Student student : all) {
                list.add(new StudentDTO(
                        student.getId(),
                        student.getStudentName(),
                        student.getAddress(),
                        student.getContact(),
                        String.valueOf(student.getDob()),
                        student.getGender()
                ));
            }
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public String getLastStudentId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        String lastId;
        try {
            tx = session.beginTransaction();
            lastId = studentDAO.getLastStudentId();
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }

        if (lastId == null) {
            return "S001";
        } else {
            int maxId = Integer.parseInt(lastId.replace("S", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "S00" + maxId;
            } else if (maxId < 100) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            return id;
        }
    }
}
