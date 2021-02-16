package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBO;

public interface LoginBO extends SuperBO {
    public String getPassFromFile() throws Exception;

    public void setPassToFile(String pass) throws Exception;
}
