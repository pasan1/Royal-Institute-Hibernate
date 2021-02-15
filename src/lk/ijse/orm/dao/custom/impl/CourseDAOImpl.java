package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.dao.CrudDAOImpl;
import lk.ijse.orm.dao.custom.CourseDAO;
import lk.ijse.orm.entity.Course;

import java.util.List;

public class CourseDAOImpl extends CrudDAOImpl<Course, String> implements CourseDAO {
    @Override
    public String getLastCourseCode() throws Exception {
        return (String) session.createNativeQuery("SELECT code FROM Course ORDER BY code DESC LIMIT 1").uniqueResult();
    }

    @Override
    public List<String> getAllCourseCodes() throws Exception {
        return session.createNativeQuery("SELECT code FROM Course").list();
    }

    @Override
    public List<Course> getSearchCourseDetails(String id) throws Exception {
        return session.createNativeQuery("SELECT code FROM Course WHERE code=" + id + "").list();
    }
}
