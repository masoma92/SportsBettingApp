package com.example.sportsbetting.app;

import com.example.sportsbetting.config.AppConfig;
import com.example.sportsbetting.config.JpaConfig;
import com.example.sportsbetting.domain.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
