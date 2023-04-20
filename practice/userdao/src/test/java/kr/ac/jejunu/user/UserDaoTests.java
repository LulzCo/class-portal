package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeEach
    public void setUp() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = ac.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException {
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
        String name = "hulk";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        assertThat(user.getId(), greaterThan(1L));

        User insertedUser = userDao.findById(user.getId());

        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1234");
        userDao.insert(user);

        User updateUser = userDao.findById(user.getId());

        updateUser.setName("헐크");
        updateUser.setPassword("1111");

        userDao.update(updateUser);

        assertThat(updateUser.getId(), is(user.getId()));
        assertThat(updateUser.getName(), is("헐크"));
        assertThat(updateUser.getPassword(), is("1111"));

    }

    @Test
    public void delete() throws SQLException {
        User user = new User();
        user.setName("헐크");
        user.setPassword("1111");
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }
}
