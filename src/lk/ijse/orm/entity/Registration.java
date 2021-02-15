package lk.ijse.orm.entity;

import javax.persistence.*;

@Entity
public class Registration implements SuperEntity {
    @Id
    private int regNo;
    private String regDate;
    private double regFee;
    @ManyToOne
//    @JoinColumn(name = "student_Id", referencedColumnName = "id")
    private Student student_Id;

    @ManyToOne
//    @JoinColumn(name = "course_Code", referencedColumnName = "code")
    private Course course_Code;

    public Registration() {
    }

    public Registration(int regNo, String regDate, double regFee, Student student_Id, Course course_Code) {
        this.setRegNo(regNo);
        this.setRegDate(regDate);
        this.setRegFee(regFee);
        this.setStudent_Id(student_Id);
        this.setCourse_Code(course_Code);
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public Student getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(Student student_Id) {
        this.student_Id = student_Id;
    }

    public Course getCourse_Code() {
        return course_Code;
    }

    public void setCourse_Code(Course course_Code) {
        this.course_Code = course_Code;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student_Id='" + student_Id + '\'' +
                ", course_Code='" + course_Code + '\'' +
                '}';
    }
}
