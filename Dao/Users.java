package Dao;

public class Users
{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(int userName, String password) {
        this.userName = String.valueOf(userName);
        this.password = password;
    }
}
