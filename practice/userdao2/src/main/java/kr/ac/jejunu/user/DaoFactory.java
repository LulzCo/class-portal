package kr.ac.jejunu.user;

public class DaoFactory {

    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
}
