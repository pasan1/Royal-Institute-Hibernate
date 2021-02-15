package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Course;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course, String> {
    public String getLastCourseCode() throws Exception;

    List<String> getAllCourseCodes() throws Exception;

    List<Course> getSearchCourseDetails(String id) throws Exception;
}
