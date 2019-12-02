package com.example.sportsbetting.web.controllers;

import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class EventsController {

    @Autowired
    private SportEventRepository sportEventRepository;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getDefaultPage(Locale locale) {
        ModelAndView model = new ModelAndView();
        Iterable<SportEvent> sportevents = sportEventRepository.findAll();
        model.addObject("sportevents", sportevents);
        model.setViewName("events");
        localizeHome(model, locale);

        locale = LocaleContextHolder.getLocale();
        model.addObject("locale", locale);
        return model;
    }

    private ModelAndView localizeHome(ModelAndView model, Locale locale) {
        model.addObject("sportsbetting", messageSource.getMessage("sportsbetting", null, locale));
        model.addObject("home", messageSource.getMessage("home", null, locale));
        model.addObject("events", messageSource.getMessage("events", null, locale));
        model.addObject("language", messageSource.getMessage("language", null, locale));
        model.addObject("hungarian", messageSource.getMessage("hungarian", null, locale));
        model.addObject("english", messageSource.getMessage("english", null, locale));
        model.addObject("logout", messageSource.getMessage("logout", null, locale));
        model.addObject("events", messageSource.getMessage("events", null, locale));
        model.addObject("event_title", messageSource.getMessage("event_title", null, locale));
        model.addObject("event_type", messageSource.getMessage("event_type", null, locale));
        model.addObject("start_date", messageSource.getMessage("start_date", null, locale));
        model.addObject("end_date", messageSource.getMessage("end_date", null, locale));
        return model;
    }
}
