package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.LoginBO;
import lk.ijse.orm.util.FileManager;

public class LoginBOImpl implements LoginBO {

    @Override
    public String getPassFromFile() throws Exception {
        return FileManager.getInstance().readFile();
    }

    @Override
    public void setPassToFile(String pass) throws Exception {
        FileManager.getInstance().writeFile(pass);
    }
}
