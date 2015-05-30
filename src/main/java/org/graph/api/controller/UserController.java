package org.graph.api.controller;


import org.graph.api.neo4j.domain.GUser;
import org.graph.api.service.GraphUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class UserController {

    @Autowired
    GraphUserService userService;
    
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String profile(Model model) {
//        final GUser user = userService.getUserFromSession();
//        model.addAttribute("user", user);
//        if (user!=null) {
//            model.addAttribute("databases", userService.getDatabases(user) );
//            model.addAttribute("organization", userService.getOrganization(user));
//        }
        return "/user/index";
    }


}