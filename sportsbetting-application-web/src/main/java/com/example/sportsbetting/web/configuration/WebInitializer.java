package com.example.sportsbetting.web.configuration;

import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.config.AppConfig;
import com.example.sportsbetting.config.JpaConfig;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.repository.PlayerRepository;
import com.example.sportsbetting.repository.SportEventRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.List;

public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
        try(AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext()){

            ctx.register(WebConfig.class);

            ctx.setServletContext(container);

            //web xml helyett
            ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

            servlet.setLoadOnStartup(1);
            servlet.addMapping("/");

            /*ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

            DataBuilder dataBuilder = appContext.getBean(DataBuilder.class);
            SportEventRepository sportEventRepository = appContext.getBean(SportEventRepository.class);
            PlayerRepository playerRepository = appContext.getBean(PlayerRepository.class);

            List<SportEvent> sportEvents = dataBuilder.getEvents();
            Player player = dataBuilder.getPlayer();

            sportEventRepository.saveAll(sportEvents);
            playerRepository.save(player);*/
        }
    }
}
