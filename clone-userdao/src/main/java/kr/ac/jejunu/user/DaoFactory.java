package kr.ac.jejunu.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

    @Bean
    public UserDao userDao(ConnectionMaker connectionMaker) {
        return new UserDao(connectionMaker);
    }
}
