package ru.rightcode.anketi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class })
@EnableJpaRepositories(basePackages = "ru.rightcode.anketi.repository")
@EnableCaching()
public class QuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }
}

