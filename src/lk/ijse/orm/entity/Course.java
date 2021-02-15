package lk.ijse.orm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String courseName;
    private String courseType;
    private String duration;
    @OneToMany(mappedBy = "course_Code")
    private List<Registration> registrationList;

    public Course() {
    }

    public Course(String code, String courseName, String courseType, String duration) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setCourseType(courseType);
        this.setDuration(duration);
    }

    public Course(String code, String courseName, String courseType, String duration, List<Registration> registrationList) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setCourseType(courseType);
        this.setDuration(duration);
        this.setRegistrationList(registrationList);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + getCode() + '\'' +
                ", courseName='" + getCourseName() + '\'' +
                ", courseType='" + getCourseType() + '\'' +
                ", duration='" + getDuration() + '\'' +
                '}';
    }
}
