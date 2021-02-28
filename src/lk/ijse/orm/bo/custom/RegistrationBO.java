package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBO;
import lk.ijse.orm.dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegistrationBO extends SuperBO {
    public void setStatus(String status);

    public void setStudent(RegistrationDTO student);

    public RegistrationDTO getStudent();

    public void setCourse(RegistrationDTO Course);

    public RegistrationDTO getCourse();

    public boolean saveStudent(RegistrationDTO dto) throws Exception;

    public boolean updateStudent(RegistrationDTO dto) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public RegistrationDTO searchStudent(String id) throws Exception;

    public ArrayList<RegistrationDTO> getSearchStudentDetails(String id) throws Exception;

    public ArrayList<RegistrationDTO> getAllStudents() throws Exception;

    public String getLastStudentId() throws Exception;

    public boolean saveRegistration(RegistrationDTO dto) throws Exception;

    public boolean updateRegistration(RegistrationDTO dto) throws Exception;

    public boolean deleteRegistration(String id) throws Exception;

    public RegistrationDTO searchRegistration(String id) throws Exception;

    public ArrayList<RegistrationDTO> getAllRegistration() throws Exception;

    public int getLastRegistrationId() throws Exception;

    public RegistrationDTO searchCourse(String id) throws Exception;

    public boolean saveCourse(RegistrationDTO dto) throws Exception;

    public ArrayList<String> getAllCourseCodes() throws Exception;
}
