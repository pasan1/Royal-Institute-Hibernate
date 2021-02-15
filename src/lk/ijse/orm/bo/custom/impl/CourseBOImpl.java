package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.CourseBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.CourseDAO;
import lk.ijse.orm.dto.CourseDTO;
import lk.ijse.orm.entity.Course;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    private final CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
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
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            courseDAO.update(new Course(
                    dto.getCode(),
                    dto.getCourseName(),
                    dto.getCourseType(),
                    dto.getDuration()
            ));
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            courseDAO.delete(id);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public CourseDTO searchCourse(String id) throws Exception {
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
        return new CourseDTO(
                course.getCode(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        );
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        ArrayList<CourseDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Course> all = courseDAO.getAll();
            for (Course course : all) {
                list.add(new CourseDTO(
                        course.getCode(),
                        course.getCourseName(),
                        course.getCourseType(),
                        course.getDuration()
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
    public ArrayList<CourseDTO> getSearchCourseDetails(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        ArrayList<CourseDTO> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            List<Course> all = courseDAO.getSearchCourseDetails(id);
            for (Course course : all) {
                list.add(new CourseDTO(
                        course.getCode(),
                        course.getCourseName(),
                        course.getCourseType(),
                        course.getDuration()
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
    public String getLastCourseId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        String lastId;
        try {
            tx = session.beginTransaction();
            lastId = courseDAO.getLastCourseCode();
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        } finally {
            session.close();
        }

        if (lastId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }
}
