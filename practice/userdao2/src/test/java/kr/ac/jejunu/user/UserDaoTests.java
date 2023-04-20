package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.support.NullValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = ac.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "hulk";
        String password = "1234";
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = insertedUser();

        assertThat(user.getId(), greaterThan(1L));

        User insertedUser = userDao.findById(user.getId());

        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException {
        User user = insertedUser();

        User insertedUser = userDao.findById(user.getId());
        String updateName = "헐크";
        String updatePassword = "1111";
        insertedUser.setName(updateName);
        insertedUser.setPassword(updatePassword);
        userDao.update(insertedUser);

        User updatedUser = userDao.findById(user.getId());

        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(insertedUser.getName()));
        assertThat(updatedUser.getPassword(), is(insertedUser.getPassword()));

    }

    @Test
    public void delete() throws SQLException {
        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, nullValue());
    }

    private User insertedUser() throws SQLException {
        String name = "hulk";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }
}
