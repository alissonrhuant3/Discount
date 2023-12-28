package com.projeto.discount.resources;

import com.projeto.discount.entities.Store;
import com.projeto.discount.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StoreResource {

    @Autowired
    private StoreService ss;

    @RequestMapping("/paginainicial")
    public ModelAndView store(){
        ModelAndView mv = new ModelAndView("index");

        Iterable<Store> store = ss.findAll();
        mv.addObject("store",store);
        return mv;
    }
}
