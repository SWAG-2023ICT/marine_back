package swag.marine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableWebMvc
@SpringBootApplication
public class MarineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarineApplication.class, args);
    }

}
