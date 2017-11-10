package my;

import my.hibernate.config.HibernateConfig;
import my.hibernate.entities.Foreignwords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("my")
@EntityScan(basePackages = "my.hibernate.entities")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{Application.class,HibernateConfig.class,Foreignwords.class},args);
    }
}
