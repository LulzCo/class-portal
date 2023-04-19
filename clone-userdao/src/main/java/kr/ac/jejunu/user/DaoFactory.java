package kr.ac.jejunu.user;

public class DaoFactory {

    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

    public UserDao userDao(ConnectionMaker connectionMaker) {
        return new UserDao(connectionMaker);
    }
}
