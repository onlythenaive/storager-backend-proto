package ru.spb.iac.storager.server;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ru.spb.iac.storager.server.security.UserSecurityInterceptor;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private UserSecurityInterceptor userSecurityInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(userSecurityInterceptor)
                .addPathPatterns("/data/**")
                .addPathPatterns("/security/**")
                .excludePathPatterns("/security/logon");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public RequestForwardFilter indexPageForwardFilter() {
        final Set<String> exclusionPrefixes = new HashSet<>();
        exclusionPrefixes.add("/data");
        exclusionPrefixes.add("/integration");
        exclusionPrefixes.add("/security");
        exclusionPrefixes.add("/static");
        return new RequestForwardFilter("/", exclusionPrefixes);
    }

    @Bean
    public FilterRegistrationBean indexPageForwardFilterRegistration(final RequestForwardFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("indexPageForwardFilter");
        registration.setOrder(1);
        return registration;
    }
}
