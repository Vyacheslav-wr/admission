package by.salei.admission.config;

import by.salei.admission.filter.BaseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<BaseFilter> basFilter(){

        FilterRegistrationBean<BaseFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BaseFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addUrlPatterns("/faculty/*");

        return registrationBean;
    }
}
