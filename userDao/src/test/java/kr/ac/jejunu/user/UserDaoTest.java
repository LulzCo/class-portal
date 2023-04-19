package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

class UserDaoTest {

    private static UserDao userDao;

    @BeforeAll
    public static void setUp() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 41l;      // -> 오류 수정하며 테이블 데이터 리셋 후 작업해서 수정하게 되었음
        String name = "seongwon";
        String password = "1234";

        User user = userDao.findById(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "양성원";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        System.out.println(user.getId());
        assertThat(user.getId(), greaterThan(83l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));

    }

    @Test
    public void update() throws SQLException {
        User user = new User();
        String name = "양성원";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        String updatedName = "updated양성원";
        user.setName(updatedName);
        String updatedPassword = "4321";
        user.setPassword(updatedPassword);
        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }

    private User insertedUser() throws SQLException {
        String name = "양성원";
        String password = "12345";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }
}