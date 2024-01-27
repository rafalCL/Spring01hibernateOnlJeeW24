package pl.coderslab.spring01hibernateonljeew24;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.coderslab.spring01hibernateonljeew24.converter.AuthorConverter;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("pl.coderslab.spring01hibernateonljeew24")
@EnableTransactionManagement
@EnableJpaRepositories("pl.coderslab.spring01hibernateonljeew24.repository")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("bookstorePersistenceUnit");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public AuthorConverter authorConverter() {
        return new AuthorConverter();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(name="localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("pl","PL"));
        localeResolver.setDefaultLocale(new Locale("en","EN"));
        return localeResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(authorConverter());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}
