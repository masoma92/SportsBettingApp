package com.example.sportsbetting.config;

import com.example.sportsbetting.app.App;
import com.example.sportsbetting.app.service.SportsBettingService;
import com.example.sportsbetting.app.view.View;
import com.example.sportsbetting.domain.builders.DataBuilder;
import com.example.sportsbetting.domain.builders.PlayerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.inject.Inject;
import java.util.Locale;
import java.util.Scanner;

@Configuration
@Import({MessageSourceConfig.class})
@PropertySource(value = { "classpath:config.properties" }, encoding = "utf-8")
public class AppConfig {

    @Value("${default.language}")
    private Locale locale;

    @Bean
    public App app(){
        return new App(service(), view());
    }

    @Bean
    public SportsBettingService service(){
        return new SportsBettingService();
    }

    @Bean
    public View view(){
        return new View(locale);
    }

    @Bean(initMethod = "buildingDatas")
    public DataBuilder dataBuilder(){
        return new DataBuilder();
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public PlayerBuilder playerBuilder(){
        return new PlayerBuilder();
    }

}
