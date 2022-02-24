package hello.jejulu.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //HttpHiddenMethodFilter 활성화 Spring빈 등록
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}
