package pl.coderslab.spring01hibernateonljeew24;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("pl.coderslab.spring01hibernateonljeew24")
public class AppConfig implements WebMvcConfigurer {
}
