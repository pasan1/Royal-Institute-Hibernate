package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.RegistrationBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.CourseDAO;
import lk.ijse.orm.dao.custom.RegistrationDAO;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.RegistrationDTO;
import lk.ijse.orm.entity.Course;
import lk.ijse.orm.entity.Registration;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    private final RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);
    private final CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);

    private static RegistrationDTO student;
    private static RegistrationDTO course;
    private static String status;

    @Override
    public void setStatus(String status) {
        RegistrationBOImpl.status = status;
    }

    @Override
    public void setStudent(RegistrationDTO student) {
        RegistrationBOImpl.student = student;
    }

    @Override
    public RegistrationDTO getStudent() {
        return student;
    }

    @Override
    public void setCourse(RegistrationDTO course) {
        RegistrationBOImpl.course = course;
    }

    @Override
    public RegistrationDTO getCourse() {
        return course;
    }

    @Override
    public boolean saveStudent(RegistrationDTO dto) throws Exception {
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
                    dto.getDob(),
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
    public boolean updateStudent(RegistrationDTO dto) throws Exception {
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
                    dto.getDob(),
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
    public RegistrationDTO searchStudent(String id) throws Exception {
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
        return new RegistrationDTO(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDob(),
                student.getGender()
        );
    }

    @Override
    public ArrayList<RegistrationDTO> getSearchStudentDetails(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        ArrayList<RegistrationDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Student> all = studentDAO.getSearchStudentDetails(id);
            for (Student student : all) {
                list.add(new RegistrationDTO(
                        student.getId(),
                        student.getStudentName(),
                        student.getAddress(),
                        student.getContact(),
                        student.getDob(),
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
    public ArrayList<RegistrationDTO> getAllStudents() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        ArrayList<RegistrationDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Student> all = studentDAO.getAll();
            for (Student student : all) {
                list.add(new RegistrationDTO(
                        student.getId(),
                        student.getStudentName(),
                        student.getAddress(),
                        student.getContact(),
                        student.getDob(),
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

    @Override
    public boolean saveRegistration(RegistrationDTO dto) throws Exception {
        if (status.equals("Save")) {
            saveStudent(getStudent());
        } else if (status.equals("Update")) {
            updateStudent(getStudent());
        } else {
            throw new Exception("Error on Registration");
        }
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            registrationDAO.save(new Registration(
                    Integer.parseInt(dto.getRegNo()),
                    dto.getRegDate(),
                    dto.getRegFee(),
                    new Student(student.getStudentId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()),
                    new Course(course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration())
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
    public boolean updateRegistration(RegistrationDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            registrationDAO.update(new Registration(
                    Integer.parseInt(dto.getRegNo()),
                    dto.getRegDate(),
                    dto.getRegFee(),
                    new Student(student.getStudentId(),student.getName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()),
                    new Course(course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration())
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
    public boolean deleteRegistration(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            registrationDAO.delete(id);
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
    public RegistrationDTO searchRegistration(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        Registration registration = null;
        try {
            tx = session.beginTransaction();
            registration = registrationDAO.search(id);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return new RegistrationDTO(
                String.valueOf(registration.getRegNo()),
                registration.getRegDate(),
                registration.getRegFee(),
                registration.getStudent_Id().getId(),
                registration.getCourse_Code().getCode()
        );
    }

    @Override
    public ArrayList<RegistrationDTO> getAllRegistration() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        ArrayList<RegistrationDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Registration> all = registrationDAO.getAll();
            for (Registration registration : all) {
                list.add(new RegistrationDTO(
                        String.valueOf(registration.getRegNo()),
                        registration.getRegDate(),
                        registration.getRegFee(),
                        registration.getStudent_Id().getId(),
                        registration.getCourse_Code().getCode()
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
    public String getLastRegistrationId() throws Exception {
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
            return "1";
        } else {
//            int maxId = Integer.parseInt(lastId.replace("", ""));
//            maxId = maxId + 1;
//            String id = "";
//            if (maxId < 10) {
//                id = "00" + maxId;
//            } else if (maxId < 100) {
//                id = "0" + maxId;
//            } else {
//                id = "" + maxId;
//            }
            return lastId;
        }
    }

    @Override
    public RegistrationDTO searchCourse(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        Course course = null;
        try {
            tx = session.beginTransaction();
            course = courseDAO.search(id);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return new RegistrationDTO(
                course.getCode(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        );
    }

    @Override
    public boolean saveCourse(RegistrationDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            courseDAO.save(new Course(
                    dto.getCode(),
                    dto.getCourseName(),
                    dto.getCourseType(),
                    dto.getDuration()
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
    public ArrayList<String> getAllCourseCodes() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<String> all = courseDAO.getAllCourseCodes();
            for (String s : all) {
                list.add(s);
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
}
