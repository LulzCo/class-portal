package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void getForJeju() throws SQLException, ClassNotFoundException {
        Long id = 69l;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new JejuUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insertForJeju() throws SQLException, ClassNotFoundException {
        String name = "hulk";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new JejuUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(1L);

        assertThat(user.getName(), is(insertedUser.getName()));
        assertThat(user.getPassword(), is(insertedUser.getPassword()));
    }

    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
        Long id = 69l;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new HallaUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        String name = "hulk";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new HallaUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(1L);

        assertThat(user.getName(), is(insertedUser.getName()));
        assertThat(user.getPassword(), is(insertedUser.getPassword()));

    }
}
