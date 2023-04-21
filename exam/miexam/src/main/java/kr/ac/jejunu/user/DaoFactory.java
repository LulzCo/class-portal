package kr.ac.jejunu.user;

public class DaoFactory {

    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

}
