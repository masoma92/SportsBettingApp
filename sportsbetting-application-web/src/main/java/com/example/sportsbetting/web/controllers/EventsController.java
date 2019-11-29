package com.example.sportsbetting.web.controllers;

import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventsController {

    @Autowired
    private SportEventRepository sportEventRepository;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getDefaultPage() {
        ModelAndView model = new ModelAndView();
        Iterable<SportEvent> sportevents = sportEventRepository.findAll();
        model.addObject("sportevents", sportevents);
        model.setViewName("events");
        return model;
    }
}
