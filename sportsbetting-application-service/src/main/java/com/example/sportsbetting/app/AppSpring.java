package com.example.sportsbetting.app;

import com.example.sportsbetting.config.AppConfig;
import com.example.sportsbetting.config.JpaConfig;
import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.domain.builders.*;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class AppSpring {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class)) {
            App app = appContext.getBean(App.class);

            EntityManagerFactory emf = appContext.getBean(EntityManagerFactory.class);
            EntityManager em = emf.createEntityManager();

            EntityTransaction tr = em.getTransaction();
            tr.begin();

            DataBuilder builder = appContext.getBean(DataBuilder.class);

            for (SportEvent event : builder.getEvents()){
                em.persist(event);
                for (Bet bet : event.getBets()){
                    em.persist(bet);
                    for (Outcome oc : bet.getOutcomes()){
                        em.persist(oc);
                        for (OutcomeOdd oco : oc.getOutcomeOdds()){
                            em.persist(oco);
                        }
                    }
                }
            }

            tr.commit();

            app.play();

        }
    }
}
