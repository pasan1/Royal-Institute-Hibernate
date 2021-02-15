package lk.ijse.orm.dto;


import java.sql.Date;

public class RegistrationDTO {
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private Date dob;
    private String gender;

    private String regNo;
    private String regDate;
    private Double regFee;
    private String courseCode;

    private String code;
    private String courseName;
    private String courseType;
    private String duration;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String regNo, String regDate, Double regFee, String studentId, String courseCode) {
        this.setRegNo(regNo);
        this.setRegDate(regDate);
        this.setRegFee(regFee);
        this.setStudentId(studentId);
        this.setCourseCode(courseCode);
    }

    public RegistrationDTO(String studentId, String name, String address, String contact, Date dob, String gender) {
        this.setStudentId(studentId);
        this.setName(name);
        this.setAddress(address);
        this.setContact(contact);
        this.setDob(dob);
        this.setGender(gender);
    }

    public RegistrationDTO(String code, String courseName, String courseType, String duration) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setCourseType(courseType);
        this.setDuration(duration);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Double getRegFee() {
        return regFee;
    }

    public void setRegFee(Double regFee) {
        this.regFee = regFee;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "studentId='" + getStudentId() + '\'' +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", contact='" + getContact() + '\'' +
                ", dob='" + getDob() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", regNo='" + getRegNo() + '\'' +
                ", regDate='" + getRegDate() + '\'' +
                ", regFee=" + getRegFee() +
                ", courseCode='" + getCourseCode() + '\'' +
                '}';
    }
}
