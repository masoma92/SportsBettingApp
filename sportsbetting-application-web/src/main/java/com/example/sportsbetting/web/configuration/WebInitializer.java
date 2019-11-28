package com.example.sportsbetting.web.configuration;

import com.example.sportsbetting.app.AppSpring;
import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.config.AppConfig;
import com.example.sportsbetting.config.JpaConfig;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.repository.SportEventRepository;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.List;

public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(JpaConfig.class);
        ctx.register(AppConfig.class);
        ctx.register(WebConfig.class);

        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
