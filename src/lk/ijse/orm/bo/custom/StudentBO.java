package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBO;
import lk.ijse.orm.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public boolean saveStudent(StudentDTO dto) throws Exception;

    public boolean updateStudent(StudentDTO dto) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public StudentDTO searchStudent(String id) throws Exception;

    public ArrayList<StudentDTO> getAllStudents() throws Exception;

    public String getLastStudentId() throws Exception;
}
