package com.example.sportsbetting.config;

import com.example.sportsbetting.app.App;
import com.example.sportsbetting.app.service.SportsBettingService;
import com.example.sportsbetting.app.view.View;
import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.Wager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@Import({MessageSourceConfig.class, JpaConfig.class})
@PropertySource(value = { "classpath:config.properties" }, encoding = "UTF-8")
public class AppConfig {

    @Value("${default.language}")
    private Locale locale;

    @Bean
    public App app(){
        return new App();
    }

    @Bean
    public SportsBettingService service(){
        return new SportsBettingService();
    }

    @Bean
    public View view(){
        return new View(locale);
    }

    @Bean(initMethod = "buildingData") //after creating bean it runs buildingData method
    public DataBuilder dataBuilder(){
        return new DataBuilder();
    }

    // for view
    @Bean
    public Player.PlayerBuilder playerBuilder(){
        return new Player.PlayerBuilder();
    }

    @Bean
    public Player player(){
        return new Player();
    }

    @Bean
    public List<SportEvent> events(){
        return new ArrayList<SportEvent>();
    }

    @Bean
    public List<Wager> wagers(){
        return new ArrayList<Wager>();
    };

}
