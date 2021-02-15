package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBO;
import lk.ijse.orm.dto.CourseDTO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    public boolean saveCourse(CourseDTO dto) throws Exception;

    public boolean updateCourse(CourseDTO dto) throws Exception;

    public boolean deleteCourse(String id) throws Exception;

    public CourseDTO searchCourse(String id) throws Exception;

    public ArrayList<CourseDTO> getAllCourse() throws Exception;

    public ArrayList<CourseDTO> getSearchCourseDetails(String id) throws Exception;

    public String getLastCourseId() throws Exception;
}
