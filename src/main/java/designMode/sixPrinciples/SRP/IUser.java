package designMode.sixPrinciples.SRP;


interface IUser {
    void setUserId(String userId);
    String getUserId();
    void setPassword(String password);
    String getPassWord();
    void setUserName(String name);
    String getUserName();
}

interface IUserBehaive {

    boolean changePassword(String oldPassword);
    boolean deleteUser();
    boolean addOrg(int orgId);
    boolean addRole(int roleId);
}

interface IUserInfo extends IUser, IUserBehaive{
}

class User {
    private void doUser() {
        User user = new User();
        // 用户信息对象
        IUser iUser = (IUser) user;

        // 用户行为对象
        IUserBehaive userBehaive = (IUserBehaive) user;
    }
}

